package jp.co.example.ecommerce_c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_c.service.OrderUserService;

/**
 * orderとuserを操作するコントローラー
 * 
 * @author nakamuratomoya
 *
 */
@Controller
@RequestMapping("create-csv")
public class OrderUserController {
	
	@Autowired
	private OrderUserService service;
	
	/**
	 * orderとuserの情報をcsvに出力する.
	 * 
	 * @return
	 */
	@RequestMapping("")
	public String CreateCsv() {
		
		service.CreateCsv();
		
		return "redirect:/";
		
	}

}
