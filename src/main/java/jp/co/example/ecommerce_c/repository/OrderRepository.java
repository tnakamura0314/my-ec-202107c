package jp.co.example.ecommerce_c.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.example.ecommerce_c.domain.Order;

/**
 * Orderテーブルを操作するリポジトリ.
 * @author kanekojota
 *
 */
@Repository
public class OrderRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * Articleオブジェクトを生成するローマッパー.
	 */
	private static final RowMapper<Order> ORDER_ROW_MAPPER = new BeanPropertyRowMapper<>(Order.class);
}
