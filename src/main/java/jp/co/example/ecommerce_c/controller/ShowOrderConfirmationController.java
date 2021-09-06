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
import jp.co.example.ecommerce_c.service.ShowOrderConfirmationService;

@Controller
@RequestMapping("/showOrderConfirmation")
public class ShowOrderConfirmationController {

	@Autowired
	private ShowOrderConfirmationService service;

	@Autowired
	private HttpSession sessison;
	
	@ModelAttribute
	public OrderForm setUpForm() {
		return new OrderForm();
	}

	/**
	 * 注文内容確認画面を表示する.
	 * 
	 * @param model 注文情報をリクエストスコープに格納
	 * @return 注文内容確認画面へフォワードする.
	 */
	@RequestMapping("")
	public String showOrderConfirmation(Model model) {
		User user = (User) sessison.getAttribute("user");
		Integer userId = user.getId();
		Order order = service.showShoppingCart(userId);
		int subtotalPrice = order.getTotalPrice();
		int tax = (int) (subtotalPrice * 0.1);
		int totalPrice = subtotalPrice + tax;
		System.out.println("合計金額"+totalPrice);
		if(totalPrice == 0) {
			model.addAttribute("noItemMessage","カートに商品がありません。");
		}
		model.addAttribute("order", order);
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("tax", tax);
		return "/order_confirm";
	}

}
