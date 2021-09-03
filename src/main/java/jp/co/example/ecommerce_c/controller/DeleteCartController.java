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
	 * ショッピングカートの商品を削除する.
	 * @param orderItemId 削除する商品Id
	 * @return
	 */
	@RequestMapping("")
	public String deleteOrderItemAndOrderTopping(int orderItemId) {
		System.out.println("IDテスト"+orderItemId);
		deleteCartService.deleteOrderItemAndOrderTopping(orderItemId);
		return "redirect:/shopping-cart/show";
	}
}
