package jp.co.example.ecommerce_c.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.example.ecommerce_c.repository.ItemRepository;

/**
 * csvからitemsテーブルにinsertするサービス.
 * 
 * @author nakamuratomoya
 *
 */
@Service
public class CsvOfItemInsertService {

	@Autowired
	private ItemRepository repository;

	public void ItemInsert() {
		
		repository.insert();

	}

}
