package jp.co.example.ecommerce_c.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_c.domain.User;
import jp.co.example.ecommerce_c.service.DeleteUserService;

@Controller
@RequestMapping("/user-detail")
public class UserDetailController {

	@Autowired
	private DeleteUserService deleteUserService;

	@Autowired
	private HttpSession session;

	/**
	 * ユーザー詳細ページを表示.
	 * 
	 * @return ユーザー詳細ページ
	 */
	@RequestMapping("")
	public String index() {
		return "/user_detail";

	}

	/**
	 * 退会をする.
	 * 
	 * @return ログイン画面
	 */
	@RequestMapping("/quit")
	public String quit() {
		User user = (User) session.getAttribute("user");
		Integer userId = user.getId();
		// セッションの切断
		session.invalidate();
		deleteUserService.deleteById(userId);
		return "forward:/login";
	}

}
