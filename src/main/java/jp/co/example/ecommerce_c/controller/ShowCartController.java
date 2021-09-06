package jp.co.example.ecommerce_c.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_c.domain.Order;
import jp.co.example.ecommerce_c.domain.User;
import jp.co.example.ecommerce_c.service.ShowCartService;

/**
 * ショッピングカート一覧表示をするコントローラー.
 * 
 * @author kanekojota
 *
 */
@Controller
@RequestMapping("/shopping-cart/show")
public class ShowCartController {

	@Autowired
	private ShowCartService showCartService;

	@Autowired
	private HttpSession sessison;

	/**
	 * ショッピングカート一覧を表示する.
	 * 
	 * @param model 注文情報をリクエストスコープに格納
	 * @return ショッピングカート一覧画面へフォワードする.
	 */
	@RequestMapping("")
	public String showCart(Model model) {
		User user = (User) sessison.getAttribute("user");
		Order order = null;
		if (user == null) {
			Integer noLoginUserId = (Integer) sessison.getAttribute("noLoginUserId");
			System.out.println("未ログインユーザー：" + noLoginUserId);
			order = showCartService.showShoppingCart(noLoginUserId);
		} else {
			Integer userId = user.getId();
			order = showCartService.showShoppingCart(userId);
		}
		int subtotalPrice = order.getTotalPrice();
		int tax = (int) (subtotalPrice * 0.1);
		int totalPrice = subtotalPrice + tax;
		if (totalPrice == 0) {
			model.addAttribute("noItemMessage", "カートに商品がありません。");
		}
		model.addAttribute("order", order);
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("tax", tax);
		return "cart_list";
	}
}
