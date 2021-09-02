package jp.co.example.ecommerce_c.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
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
	
	public Order insert(Order order) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		String sql = "INSERT INTO orders(user_id, status, total_price, order_date, destination_name,"
				+ " destion_email, destination_zipcode, destination_address, destination_tel, delivery_time,"
				+ " payment_method) VALUES(:userId, :status, :totalPrice, :orderDate, :destinationName,"
				+ " :destionEmail, :destinationZipcode, :destinationAddress, :destinationTel, :deliveryTime,"
				+ " :paymentMethod);";
		
		template.update(sql, param);
		return order;
	}
	
	public Order findByUserIdAndStatus(Integer id, Integer status) {
		String sql = "SELECT id, name, user_id, status, total_price FROM orders"
				+ " WHERE id=:id AND status=:status;";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id).addValue("status", status);
		
		List<Order> OrderList = template.query(sql, param, XXX);
		if(OrderList.size() == 0) {
			return null;
		}

		return OrderList.get(0);
	}
}
