package jp.co.example.ecommerce_c.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.example.ecommerce_c.domain.Order;
import jp.co.example.ecommerce_c.repository.OrderRepository;

/**
 * 注文情報を操作するサービス.
 * 
 * @author nakamuratomoya
 *
 */
@Service
public class ShowOrderConfirmationService {
	
	@Autowired
	private OrderRepository repository;
	
	/**
	 * userIdとstatusが0の注文を全件取得するメソッド
	 * 
	 * @param userId　ユーザーID
	 * @return　serIdとstatusが0の全件注文情報
	 */
	public List<Order> showOrderConfirmation(Integer userId){
		
		List<Order> orderList = repository.findByUserIdAndStatus(userId);
		
		return orderList;
 		
	}

}
