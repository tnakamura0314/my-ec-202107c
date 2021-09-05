package jp.co.example.ecommerce_c.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_c.domain.Order;
import jp.co.example.ecommerce_c.domain.OrderItem;
import jp.co.example.ecommerce_c.domain.OrderTopping;
import jp.co.example.ecommerce_c.domain.Status;
import jp.co.example.ecommerce_c.domain.Topping;
import jp.co.example.ecommerce_c.form.AddCartForm;
import jp.co.example.ecommerce_c.repository.OrderItemRepository;
import jp.co.example.ecommerce_c.repository.OrderRepository;
import jp.co.example.ecommerce_c.repository.OrderToppingRepository;
import jp.co.example.ecommerce_c.repository.ToppingRepository;

/**
 * ショッピングカートに商品を追加するためのサービス.
 * @author kanekojota
 *
 */
@Service
@Transactional
public class AddCartService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private OrderToppingRepository orderToppingRepository;

	@Autowired
	private ToppingRepository toppingRepository;


	/**
	 * ショッピングカートに商品を追加する.
	 * @param userId ユーザーId
	 * @param form 入力された商品データ
	 */
	public void addShoppingCart(int userId, AddCartForm form) {
		int status = Status.BOFORE_ORDER.getKey();
		int defaultTotalPrice = 0;
		Order order = orderRepository.findByUserIdAndStatus(userId, status);
		if (order.getId() == null) {
			order.setUserId(userId);
			order.setStatus(status);
			order.setTotalPrice(defaultTotalPrice);
			order = orderRepository.insert(order);
		}
		System.out.println(order);
		OrderItem orderItem = new OrderItem();
		orderItem.setItemId(form.getIntItemId());
		orderItem.setOrderId(order.getId());
		orderItem.setQuantity(form.getIntQuantity());
		orderItem.setReceiveStringSize(form.getSize());
		orderItem = orderItemRepository.insert(orderItem);
		if (form.getToppingList() != null) {
			for (String toppingId : form.getToppingList()) {
				Topping topping = toppingRepository.load(Integer.parseInt(toppingId));
				OrderTopping orderTopping = new OrderTopping();
				orderTopping.setToppingId(topping.getId());
				orderTopping.setOrderItemId(orderItem.getId());
				orderToppingRepository.insert(orderTopping);
			}
		}
	}

}
