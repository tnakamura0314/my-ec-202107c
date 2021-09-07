package jp.co.example.ecommerce_c.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * ログインをします.
	 * 
	 * @param email メールアドレス
	 * @param password　パスワード
	 * @return
	 */
	public User login(String email, String password ) {

		User user = userRepository.findByMailAddress(email);
		if(user == null) {
			return null;
		}
		
		if (passwordEncoder.matches(password, user.getPassword())) {

			return user;

		}
		
		return null;
	}

}
