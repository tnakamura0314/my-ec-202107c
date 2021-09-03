package jp.co.example.ecommerce_c.form;

/**
 * ソートする際に使用するフォーム.
 * 
 * @author yuyayokoyama
 *
 */
public class SortForm {
	/** 商品名 */
	private String name;
	/** Mサイズの値段 */
	private Integer priceM;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPriceM() {
		return priceM;
	}

	public void setPriceM(Integer priceM) {
		this.priceM = priceM;
	}

	@Override
	public String toString() {
		return "SortForm [name=" + name + ", priceM=" + priceM + "]";
	}

}
