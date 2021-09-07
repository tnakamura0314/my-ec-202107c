package jp.co.example.ecommerce_c.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_c.repository.UserRepository;

/**
 * ユーザを削除するためのサービス.
 * 
 * @author fukushima
 *
 */
@Service
@Transactional
public class DeleteUserService {
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * ユーザを削除する.
	 * 
	 * @param id ID
	 */
	public void deleteById(Integer id) {
		userRepository.deleteById(id);
	}
}
