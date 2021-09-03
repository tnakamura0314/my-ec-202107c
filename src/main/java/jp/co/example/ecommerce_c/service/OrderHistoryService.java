package jp.co.example.ecommerce_c.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import jp.co.example.ecommerce_c.domain.Order;
import jp.co.example.ecommerce_c.domain.OrderItem;
import jp.co.example.ecommerce_c.domain.OrderTopping;
import jp.co.example.ecommerce_c.domain.Status;
import jp.co.example.ecommerce_c.form.OrderForm;
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
	private OrderRepository orderRepository;
	
	@ModelAttribute
	public OrderForm setUpForm(){
		return new OrderForm();
	}
	
	/**
	 * 注文履歴一覧を取得します.
	 * @param userId ログイン中のユーザー
	 * @return したショッピングカート一覧
	 */
	public Order orderHistory(int userId) {
		int totalPrice = 0;
		int status = Status.DELIVERED.getKey();
		int status2 =  Status.DELIVERY_COMPLETE.getKey();
		Order order = orderRepository.findByUserIdAndStatusOfOrderHistory(userId, status, status2);
		if (order != null) {
			for (OrderItem orderItem : order.getOrderItemList()) {
				// サイズによりトッピングの価格が異なるためサイズで場合分けする
				if (orderItem.getSize() == 'M') {
					int pizzaPriceM = orderItem.getItem().getPriceM();
					totalPrice += pizzaPriceM;
					if (orderItem.getOrderToppingList() != null) {
						int toppingPrice = 0;
						for (OrderTopping orderTopping : orderItem.getOrderToppingList()) {
							toppingPrice += orderTopping.getTopping().getPriceM();
						}
						totalPrice += toppingPrice;
					}
				} else if (orderItem.getSize() == 'L') {
					int pizzaPriceL = orderItem.getItem().getPriceM();
					totalPrice += pizzaPriceL;
					if (orderItem.getOrderToppingList() != null) {
						int toppingPrice = 0;
						for (OrderTopping orderTopping : orderItem.getOrderToppingList()) {
							toppingPrice += orderTopping.getTopping().getPriceL();
						}
						totalPrice += toppingPrice;
					}
				}
			}
		}
		order.setTotalPrice(totalPrice);
		return order;
	}


}
