package jp.co.example.ecommerce_c.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ユーザー情報を挿入するコントローラー.
 * @author kanekojota
 *
 */
@Controller
@RequestMapping("/register")
public class RegisterUserController {

	/**
	 * ユーザー登録画面へ遷移する.
	 * @return ユーザー登録画面へフォワード
	 */
	@RequestMapping("")
	public String toRegister() {
		return "register_user";
	}
	
	@RequestMapping("/register-user")
	public String registerUser() {
		return "";
	}
}
