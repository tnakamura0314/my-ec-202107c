package jp.co.example.ecommerce_c.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_c.domain.User;
import jp.co.example.ecommerce_c.repository.UserRepository;

/**
 * Usersテーブルを操作するサービス
 * 
 * @author kanekojota
 *
 */
@Service
@Transactional
public class ResisterUserService {

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;

	/**
	 * ユーザー登録を行う.
	 * 
	 * @param user ユーザー情報
	 */
	public void insert(User user) {
		
		String password = user.getPassword();
		String digest = passwordEncoder.encode(password);
		user.setPassword(digest);
		
		userRepository.insert(user);
	}

	/**
	 * メールアドレスからユーザー情報を取得します.
	 * 
	 * @param email メールアドレス
	 * @return ユーザー情報
	 */
	public User findByMailAddress(String email) {
		return userRepository.findByMailAddress(email);
	}
}
