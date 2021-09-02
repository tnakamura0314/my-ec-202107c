package jp.co.example.ecommerce_c.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import jp.co.example.ecommerce_c.domain.OrderItem;

/**
 * order_itemsテーブルを操作するリポジトリ.
 * 
 * @author nakamuratomoya
 *
 */
@Repository
public class OrderItemRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * OrderItemオブジェクトを生成するローマッパー.
	 */
	private static final RowMapper<OrderItem> ORDER_ITEM_ROW_MAPPER = (rs, i) -> {

		OrderItem orderItem = new OrderItem();

		orderItem.setId(rs.getInt("id"));
		orderItem.setItemId(rs.getInt("item_id"));
		orderItem.setOrderId(rs.getInt("order_id"));
		orderItem.setQuantity(rs.getInt("quantity"));
		//後で修正
//		orderItem.setSize(rs.getCharacterStream("size"));

		return orderItem;
	};
	
	public OrderItem insert(OrderItem orderItem) {
		String sql = "INSERT INTO order_items (item_id,order_id,quantity,size) VALUES (:itemId,:orderId,:quantity,:size);";
		SqlParameterSource param = new BeanPropertySqlParameterSource(orderItem);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String[] keyColumnName = { "id" };
		template.update(sql, param, keyHolder, keyColumnName);
		orderItem.setId(keyHolder.getKey().intValue());
		return orderItem;
	}

}
