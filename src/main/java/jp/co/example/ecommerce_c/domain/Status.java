package jp.co.example.ecommerce_c.domain;

/**
 * 注文状態を表す列挙型/
 * @author kanekojota
 *
 */
public enum Status {
	//定数一覧
	BOFORE_ORDER(0,"注文前")
	,NOT_PAYMENT(1, "入金前")
	,DEPOSITED(2, "入金済")
	,DELIVERED(3, "配送済")
	,DELIVERY_COMPLETE(4, "配送完了")
	,CANCEL_ORDER(9,"キャンセル");
	
	private final Integer key;
	private final String value;
	
	/**
	 * コンストラクタ.
	 */
	private Status(Integer key, String value) {
		this.key = key;
		this.value = value;
	}

	public Integer getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}
	
	/**
	 * keyの値からenumを取得
	 * @param key ステータス番号
	 * @return 渡されたステータスを含むenum
	 */
	public static Status of(Integer key) {
		for(Status status: Status.values()) {
			if(status.key == key) {
				return status;
			}
		}
		throw new IndexOutOfBoundsException("The value of status doesn't exist;");
	}
}
