package jp.co.example.ecommerce_c.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.example.ecommerce_c.repository.OrderUserRepository;

/**
 * orderとuserを操作するサービス.
 * 
 * @author nakamuratomoya
 *
 */
@Service
public class OrderUserService {
	
	@Autowired
	private OrderUserRepository repository;
	
	
	/**
	 * orderとuserの情報をcsvに出力する.
	 * 
	 */
	public void CreateCsv() {
		
		repository.findByUserAndOrder();
		
	}

}
