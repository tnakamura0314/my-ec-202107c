package jp.co.example.ecommerce_c.controller;

import javax.servlet.http.HttpSession;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;

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
	 * @param form 利用者情報用フォーム
	 * @param result エラー情報格納用オブジェクト
	 * @return ログイン後の商品一覧画面
	 */
	@RequestMapping("/login_user")
	public String login_user(LoginForm form, BindingResult result) {
		User user = (User) loginService.login(form.getEmail(), form.getPassword());
		if(user == null) {
			result.addError(new ObjectError("", "メールアドレスまたはパスワードが不正です。"));
			return toLogin();
		}
		session.setAttribute("userName", user.getName());
		return "forward:/showItem/showItemList";
	}

}
