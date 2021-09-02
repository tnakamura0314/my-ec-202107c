package jp.co.example.ecommerce_c.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_c.domain.User;
import jp.co.example.ecommerce_c.repository.UserRepository;

/**
 * 利用者情報を操作するサービス.
 * 
 * @author yuyayokoyama
 *
 */
@Service
@Transactional
public class LoginService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User login(String email, String password) {
		User user = userRepository.findByMailAddressAndPassword(email, password);
		return user;
	}

}
