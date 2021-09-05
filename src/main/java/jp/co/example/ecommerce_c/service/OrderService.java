package jp.co.example.ecommerce_c.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_c.domain.Order;
import jp.co.example.ecommerce_c.domain.PaymentMethod;
import jp.co.example.ecommerce_c.domain.Status;
import jp.co.example.ecommerce_c.form.OrderForm;
import jp.co.example.ecommerce_c.repository.OrderRepository;


/**
 * 注文情報を操作するサービス.
 * 
 * @author kanekojota
 *
 */
@Service
@Transactional
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private JavaMailSender sender;


	/**
	 * 注文情報を更新する.
	 * 
	 * @param order 更新する注文情報
	 */

	public boolean update(OrderForm form) {

		try {
			// 注文日の作成
			Date orderDay = new Date();
			Order order = orderRepository.load(form.getId());
			// フォームからドメインにプロパティ値をコピー
			BeanUtils.copyProperties(form, order);

			// フォームとドメインで型が異なるものをコピー
			String strDate = form.getDeliveryDate() + "-" + form.getDeliveryTime() + "-00-00";
			System.out.println(strDate);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			Date deliveryDate = simpleDateFormat.parse(strDate);
			Timestamp deliveryTime = new Timestamp(deliveryDate.getTime());

			long diff = deliveryDate.getTime() - orderDay.getTime();
			// 3時間＝10800秒→1.08e+7
			long limitHour = 10800000;
			if (diff <= limitHour) {
				System.out.println("時間差" + diff);
				return false;
			} else {
				order.setOrderDate(orderDay);
				order.setDeliveryTime(deliveryTime);
				order.setPaymentMethod(form.getIntPaymentMethod());

				// 代金引換の場合はステータスを「1:未入金」、クレジットの場合は「2:入金済」にする。
				if (form.getIntPaymentMethod() == PaymentMethod.CASH_ON_DELIVERY.getKey()) {
					order.setStatus(Status.NOT_PAYMENT.getKey());
				} else if (form.getIntPaymentMethod() == PaymentMethod.CREDIT_CARD.getKey()) {
					order.setStatus(Status.DEPOSITED.getKey());
				}
				orderRepository.update(order);
				//以下メール送信処理
				String content = createContent(order);
				sendMail(order.getDestinationEmail(),content);
				return true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 注文確定時にメール送信を行う.
	 * @param mailAdress 宛先メールアドレス
	 */
	public void sendMail(String mailAdress,String content) {
		MimeMessage message = sender.createMimeMessage();
		try {
			// 送信情報設定
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom("rakuraku-toy@rakus.com");
			helper.setTo(mailAdress);
			helper.setSubject("【ラクラクトイ】ご注文ありがとうございました。");
			helper.setText(content,true);
			// メール送信
			sender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}	
	
	/**
	 * メール本文を作成する.
	 * @param order 注文商品情報
	 * @return　メール本文内容
	 */
	private String createContent(Order order) {
		StringBuilder content = new StringBuilder();
		content.append("<html>"+order.getDestinationName()+"様<br>ご注文ありがとうございます。");
		content.append("ご注文情報は下記の通りです。商品の到着まで今しばらくおまちください。<hr>");
		content.append("ご注文日："+order.getOrderDate()+"<br>");
		content.append("請求金額："+order.getTotalPrice()+"円"+"<br>");
		content.append("メールアドレス："+order.getDestinationEmail()+"<br>");
		content.append("住所："+order.getDestinationZipCode()+" "+order.getDestinationAddress()+"<br>");
		content.append("電話番号："+order.getDestinationTel()+"<br>");
		content.append("お届け予定日："+order.getDeliveryTime()+"<br>");
		content.append("</html>");
		return content.toString();
	}
	
	
}
