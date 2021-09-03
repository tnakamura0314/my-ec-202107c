package jp.co.example.ecommerce_c.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.example.ecommerce_c.domain.Item;
import jp.co.example.ecommerce_c.repository.ItemRepository;

/**
 * 商品情報をソート操作するサービス.
 * 
 * @author yuyayokoyama
 *
 */
@Service
public class SortService {
	
	@Autowired
	private ItemRepository repository;
	
	/**
	 * 商品情報を全件検索する.
	 * 
	 * @return 全件の商品
	 */
	public List<Item> showItemList(){
		List<Item> itemList = repository.findAll();
		return itemList;
	}
	
	/**
	 * 商品情報を値段の昇順で表示する.
	 * @param price_m Mの値段
	 * @return 値段の昇順で並び替えられた商品情報
	 */
	public List<Item> priceSortAsc(){
		List<Item> itemList = repository.findByPriceAsc();
		return itemList;
	}
	
	
	/**
	 * 商品情報を値段の降順で表示する.
	 * @param price_m Mの値段
	 * @return 値段の降順で並び替えられた商品情報
	 */
	public List<Item> priceSortDesc(){
		List<Item> itemList = repository.findByPriceDesc();
		return itemList;
	}
	
	/**
	 * 商品情報を名前の昇順で表示する.
	 * @param name 商品名
	 * @return 商品名の昇順で並び替えられた商品情報
	 */
	public List<Item> nameSortAsc(){
		List<Item> itemList = repository.findByNameAsc();
		return itemList;
	}
	
	/**
	 * 商品情報を名前の降順で表示する.
	 * @param name 商品名
	 * @return 商品名の降順で並び替えられた商品情報
	 */
	public List<Item> nameSortDesc(){
		List<Item> itemList = repository.findByNameDesc();
		return itemList;
	}

}
