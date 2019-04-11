package cn.icloudit.service.pay;

import cn.icloudit.entity.PaymentInfo;
import cn.icloudit.entity.PaymentType;

public interface PayAdaptService {
	public String pay(PaymentInfo paymentInfo,PaymentType paymentType );
}
