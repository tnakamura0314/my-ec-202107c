package jp.co.example.ecommerce_c.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.example.ecommerce_c.domain.Topping;

@Repository
public class ToppingRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Topping> TOPPING_ROW_MAPPER
		= (rs, i) -> {
			Topping topping = new Topping();
			topping.setId(rs.getInt("id"));
			topping.setName(rs.getString("name"));
			topping.setPriceM(rs.getInt("priceM"));
			topping.setPriceL(rs.getInt("priceL"));
			return topping;
		};
}
