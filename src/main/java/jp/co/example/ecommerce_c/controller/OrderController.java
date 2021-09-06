package jp.co.example.ecommerce_c.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_c.domain.Order;
import jp.co.example.ecommerce_c.domain.User;
import jp.co.example.ecommerce_c.form.OrderForm;
import jp.co.example.ecommerce_c.service.OrderService;
import jp.co.example.ecommerce_c.service.ShowOrderConfirmationService;

/**
 * 注文情報を操作するコントローラー.
 * @author kanekojota
 *
 */
@Controller
@RequestMapping("/order-confirm")
public class OrderController {
	
	@Autowired
	private HttpSession sessison;

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ShowOrderConfirmationService orderConfirmationService;
	
	@ModelAttribute
	public OrderForm setUpForm() {
		return new OrderForm();
	}
	
	/**
	 * 注文確認画面へ遷移する.
	 * @return 注文確認画面へフォワードする
	 */
	@RequestMapping("")
	public String showOrderConfirmation(Model model) {
		User user = (User) sessison.getAttribute("user");
		
		if(user == null) {
			return "redirect:/login_user";
		
		} else {
		Integer userId = user.getId();
		Order order = orderConfirmationService.showShoppingCart(userId);
		int subtotalPrice = order.getTotalPrice();
		int tax = (int) (subtotalPrice * 0.1);
		int totalPrice = subtotalPrice + tax;
		model.addAttribute("order", order);
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("tax", tax);
		
		return "/order_confirm";
		}
	}
	
	/**
	 * 受け取った入力情報をもとに注文処理を行う.
	 * @param form
	 * @return
	 */
	@RequestMapping("/order")
	public String order(@Validated OrderForm form,BindingResult result,Model model) {

		if (result.hasErrors()) {
			return showOrderConfirmation(model);
		}
			boolean judge = orderService.update(form);
			if(!judge) {
				model.addAttribute("timeErrorMessage", "今から3時間後の日時をご入力ください");
				return showOrderConfirmation(model);
			}else {
				return "redirect:/order-confirm/toComplete";				
			}
	}
	
	/**
	 * 注文完了画面に遷移する.
	 * @return 注文画面へフォワードする
	 */
	@RequestMapping("/toComplete")
	public String toComplete() {
		return "order_finished";
	}
}
