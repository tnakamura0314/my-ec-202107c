package jp.co.example.ecommerce_c.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_c.repository.OrderItemRepository;

/**
 * ショッピングカートの商品を削除する.
 * @author kanekojota
 *
 */
@Service
@Transactional
public class DeleteCartService {
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	/**
	 * ショッピングカート内の商品を削除する.
	 * @param orderItemId 削除する商品のid
	 */
	public void deleteOrderItemAndOrderTopping(int orderItemId) {
		orderItemRepository.deleteOrderItemAndOrderTopping(orderItemId);
	}

}
