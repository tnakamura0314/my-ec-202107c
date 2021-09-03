package jp.co.example.ecommerce_c.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.example.ecommerce_c.domain.Item;
import jp.co.example.ecommerce_c.repository.ItemRepository;

/**
 * 商品情報を操作するサービス.
 * 
 * @author nakamuratomoya
 *
 */
@Service
public class ShowItemListService {
	
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
	 * 商品情報を商品名で曖昧検索する.
	 * 
	 * @param name  商品名
	 * @return　検索名に該当する商品情報
	 */
	public List<Item> showItemNameList(String name){
		
		List<Item> itemList = repository.findByName(name);
		
		return itemList;
	}
	
	/**
	 * 商品情報を値段の昇順で表示する.
	 * @param price_m Mの値段
	 * @return 値段の昇順で並び替えられた商品情報
	 */
	public List<Item> priceSortAsc(String sort){
		List<Item> itemList = repository.findByPriceAsc(sort);
		return itemList;
	}
	
	
	/**
	 * 商品情報を値段の降順で表示する.
	 * @param price_m Mの値段
	 * @return 値段の降順で並び替えられた商品情報
	 */
	public List<Item> priceSortDesc(String sort){
		List<Item> itemList = repository.findByPriceDesc(sort);
		return itemList;
	}
	
	/**
	 * 商品情報を名前の昇順で表示する.
	 * @param name 商品名
	 * @return 商品名の昇順で並び替えられた商品情報
	 */
	public List<Item> nameSortAsc(String sort){
		List<Item> itemList = repository.findByNameAsc(sort);
		return itemList;
	}
	
	/**
	 * 商品情報を名前の降順で表示する.
	 * @param name 商品名
	 * @return 商品名の降順で並び替えられた商品情報
	 */
	public List<Item> nameSortDesc(String sort){
		List<Item> itemList = repository.findByNameDesc(sort);
		return itemList;
	}

}
