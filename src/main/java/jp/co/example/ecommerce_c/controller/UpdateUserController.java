package jp.co.example.ecommerce_c.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_c.domain.User;
import jp.co.example.ecommerce_c.form.RegisterUserForm;
import jp.co.example.ecommerce_c.repository.UserRepository;
import jp.co.example.ecommerce_c.service.ResisterUserService;
import jp.co.example.ecommerce_c.service.UpdateUserService;

/**
 * ユーザー情報の更新を行うコントローラー.
 * 
 * @author nakamuratomoya
 *
 */
@Controller
@RequestMapping("/update")
public class UpdateUserController {

	@Autowired
	private ResisterUserService registerUserService;

	@Autowired
	private UpdateUserService service;

	@Autowired
	private HttpSession sessison;
	
	@Autowired
	private UserRepository repository;

	@ModelAttribute
	public RegisterUserForm setUpForm() {
		RegisterUserForm form = new RegisterUserForm();
		User user = (User) sessison.getAttribute("user");
		form.setName(user.getName());
		form.setEmail(user.getEmail());
		form.setZipcode(user.getZipcode());
		form.setAddress(user.getAddress());
		form.setTelephone(user.getTelephone());
		form.setPassword(user.getPassword());
		form.setConfirmPassword(user.getPassword());
		return form;
	}

	@RequestMapping("")
	public String index() {
		return "update-user-info";

	}

	@RequestMapping("update-user")
	public String updateUser(@Validated RegisterUserForm form, BindingResult result, Model model) {

		// パスワード確認
		if (!(form.getConfirmPassword().equals(form.getPassword()))) {
			result.rejectValue("confirmPassword", "", "パスワードと確認用パスワードが不一致です。");
		}
		
		User user = (User) sessison.getAttribute("user");

		

		User user2 = repository.load(user.getId());
		
		if(!(user2.getEmail().equals(form.getEmail()))) {
			
			// メールアドレス重複チェック
			User dupulicateUser = registerUserService.findByMailAddress(form.getEmail());
			
			if (dupulicateUser != null )  {
				
				result.rejectValue("email", "", "既に登録済みのメールアドレスです。");
				
			}
			
		}
		

		if (result.hasErrors()) {
			return index();
		}

		
		// フォームからドメインにプロパティ値をコピー
		BeanUtils.copyProperties(form, user);
		System.out.println(user);
		service.updateUser(user);
		return "redirect:/user-detail";

	}

}
