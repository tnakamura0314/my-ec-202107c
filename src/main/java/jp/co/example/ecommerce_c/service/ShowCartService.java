package jp.co.example.ecommerce_c.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_c.domain.Order;
import jp.co.example.ecommerce_c.domain.OrderItem;
import jp.co.example.ecommerce_c.domain.OrderTopping;
import jp.co.example.ecommerce_c.domain.Status;
import jp.co.example.ecommerce_c.repository.OrderRepository;

/**
 * ショッピングカート一覧を表示するサービスクラス.
 * @author kanekojota
 *
 */
@Service
@Transactional
public class ShowCartService {

	@Autowired
	private OrderRepository orderRepository;

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
		System.out.println(totalPrice);
		return order;
	}
}
