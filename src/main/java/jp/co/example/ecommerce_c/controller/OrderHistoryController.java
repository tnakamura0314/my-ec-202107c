package jp.co.example.ecommerce_c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_c.service.OrderHistoryService;

/**
 * 商品注文履歴を操作するコントローラー.
 * 
 * @author nakamuratomoya
 *
 */
@Controller
@RequestMapping("/orderHistory")
public class OrderHistoryController {
	
	@Autowired
	private OrderHistoryService service;
	
	@RequestMapping("")
	public String showHistory() {
		return "/order_history";
	}

}
