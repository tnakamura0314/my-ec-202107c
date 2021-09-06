package jp.co.example.ecommerce_c.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_c.domain.User;
import jp.co.example.ecommerce_c.form.AddCartForm;
import jp.co.example.ecommerce_c.repository.UserRepository;
import jp.co.example.ecommerce_c.service.AddCartService;

/**
 * ショッピングカートに商品を追加するコントローラー.
 * 
 * @author kanekojota
 *
 */
@Controller
@RequestMapping("shopping-cart/add")
public class AddCartController {

	@Autowired
	private AddCartService AddCartService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private HttpSession sessison;

	@ModelAttribute
	public AddCartService setUpForm() {
		return new AddCartService();
	}

	/**
	 * ショッピングカートに商品を追加する.
	 * 
	 * @param form 入力された商品情報
	 * @return 商品一覧画面へリダイレクト
	 */
	@RequestMapping("")
	public String addShoppingCart(AddCartForm form) {
		User user = (User) sessison.getAttribute("user");
		if (user == null) {
			Integer noLoginUserId = 0;
			for (int i = 0; true; i++) {
				Random randomNum = new Random();
				noLoginUserId = randomNum.nextInt(10000);
				if (userRepository.load(noLoginUserId) == null) {
					break;
				}
			}
			AddCartService.addShoppingCart(noLoginUserId, form);
			sessison.setAttribute("noLoginUserId", noLoginUserId);
		} else {
			Integer userId = user.getId();
			AddCartService.addShoppingCart(userId, form);
		}
		return "redirect:/shopping-cart/show";
	}
}
