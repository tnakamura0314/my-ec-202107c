package jp.co.example.ecommerce_c.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_c.domain.User;
import jp.co.example.ecommerce_c.repository.UserRepository;

/**
 * ユーザー情報の更新を行うサービス.
 * 
 * @author nakamuratomoya
 *
 */
@Service
@Transactional
public class UpdateUserService {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository repository;
	
	/**
	 * ユーザー情報更新.
	 * @param form
	 */
	public void updateUser(User user) {
		
		String password = user.getPassword();
		String digest = passwordEncoder.encode(password);
		user.setPassword(digest);
		
		repository.update(user);
		
	}

}
