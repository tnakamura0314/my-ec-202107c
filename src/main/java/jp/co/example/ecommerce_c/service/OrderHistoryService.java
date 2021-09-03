package jp.co.example.ecommerce_c.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.example.ecommerce_c.domain.Order;
import jp.co.example.ecommerce_c.repository.OrderRepository;

/**
 * 注文履歴情報を操作するサービスクラス.
 * 
 * @author nakamuratomoya
 *
 */
@Service
public class OrderHistoryService {
	
	@Autowired
	private OrderRepository repository;
	
	
	//後ほど実装します
	/**
	 * 注文履歴情報を取得するメソッド.
	 * 
	 * @return
	 */
//	public List<Order> orderHistory(){
//		
//		
//		List<Order> orderHistoryList = repository.
////		
//		return orderHistoryList;
//	}

}
