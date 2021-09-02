package jp.co.example.ecommerce_c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_c.form.OrderForm;
import jp.co.example.ecommerce_c.service.OrderService;

/**
 * 注文情報を操作するコントローラー.
 * @author kanekojota
 *
 */
@Controller
@RequestMapping("/order-confirm")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@ModelAttribute
	public OrderForm setUpForm() {
		return new OrderForm();
	}
	
	/**
	 * 注文確認画面へ遷移する.
	 * @return 注文確認画面へフォワードする
	 */
	@RequestMapping("")
	public String toOrderConfirm() {
		return "order_confirm";
	}
	
	/**
	 * 受け取った入力情報をもとに注文処理を行う.
	 * @param form
	 * @return
	 */
	@RequestMapping("/order")
	public String order(@Validated OrderForm form,BindingResult result,Model model) {
		
		if (result.hasErrors()) {
			return toOrderConfirm();
		}
		orderService.update(form,model);
		return "redirect:/order-confirm/toComplete";
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
