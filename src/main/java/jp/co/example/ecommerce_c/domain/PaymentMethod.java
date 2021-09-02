package jp.co.example.ecommerce_c.domain;

/**
 * 支払い方法を表す列挙型.
 * @author kanekojota
 *
 */
public enum PaymentMethod {
	
	//定数一覧
	CASH_ON_DELIVERY(1,"代金引換")
	,CREDIT_CARD(2, "クレジットカード");

	private final Integer key;
	private final String value;
	
	/**
	 * コンストラクタ.
	 */
	private PaymentMethod(Integer key, String value) {
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
	public static PaymentMethod of(Integer key) {
		for(PaymentMethod paymentMethod: PaymentMethod.values()) {
			if(paymentMethod.key == key) {
				return paymentMethod;
			}
		}
		throw new IndexOutOfBoundsException("The value of status doesn't exist;");
	}
}
