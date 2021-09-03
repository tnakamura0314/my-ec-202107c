package jp.co.example.ecommerce_c.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_c.domain.Item;
import jp.co.example.ecommerce_c.form.SortForm;
import jp.co.example.ecommerce_c.service.SortService;

@Controller
@RequestMapping("/sort")
public class SortController {
	
	@Autowired
	private SortService service;	
	
	@ModelAttribute
	public SortForm setUpForm() {
		return new SortForm();
	}
	
	
	

	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("")
	public String priceSortList(String sort, Model model) {
		
		
		
		// 名前の昇順
		if(sort.equals("1")) {
			
			
			List<List<Item>> itemList3 = new ArrayList<>();
			List<Item> itemList2 = new ArrayList<>();
			List<Item> itemList = service.nameSortAsc(sort);
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
			return "/item_list_toy";
		
			// 名前の降順
		} else if (sort.equals("2")) {
			List<List<Item>> itemList3 = new ArrayList<>();
			List<Item> itemList2 = new ArrayList<>();
			List<Item> itemList = service.nameSortDesc(sort);
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
			return "/item_list_toy";
			
			// 金額の昇順
		} else if (sort.equals("3")) {
			List<List<Item>> itemList3 = new ArrayList<>();
			List<Item> itemList2 = new ArrayList<>();
			List<Item> itemList = service.priceSortAsc(sort);
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
			return "/item_list_toy";
			
			//金額の降順
		} else if (sort.equals("4")) {
			List<List<Item>> itemList3 = new ArrayList<>();
			List<Item> itemList2 = new ArrayList<>();
			List<Item> itemList = service.priceSortDesc(sort);
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
		}
		return "/item_list_toy";
	}
}



