package jp.co.example.ecommerce_c.repository;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import jp.co.example.ecommerce_c.domain.UserOrder;

/**
 * oudersとusersテーブルを操作するリポジトリ.
 * 
 * @author nakamuratomoya
 *
 */
@Repository
public class OrderUserRepository {

	// カンマ
	private static final String COMMA = ",";
	// 改行
	private static final String NEW_LINE = "\n";
	
	/**
	 * orderとuserの情報をcsvに出力する.
	 */
	public void findByUserAndOrder(){
		
		try {
			// インストールしたpostgreSQLのドライバを指定
			Class.forName("org.postgresql.Driver");

			// postgreSQLデータベースに接続 (DB名,ID,パスワードを指定)
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ec-202107c", "postgres",
					"postgres");

			// ステートメントを作成
			PreparedStatement stmts = conn.prepareStatement(
					"SELECT u.id u_id, u.name u_name, u.email u_email, u.password u_password, u.zipcode u_zipcode, u.address u_address, u.telephone u_telephone, o.id o_id, o.user_id o_user_id, o.status o_status, o.total_price o_total_price, o.order_date o_order_date, o.destination_name o_destination_name, o.destination_email o_destination_email, o.destination_zipcode o_destination_zipcode, o.destination_address o_destination_address, o.destination_tel o_destination_tel, o.delivery_time o_delivery_time, o.payment_method o_payment_method FROM users AS u LEFT OUTER JOIN orders AS o ON u.id = o.user_id ORDER BY u.id ;");

			ResultSet rs = stmts.executeQuery();

			// UserOrderクラスの空のListを作成
			List<UserOrder> userOrderList = new ArrayList<>();
			

			while (rs.next()) {
				UserOrder userOrder = new UserOrder();
				userOrder.setId(rs.getInt("u_id"));
				userOrder.setName(rs.getString("u_name"));
				userOrder.setEmail(rs.getString("u_email"));
				userOrder.setPassword(rs.getString("u_password"));
				userOrder.setZipcode(rs.getString("u_zipcode"));
				userOrder.setAddress(rs.getString("u_address"));
				userOrder.setTelephone(rs.getString("u_telephone"));
				userOrder.setOrderId(rs.getInt("o_id"));
				userOrder.setUserId(rs.getInt("o_user_id"));
				userOrder.setStatus(rs.getInt("o_status"));
				userOrder.setTotalPrice(rs.getInt("o_total_price"));
				userOrder.setOrderDate(rs.getDate("o_order_date"));
				userOrder.setDestinationName(rs.getString("o_destination_name"));
				userOrder.setDestinationEmail(rs.getString("o_destination_email"));
				userOrder.setDestinationZipCode(rs.getString("o_destination_zipcode"));
				userOrder.setDestinationAddress(rs.getString("o_destination_address"));
				userOrder.setDestinationTel(rs.getString("o_destination_tel"));
				userOrder.setDeliveryTime(rs.getTimestamp("o_delivery_time"));
				userOrder.setPaymentMethod(rs.getInt("o_payment_method"));
				userOrderList.add(userOrder);

			}
			
			System.out.println(userOrderList);

			FileWriter fileWriter = null;

			try {
				
				LocalDate localDate = LocalDate.now();
				
				fileWriter = new FileWriter("user_order" + localDate + ".csv");

				fileWriter.append("id");
				fileWriter.append(COMMA);
				fileWriter.append("name");
				fileWriter.append(COMMA);
				fileWriter.append("email");
				fileWriter.append(COMMA);
				fileWriter.append("password");
				fileWriter.append(COMMA);
				fileWriter.append("zipcode");
				fileWriter.append(COMMA);
				fileWriter.append("address");
				fileWriter.append(COMMA);
				fileWriter.append("telephone");
				fileWriter.append(COMMA);
				fileWriter.append("id");
				fileWriter.append(COMMA);
				fileWriter.append("user_id");
				fileWriter.append(COMMA);
				fileWriter.append("status");
				fileWriter.append(COMMA);
				fileWriter.append("total_price");
				fileWriter.append(COMMA);
				fileWriter.append("order_date");
				fileWriter.append(COMMA);
				fileWriter.append("destination_name");
				fileWriter.append(COMMA);
				fileWriter.append("destination_email");
				fileWriter.append(COMMA);
				fileWriter.append("destination_zipcode");
				fileWriter.append(COMMA);
				fileWriter.append("destination_address");
				fileWriter.append(COMMA);
				fileWriter.append("destination_tel");
				fileWriter.append(COMMA);
				fileWriter.append("delivery_time");
				fileWriter.append(COMMA);
				fileWriter.append("payment_method");
				fileWriter.append(NEW_LINE);

				// リストの内容を順に処理
				for (UserOrder userOrder : userOrderList) {

					fileWriter.append(String.valueOf(userOrder.getId()));
					fileWriter.append(COMMA);
					fileWriter.append(userOrder.getName());
					fileWriter.append(COMMA);
					fileWriter.append(userOrder.getEmail());
					fileWriter.append(COMMA);
					fileWriter.append(userOrder.getPassword());
					fileWriter.append(COMMA);
					fileWriter.append(userOrder.getZipcode());
					fileWriter.append(COMMA);
					fileWriter.append(userOrder.getAddress());
					fileWriter.append(COMMA);
					fileWriter.append(userOrder.getTelephone());
					fileWriter.append(COMMA);
					fileWriter.append(String.valueOf(userOrder.getOrderId()));
					fileWriter.append(COMMA);
					fileWriter.append(String.valueOf(userOrder.getUserId()));
					fileWriter.append(COMMA);
					fileWriter.append(String.valueOf(userOrder.getStatus()));
					fileWriter.append(COMMA);
					fileWriter.append(String.valueOf(userOrder.getTotalPrice()));
					fileWriter.append(COMMA);
					fileWriter.append(String.valueOf(userOrder.getOrderDate()));
					fileWriter.append(COMMA);
					fileWriter.append(userOrder.getDestinationName());
					fileWriter.append(COMMA);
					fileWriter.append(userOrder.getDestinationEmail());
					fileWriter.append(COMMA);
					fileWriter.append(userOrder.getDestinationZipCode());
					fileWriter.append(COMMA);
					fileWriter.append(userOrder.getDestinationAddress());
					fileWriter.append(COMMA);
					fileWriter.append(userOrder.getDestinationTel());
					fileWriter.append(COMMA);
					fileWriter.append(String.valueOf(userOrder.getDeliveryTime()));
					fileWriter.append(COMMA);
					fileWriter.append(String.valueOf(userOrder.getPaymentMethod()));
					fileWriter.append(NEW_LINE);
					
					

				}
				
				System.out.println("CSVファイル出力完了");

			} catch (Exception e) {
				e.printStackTrace();
			} finally {

				try {
					fileWriter.flush();
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
