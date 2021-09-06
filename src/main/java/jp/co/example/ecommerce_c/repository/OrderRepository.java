package jp.co.example.ecommerce_c.repository;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import jp.co.example.ecommerce_c.domain.Item;
import jp.co.example.ecommerce_c.domain.Order;
import jp.co.example.ecommerce_c.domain.OrderItem;
import jp.co.example.ecommerce_c.domain.OrderTopping;
import jp.co.example.ecommerce_c.domain.Topping;

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
	 * @param userID ユーザーID
	 * @return serIdとstatusが0の全件注文情報
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

	/**
	 * Orderオブジェクトを生成するローマッパー(コメントと結合).
	 * 
	 */
	private static final ResultSetExtractor<Order> ARTICLE_RESULT_SET_EXTRATOR = (rs) -> {
		// orderオブジェクトを作成
		Order order = new Order();
		// 各情報の格納用リストの作成/
		List<OrderItem> orderItemList = null;
		List<OrderTopping> orderToppingList = null;
		List<Topping> toppingList = null;

		// 格納するオブジェクトの作成
		OrderItem orderItem = null;
		OrderTopping orderTopping = null;
		Item item = null;
		// 初期変数
		int beforeOrderId = 0;
		int beforeOrderItemId = 0;
		int beforeOrderToppingId = 0;
		int beforeItemId = 0;
		int beforeToppingId = 0;

		while (rs.next()) {
			int nowOrderId = rs.getInt("o_id");
			int nowOrderItemId = rs.getInt("i_id");
			int nowOrderToppingId = rs.getInt("ot_topping_id");
			int nowItemId = rs.getInt("oi_item_id");
			int nowToppingId = rs.getInt("t_id");

			if (nowOrderId != beforeOrderId) {
				order.setId(rs.getInt("o_id"));
				order.setUserId(rs.getInt("o_user_id"));
				order.setStatus(rs.getInt("o_status"));
				order.setTotalPrice(rs.getInt("o_total_price"));
				order.setOrderDate(rs.getDate("o_order_date"));
				order.setDestinationName(rs.getString("o_destination_name"));
				order.setDestinationEmail(rs.getString("o_destination_email"));
				order.setDestinationZipCode(rs.getString("o_destination_zipcode"));
				order.setDestinationAddress(rs.getString("o_destination_address"));
				order.setDestinationTel(rs.getString("o_destination_tel"));
				order.setDeliveryTime(rs.getTimestamp("o_delivery_time"));
				order.setPaymentMethod(rs.getInt("o_payment_method"));
				orderItemList = new ArrayList<OrderItem>();
				order.setOrderItemList(orderItemList);
			}

			if (rs.getInt("oi_id") != 0) {
				if (nowOrderItemId != beforeOrderItemId) {
					orderItem = new OrderItem();
					orderItem.setId(rs.getInt("oi_id"));
					orderItem.setItemId(rs.getInt("oi_item_id"));
					orderItem.setOrderId(rs.getInt("oi_order_id"));
					orderItem.setQuantity(rs.getInt("oi_quantity"));
					orderItem.setReceiveStringSize(rs.getString("oi_size"));
					orderItem.setItem(item);
					orderToppingList = new ArrayList<OrderTopping>();
					orderItem.setOrderToppingList(orderToppingList);
					orderItemList.add(orderItem);
					orderToppingList = new ArrayList<OrderTopping>();
					orderItem.setOrderToppingList(orderToppingList);
				}

				if (nowItemId != beforeItemId) {
					item = new Item();
					item.setId(rs.getInt("i_id"));
					item.setName(rs.getString("i_name"));
					item.setDescription(rs.getString("i_description"));
					item.setPriceM(rs.getInt("i_price_m"));
					item.setPriceL(rs.getInt("i_price_l"));
					item.setImagePath(rs.getString("i_image_path"));
					item.setDeleted(rs.getBoolean("i_deleted"));
					toppingList = new ArrayList<Topping>();
					item.setToppingList(toppingList);
					orderItem.setItem(item);
				}

				if (rs.getInt("ot_id") != 0) {
					Topping topping = new Topping();
					if (nowOrderToppingId != beforeOrderToppingId) {
						orderTopping = new OrderTopping();
						orderTopping.setId(rs.getInt("ot_id"));
						orderTopping.setToppingId(rs.getInt("ot_topping_id"));
						orderTopping.setOrderItemId(rs.getInt("ot_order_item_id"));
						orderTopping.setTopping(topping);
						orderToppingList.add(orderTopping);
					}

					if (nowToppingId != beforeToppingId) {
						topping.setId(rs.getInt("t_id"));
						topping.setName(rs.getString("t_name"));
						topping.setPriceM(rs.getInt("t_price_m"));
						topping.setPriceL(rs.getInt("t_price_l"));
						toppingList.add(topping);
					}
				}
			}
			beforeOrderId = nowOrderId;
			beforeOrderItemId = nowOrderItemId;
			beforeOrderToppingId = nowOrderToppingId;
			beforeItemId = nowItemId;
			beforeToppingId = nowToppingId;

		}
		return order;
	};

	/**
	 * 主キー検索を行う.
	 * 
	 * @param id 検索すつ注文id
	 * @return 検索された注文情報
	 */
	public Order load(Integer orderId) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT id,user_id,status,total_price,order_date,destination_name,destination_email,destination_zipcode,");
		sql.append("destination_address,destination_tel,delivery_time,payment_method");
		sql.append(" FROM orders WHERE id=:orderId");
		SqlParameterSource param = new MapSqlParameterSource().addValue("orderId", orderId);
		Order order = template.queryForObject(sql.toString(), param, ORDER_ROW_MAPPER);
		return order;
	}

	/**
	 * 渡した注文情報を更新する.
	 * 
	 * @param Order 更新する注文情報
	 */
	public void update(Order order) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE orders SET user_id=:userId,status=:status,total_price=:totalPrice,order_date=:orderDate,");
		sql.append(
				"destination_name=:destinationName,destination_email=:destinationEmail,destination_zipcode=:destinationZipCode,");
		sql.append(
				"destination_address=:destinationAddress,destination_tel=:destinationTel,delivery_time=:deliveryTime,payment_method=:paymentMethod");
		sql.append(" WHERE id=:id");
		template.update(sql.toString(), param);

	}
	/**
	 * 渡した注文の合計金額を更新する.
	 * 
	 * @param Order 更新する注文情報
	 */
	public void updateTotalPrice(Integer id,Integer totalPrice) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE orders SET total_price=:totalPrice WHERE id=:id");
		SqlParameterSource param = new MapSqlParameterSource().addValue("id",id).addValue("totalPrice", totalPrice);
		template.update(sql.toString(), param);
	}

	/**
	 * 注文情報を挿入する.
	 * @param order 注文情報
	 * @return
	 */
	public Order insert(Order order) {
		String sql = "insert into orders (user_id,status,total_price) values(:userId,:status,:totalPrice);";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", order.getUserId())
				.addValue("status", order.getStatus()).addValue("totalPrice", order.getTotalPrice());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String[] keyColumnNames = { "id" };
		template.update(sql, param, keyHolder, keyColumnNames);
		order.setId(keyHolder.getKey().intValue());
		return order;
	}

	public Order findByUserIdAndStatus(Integer userId, Integer status) {

		String sql = "SELECT o.id o_id, o.user_id o_user_id, o.status o_status, o.total_price o_total_price, o.order_date o_order_date, o.destination_name o_destination_name, o.destination_email o_destination_email, o.destination_zipcode o_destination_zipcode, o.destination_address o_destination_address, o.destination_tel o_destination_tel, o.delivery_time o_delivery_time, o.payment_method o_payment_method"
				+ ", oi.id oi_id, oi.item_id oi_item_id, oi.order_id oi_order_id, oi.quantity oi_quantity, oi.size oi_size"
				+ ", ot.id ot_id, ot.topping_id ot_topping_id, ot.order_item_id ot_order_item_id "
				+ ", i.id i_id, i.name i_name, i.description i_description, i.price_m i_price_m, i.price_l i_price_l, i.image_path i_image_path, i.deleted i_deleted"
				+ ", t.id t_id, t.name t_name, t.price_m t_price_m, t.price_l t_price_l"
				+ " FROM orders AS o LEFT OUTER JOIN order_items AS oi ON o.id = oi.order_id LEFT OUTER JOIN order_toppings AS ot ON oi.id = ot.order_item_id LEFT OUTER JOIN items AS i ON oi.item_id = i.id LEFT OUTER JOIN toppings AS t ON ot.topping_id = t.id"
				+ " WHERE o.user_id=:userID AND o.status=:status ORDER BY oi_id DESC;";

		SqlParameterSource param = new MapSqlParameterSource().addValue("userID", userId).addValue("status", status);

		Order order = template.query(sql, param, ARTICLE_RESULT_SET_EXTRATOR);

		return order;
	}

}
