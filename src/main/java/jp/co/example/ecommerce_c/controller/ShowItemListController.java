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
	@RequestMapping("")
	public String priceSortList(String sort, Model model) {

		// 名前の昇順
		if (sort.equals("1")) {

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

			// 金額の降順
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
