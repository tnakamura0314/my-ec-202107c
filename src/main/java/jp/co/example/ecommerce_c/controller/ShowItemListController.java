package jp.co.example.ecommerce_c.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_c.domain.Item;
import jp.co.example.ecommerce_c.form.ShowItemListForm;
import jp.co.example.ecommerce_c.service.ShowItemListService;

/**
 * 商品情報を操作するコントローラー.
 * 
 * @author nakamuratomoya
 *
 */
@Controller
@RequestMapping("/showItem")
public class ShowItemListController {

	@Autowired
	private ShowItemListService service;

	@ModelAttribute
	public ShowItemListForm setUpForm() {
		return new ShowItemListForm();
	}

	/**
	 * 商品一覧画面を出力する.
	 * 
	 * @param model requestスコープ
	 * @return 商品一覧画面
	 */
	@RequestMapping("/showItemList")
	public String showItemList(Model model) {

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

		return "/item_list_toy";
	}

	/**
	 * 商品名で曖昧検索をする.
	 * 
	 * @param showItemListForm フォーム
	 * @param model            requestスコープ
	 * @return 検索名に該当する商品情報
	 */
	@RequestMapping("/showItemNameList")
	public String showItemNameList(ShowItemListForm showItemListForm, Model model) {

		if (showItemListForm.getName().equals("")) {
			model.addAttribute("errorMessage", "検索結果が1件もありませんでした");
			showItemList(model);
			return "/item_list_toy";
		}

		List<Item> itemList = service.showItemNameList(showItemListForm.getName());

		if (itemList == null) {
			model.addAttribute("errorMessage", "検索結果が1件もありませんでした");
			showItemList(model);
			return "/item_list_toy";
		}

		List<List<Item>> itemList3 = new ArrayList<>();
		List<Item> itemList2 = new ArrayList<>();
		int roopCount = 0;

		for (Item item : itemList) {

			itemList2.add(item);
			roopCount++;

			if (roopCount % 3 == 0) {
				itemList3.add(itemList2);
				itemList2 = new ArrayList<>();

			}

		}

		if (roopCount < 3) {
			itemList3.add(itemList2);
		}

		model.addAttribute("itemList3", itemList3);

		return "/item_list_toy";

	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/sort")
	public String priceSortList(String sort, Model model) {
		List<Item> itemList = null;
		if (sort.equals("1")) {
			itemList = service.nameSortAsc(sort);
		}else if(sort.equals("2")) {
			itemList = service.nameSortDesc(sort);
		}else if(sort.equals("3")) {
			itemList = service.priceSortAsc(sort);
		}else if(sort.equals("4")) {
			itemList = service.priceSortDesc(sort);	
		}

			List<List<Item>> itemList3 = new ArrayList<>();
			List<Item> itemList2 = new ArrayList<>();
			
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

		}
		

}
