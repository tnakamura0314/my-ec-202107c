package jp.co.example.ecommerce_c.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_c.domain.User;
import jp.co.example.ecommerce_c.form.AddCartForm;
import jp.co.example.ecommerce_c.service.AddCartService;

/**
 * ショッピングカートに商品を追加するコントローラー.
 * @author kanekojota
 *
 */
@Controller
@RequestMapping("shopping-cart/add")
public class AddCartController {
	
	@Autowired
	private AddCartService AddCartService;
	
	@Autowired
	private HttpSession sessison;

	@ModelAttribute
	public AddCartService setUpForm() {
		return new AddCartService();
	}
	
	/**
	 * ショッピングカートに商品を追加する.
	 * @param form 入力された商品情報
	 * @return 商品一覧画面へリダイレクト
	 */
	@RequestMapping("")
	public String addShoppingCart(AddCartForm form) {
		User user = (User)sessison.getAttribute("user");
		Integer userId = user.getId();
		AddCartService.addShoppingCart(userId,form);
		return "redirect:/shopping-cart/show";
	}
}
