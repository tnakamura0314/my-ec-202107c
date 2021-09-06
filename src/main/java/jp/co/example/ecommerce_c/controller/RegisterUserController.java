package jp.co.example.ecommerce_c.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_c.domain.User;
import jp.co.example.ecommerce_c.form.RegisterUserForm;
import jp.co.example.ecommerce_c.service.ResisterUserService;

/**
 * ユーザー情報を挿入するコントローラー.
 * 
 * @author kanekojota
 *
 */
@Controller
@RequestMapping("/register")
public class RegisterUserController {

	@Autowired
	private ResisterUserService registerUserService;

	@ModelAttribute
	public RegisterUserForm setUpForm() {
		return new RegisterUserForm();
	}

	/**
	 * ユーザー登録画面へ遷移する.
	 * 
	 * @return ユーザー登録画面へフォワード
	 */
	@RequestMapping("")
	public String toRegister() {
		return "register_user";
	}

	@RequestMapping("/register-user")
	public String registerUser(@Validated RegisterUserForm form, BindingResult result) {
		// パスワード確認
		if (!(form.getConfirmPassword().equals(form.getPassword()))) {
			result.rejectValue("confirmPassword", "", "パスワードと確認用パスワードが不一致です。");
		}

		// メールアドレス重複チェック
		User dupulicateUser = registerUserService.findByMailAddress(form.getEmail());
		if (dupulicateUser != null) {
			result.rejectValue("email", "", "既に登録済みのメールアドレスです。");
		}

		if (result.hasErrors()) {
			return toRegister();
		}

		User user = new User();
		// フォームからドメインにプロパティ値をコピー
		BeanUtils.copyProperties(form, user);
		registerUserService.insert(user);
		return "redirect:/login";
	}
}
