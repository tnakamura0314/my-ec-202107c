package jp.co.example.ecommerce_c.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_c.domain.Order;
import jp.co.example.ecommerce_c.domain.User;
import jp.co.example.ecommerce_c.form.OrderForm;
import jp.co.example.ecommerce_c.service.OrderHistoryService;

/**
 * 商品注文履歴を操作するコントローラー.
 * 
 * @author nakamuratomoya
 *
 */
@Controller
@RequestMapping("/orderHistory")
public class OrderHistoryController {
	
	@Autowired
	private OrderHistoryService service;
	
	@Autowired
	private HttpSession sessison;
	
	@ModelAttribute
	public OrderForm setUpForm() {
		return new OrderForm();
	}
	
	/**
	 * 注文履歴情報を表示する.
	 * 
	 * @param model requestスコープ
	 * @return　注文履歴情報(1件もなければメッセージ)
	 */
	@RequestMapping("")
	public String orderHistory(Model model) {
		User user = (User) sessison.getAttribute("user");
		Integer userId = user.getId();
		System.out.println("before" + userId);
		Order order = service.orderHistory(userId);
		System.out.println("after" + order.getId());
		int subtotalPrice = order.getTotalPrice();
		int tax = (int) (subtotalPrice * 0.1);
		int totalPrice = subtotalPrice + tax;
		if(totalPrice == 0) {
			model.addAttribute("noItemMessage","カートに商品がありません。");
		}	
		model.addAttribute("order", order);
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("tax", tax);
		
		return "/order_history";
	}

}
