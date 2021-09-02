package jp.co.example.ecommerce_c.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.example.ecommerce_c.domain.Topping;

/**
 * toppingsテーブルを操作するリポジトリ.
 *
 * @author fukushima, nakamuratomoya
 *
 */
@Repository
public class ToppingRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * Toppingオブジェクトを生成するローマッパー.
	 */
	private static final RowMapper<Topping> TOPPING_ROW_MAPPER
		= (rs, i) -> {
			Topping topping = new Topping();
			topping.setId(rs.getInt("id"));
			topping.setName(rs.getString("name"));
			topping.setPriceM(rs.getInt("price_m"));
			topping.setPriceL(rs.getInt("price_l"));
			return topping;
		};

	/**
	 * トッピング一覧情報をID順で取得します.
	 *
	 * @return 全トッピングリスト
	 */
	public List<Topping> findAll() {
		String sql = "SELECT id, name, price_m, price_l FROM toppings ORDER BY id;";
		List<Topping> toppingList = template.query(sql, TOPPING_ROW_MAPPER);
		return toppingList;
	}
	
	/**
	 * 主キー検索を行う.
	 * 
	 * @param id 主キー
	 * @return 検索したトッピングの情報
	 */
	public Topping load(int id) {
		String sql = "SELECT id,name,price_m,price_l FROM toppings where id = :id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Topping topping = template.queryForObject(sql, param, TOPPING_ROW_MAPPER);
		return topping;
	}
}
