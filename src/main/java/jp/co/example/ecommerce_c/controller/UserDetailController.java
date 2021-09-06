package jp.co.example.ecommerce_c.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user-detail")
public class UserDetailController {

	/**
	 * ユーザー詳細ページを表示.
	 * 
	 * @return ユーザー詳細ページ
	 */
	@RequestMapping("")
	public String index() {
		return "/user_detail";

	}

}
