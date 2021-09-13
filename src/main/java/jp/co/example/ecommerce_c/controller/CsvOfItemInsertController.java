package jp.co.example.ecommerce_c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_c.service.CsvOfItemInsertService;

/**
 * csvからitemsテーブルにinsertするコントローラー.
 * 
 * @author nakamuratomoya
 *
 */
@Controller
@RequestMapping("/csv-db")
public class CsvOfItemInsertController {
	
	@Autowired
	private CsvOfItemInsertService service;
	
	@RequestMapping("")
	public String ItemInsert() {
		
	service.ItemInsert();
	
	return "redirect:/";
		
	}

}
