package jp.co.example.ecommerce_c.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 利用者情報を操作するコントローラー.
 * 
 * @author yuyayokoyama
 *
 */
@Controller
@RequestMapping("/logout")
public class LogoutController {
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping(value = "/toLogout")
	public String toLogout() {
		session.invalidate();
		return "item_list_toy";
	}

}
