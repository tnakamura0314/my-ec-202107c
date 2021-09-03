package jp.co.example.ecommerce_c.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_c.domain.Order;
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
	
	/**
	 * 注文履歴情報を表示する.
	 * 
	 * @param model requestスコープ
	 * @return　注文履歴情報(1件もなければメッセージ)
	 */
	@RequestMapping("")
	public String showHistory(Model model) {
		
		//後ほど実装します
//		List<Order> orderHistoryList = service.
//		
//		if(orderHistoryList == null) {
//			model.addAttribute("errorMessage", "検索結果が1件もありませんでした");
//			return "/order_history";
//		}
		
		return "/order_history";
	}

}
