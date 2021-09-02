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

}
