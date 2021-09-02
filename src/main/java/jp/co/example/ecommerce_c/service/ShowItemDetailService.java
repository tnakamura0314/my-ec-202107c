package jp.co.example.ecommerce_c.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_c.domain.Item;
import jp.co.example.ecommerce_c.repository.ItemRepository;
import jp.co.example.ecommerce_c.repository.ToppingRepository;

/**
 * 商品詳細情報を表示するサービス.
 * 
 * @author fukushima
 *
 */
@Service
@Transactional
public class ShowItemDetailService {

	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ToppingRepository toppingRepository;
	
	/**
	 * 商品情報を取得します.
	 * 
	 * @param id 商品ID
	 * @return 商品情報
	 */
	public Item showDetail(Integer id) {
		Item item = itemRepository.findById(id);
		item.setToppingList(toppingRepository.findAll());
		return item;
	}
}
