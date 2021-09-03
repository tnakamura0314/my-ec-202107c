package jp.co.example.ecommerce_c.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_c.domain.Item;
import jp.co.example.ecommerce_c.form.SortForm;
import jp.co.example.ecommerce_c.service.ShowItemListService;

@Controller
@RequestMapping("/sort")
public class SortController {
	
	@Autowired
	private ShowItemListService service;
	
	@ModelAttribute
	public SortForm setUpForm() {
		return new SortForm();
	}
	
	
	

	@RequestMapping("/priceSortList")
	public String priceSortList(Model model) {
		
		List<List<Item>> itemList3 = new ArrayList<>();
		List<Item> itemList2 = new ArrayList<>();
		List<Item> itemList = service.showItemList();
		int roopCount = 0;

		for (Item item : itemList) {

			itemList2.add(item);
			roopCount++;

			if (roopCount % 3 == 0) {
				itemList3.add(itemList2);
				itemList2 = new ArrayList<>();
			}
			
		}
		model.addAttribute("itemList3", itemList3);
		
		if ()
		
		
		return "/item_list_toy";

	}
}




