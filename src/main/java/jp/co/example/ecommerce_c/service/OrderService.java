package jp.co.example.ecommerce_c.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

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

	/**
	 * 注文情報を更新する.
	 * 
	 * @param order 更新する注文情報
	 */
	public void update(OrderForm form,Model model) {
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
			if(diff < 3) {
				model.addAttribute("timeErrorMessage", "今から3時間後の日時をご入力ください");
				return;
			}
			
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
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
