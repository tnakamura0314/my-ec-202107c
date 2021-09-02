package jp.co.example.ecommerce_c.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.example.ecommerce_c.domain.Order;

/**
 * Orderテーブルを操作するリポジトリ.
 * 
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
	
	/**
	 * userIdとstatusが0の注文を全件取得するメソッド
	 * 
	 * @param userID　ユーザーID
	 * @return　serIdとstatusが0の全件注文情報
	 */
	public List<Order> findByUserIdAndStatus(Integer userID) {
		
		String sql = "SELECT o.id o_id, o.user_id o_user_id, o.status o_status, o.total_price o_total_price, o.order_date o_order_date, o.destination_name o_destination_name, o.destination_email o_destination_email, o.destination_zipcode o_destination_zipcode, o.destination_address o_destination_address, o.destination_tel o_destination_tel, o.delivery_time o_delivery_time, o.payment_method o_payment_method"
				   + ", i.id i_id, i.item_id i_item_id, i.order_id i_order_id, i.quantity i_quantity, i.size i_size"
				   + ", t.id t_id, t.topping_id t_topping_id, t.order_item_id t_order_item_id "
				   + "FROM orders AS o LEFT OUTER JOIN order_items AS i ON o.user_id = i.id LEFT OUTER JOIN order_toppings AS t ON i.id = t.order_item_id "
				   + "WHERE o.user_id=:userID AND o.status=0;";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("userID", userID);
		
		List<Order> orderList = template.query(sql, param, ORDER_ROW_MAPPER);
		
		return orderList;
		
	}
}
