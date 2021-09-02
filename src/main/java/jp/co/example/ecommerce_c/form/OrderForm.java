package jp.co.example.ecommerce_c.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 注文時に使用するフォームクラス.
 * 
 * @author kanekojota
 *
 */
public class OrderForm {
	/** 注文id */
	private Integer id;
	/** 宛先氏名 */
	@NotBlank(message="名前を入力してください。")
	private String destinationName;
	/** 宛先Eメール */
	@NotBlank(message="メールアドレスを入力してください。")
	@Email(message = "Emailの形式が不正です")
	private String destinationEmail;
	/** 宛先郵便番号 */
	@NotBlank(message="郵便番号を入力して下さい")
	@Pattern(regexp="^[0-9]{3}-[0-9]{4}$",message="郵便番号はXXX-XXXXの形式で入力してください")
	private String destinationZipCode;
	/** 宛先住所 */
	@NotBlank(message="住所を入力して下さい")
	private String destinationAddress;
	/** 宛先TEL */
	@NotBlank(message="電話番号を入力して下さい")
	@Pattern(regexp="^0\\d{2,3}-\\d{1,4}-\\d{4}$",message="電話番号はXXXX-XXXX-XXXXの形式で入力してください")
	private String destinationTel;
	@NotBlank(message="配達日時を入力して下さい")
	/** 配達日時 */
	private String deliveryDate;
	@NotBlank(message="配達日時を入力して下さい")
	/** 配達時間 */
	private String deliveryTime;
	/** 支払い方法 */
	private String paymentMethod;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public String getDestinationEmail() {
		return destinationEmail;
	}

	public void setDestinationEmail(String destinationEmail) {
		this.destinationEmail = destinationEmail;
	}

	public String getDestinationZipCode() {
		return destinationZipCode;
	}

	public void setDestinationZipCode(String destinationZipCode) {
		this.destinationZipCode = destinationZipCode;
	}

	public String getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public String getDestinationTel() {
		return destinationTel;
	}

	public void setDestinationTel(String destinationTel) {
		this.destinationTel = destinationTel;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}
	public Integer getIntDeliveryTime() {
		return Integer.parseInt(deliveryTime);
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}
	public Integer getIntPaymentMethod() {
		return Integer.parseInt(paymentMethod);
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Override
	public String toString() {
		return "OrderForm [id=" + id + ", destinationName=" + destinationName + ", destinationEmail=" + destinationEmail
				+ ", destinationZipCode=" + destinationZipCode + ", destinationAddress=" + destinationAddress
				+ ", destinationTel=" + destinationTel + ", deliveryDate=" + deliveryDate + ", deliveryTime="
				+ deliveryTime + ", paymentMethod=" + paymentMethod + "]";
	}

}
