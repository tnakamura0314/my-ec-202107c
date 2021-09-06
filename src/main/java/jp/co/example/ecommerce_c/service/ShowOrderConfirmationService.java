package jp.co.example.ecommerce_c.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

import jp.co.example.ecommerce_c.domain.Order;
import jp.co.example.ecommerce_c.domain.OrderItem;
import jp.co.example.ecommerce_c.domain.OrderTopping;
import jp.co.example.ecommerce_c.domain.Status;
import jp.co.example.ecommerce_c.form.OrderForm;
import jp.co.example.ecommerce_c.repository.OrderRepository;

/**
 * 注文情報を操作するサービス.
 * 
 * @author nakamuratomoya
 *
 */
@Service
@Transactional
public class ShowOrderConfirmationService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@ModelAttribute
	public OrderForm setUpForm(){
		return new OrderForm();
	}
	
	/**
	 * ショッピングカートの商品一覧を取得します.
	 * @param userId ログイン中のユーザー
	 * @return したショッピングカート一覧
	 */
	public Order showShoppingCart(int userId) {
		int totalPrice = 0;
		int status = Status.BOFORE_ORDER.getKey();
		Order order = orderRepository.findByUserIdAndStatus(userId, status);
		
		if (order != null) {
			if(order.getOrderItemList().size() == 0) {
				totalPrice = 0;
				order.setTotalPrice(totalPrice);
				orderRepository.updateTotalPrice(order.getId(),totalPrice);
			}
			for (OrderItem orderItem : order.getOrderItemList()) {
				// サイズによりトッピングの価格が異なるためサイズで場合分けする
				if (orderItem.getSize() == 'M') {
					int pizzaPriceM = orderItem.getItem().getPriceM();
					totalPrice += pizzaPriceM * orderItem.getQuantity();
					if (orderItem.getOrderToppingList() != null) {
						int toppingPrice = 0;
						for (OrderTopping orderTopping : orderItem.getOrderToppingList()) {
							toppingPrice += orderTopping.getTopping().getPriceM() * orderItem.getQuantity();
						}
						totalPrice += toppingPrice;
						order.setTotalPrice(totalPrice);
						orderRepository.updateTotalPrice(order.getId(),totalPrice);
					}
				} else if (orderItem.getSize() == 'L') {
					int pizzaPriceL = orderItem.getItem().getPriceM();
					totalPrice += pizzaPriceL * orderItem.getQuantity();
					if (orderItem.getOrderToppingList() != null) {
						int toppingPrice = 0;
						for (OrderTopping orderTopping : orderItem.getOrderToppingList()) {
							toppingPrice += orderTopping.getTopping().getPriceL() * orderItem.getQuantity();
						}
						totalPrice += toppingPrice;
						order.setTotalPrice(totalPrice);
						orderRepository.updateTotalPrice(order.getId(),totalPrice);
					}
				}

			}
		}
		return order;
	}

}
