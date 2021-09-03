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
	public List<Item> priceSortAsc(Integer price_m){
		List<Item> itemList = repository.findByPriceAsc(price_m);
		return itemList;
	}
	
	
	/**
	 * 商品情報を値段の降順で表示する.
	 * @param price_m Mの値段
	 * @return 値段の降順で並び替えられた商品情報
	 */
	public List<Item> priceSortDesc(Integer price_m){
		List<Item> itemList = repository.findByPriceDesc(price_m);
		return itemList;
	}
	
	
	public List<Item> nameSortAsc(String name){
		List<Item> itemList = repository.findByNameAsc(name);
		return itemList;
	}

}
