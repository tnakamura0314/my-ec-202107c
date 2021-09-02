package jp.co.example.ecommerce_c.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_c.domain.Order;
import jp.co.example.ecommerce_c.service.ShowOrderConfirmationService;

@Controller
@RequestMapping("/showOrder")
public class ShowOrderConfirmationController {
	
	@Autowired
	private ShowOrderConfirmationService service;
	
	
	@RequestMapping("/showOrderConfirmation")
	public String showOrderConfirmation(Integer userID, Model model) {
		
		List<Order> orderList = service.showOrderConfirmation(userID);
		
		model.addAttribute("orderList", orderList);
		
		return "/order_confirm.html";
		
	}

}
