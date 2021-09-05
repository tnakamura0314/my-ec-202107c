package jp.co.example.ecommerce_c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_c.service.DeleteCartService;

/**
 * 商品を削除するコントローラー.
 * @author kanekojota
 *
 */
@Controller
@RequestMapping("/shopping-cart/delete")
public class DeleteCartController {
	@Autowired
	private DeleteCartService deleteCartService;

	/**
	 * ショッピングカートの商品を削除する（ショッピングカート一覧画面へリダイレクト）.
	 * @param orderItemId 削除する商品Id
	 * @return
	 */
	@RequestMapping("")
	public String deleteOrderItemAndOrderTopping(int orderItemId) {
		deleteCartService.deleteOrderItemAndOrderTopping(orderItemId);
		return "redirect:/shopping-cart/show";
	}
	
	/**
	 * ショッピングカートの商品を削除する.(確認画面へリダイレクト)
	 * @param orderItemId 削除する商品Id
	 * @return
	 */
	@RequestMapping("/from-confirm")
	public String deleteOrderItemAndOrderToppingFromConfirm(int orderItemId) {
		deleteCartService.deleteOrderItemAndOrderTopping(orderItemId);
		return "redirect:/order-confirm";
	}
}
