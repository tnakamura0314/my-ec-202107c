package jp.co.example.ecommerce_c.form;

import java.util.List;

public class AddCartForm {
	/** 商品ID */
	private String itemId;
	/** 商品個数 */
	private String quantity;
	/** サイズ */
	private String size;
	/** トッピング数を格納 */
	private List<String> toppingList;

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public List<String> getToppingList() {
		return toppingList;
	}

	public void setToppingList(List<String> toppingList) {
		this.toppingList = toppingList;
	}

	public String getItemId() {
		return itemId;
	}
	public Integer getIntItemId() {
		return Integer.parseInt(itemId);
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getQuantity() {
		return quantity;
	}
	public Integer getIntQuantity() {
		return Integer.parseInt(quantity);
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "AddCartForm [itemId=" + itemId + ", quantity=" + quantity + ", size=" + size + ", toppingList="
				+ toppingList + "]";
	}
}
