package jp.co.example.ecommerce_c.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/add")
public class AddCartController {

	@RequestMapping("/addCart")
	public String addCart() {
		return "cart_list";
	}
}
