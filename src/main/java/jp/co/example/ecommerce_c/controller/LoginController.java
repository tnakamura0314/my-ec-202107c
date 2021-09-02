package jp.co.example.ecommerce_c.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_c.domain.User;
import jp.co.example.ecommerce_c.form.LoginForm;
import jp.co.example.ecommerce_c.service.LoginService;

/**
 * 利用者情報を操作するコントローラー.
 * 
 * @author yuyayokoyama
 *
 */

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginService loginService;
	@Autowired
	private HttpSession session;

	@ModelAttribute
	public LoginForm setUpForm() {
		return new LoginForm();
	}

	/**
	 * ログイン画面を出力します.
	 * 
	 * @return ログイン画面
	 */
	@RequestMapping("")
	public String toLogin() {
		return "login";
	}

	/**
	 * ログインします.
	 * 
	 * @param form   利用者情報用フォーム
	 * @param result エラー情報格納用オブジェクト
	 * @return ログイン後の商品一覧画面
	 */
	@RequestMapping("/login_user")
	public String login_user(LoginForm form, BindingResult result,Model model) {
		System.out.println(form);
		User user = loginService.login(form.getEmail(), form.getPassword());
		if (user == null) {
			model.addAttribute("errorMessage","メールアドレスまたはパスワードが不正です。");
			return toLogin();
		}
		session.setAttribute("userName", user.getName());
		return "item_list_toy";
	}

//　通常のログインは完成
//　6.注文確認画面を表示後のログインは後ほど実装

}
