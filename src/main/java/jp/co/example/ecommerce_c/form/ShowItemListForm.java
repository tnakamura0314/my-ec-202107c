package jp.co.example.ecommerce_c.form;

/**
 * 名前で曖昧検索する時に使用するフォーム.
 * 
 * @author nakamuratomoya
 *
 */
public class ShowItemListForm {

	/** 商品検索名 */
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ShowItemListForm [name=" + name + "]";
	}

}
