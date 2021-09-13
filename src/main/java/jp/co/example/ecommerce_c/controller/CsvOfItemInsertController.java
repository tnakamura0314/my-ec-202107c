package jp.co.example.ecommerce_c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
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
@Component
public class CsvOfItemInsertController {
	
	@Autowired
	private CsvOfItemInsertService service;
	
	@RequestMapping("")
	//定期的に処理を実行する処理（application.javaに@EnableSchedulingを追加してる）
//	@Scheduled(cron = "0 1 * * * *", zone = "Asia/Tokyo")
	public String ItemInsert() {
		
	service.ItemInsert();
	
	return "redirect:/";
		
	}

}
