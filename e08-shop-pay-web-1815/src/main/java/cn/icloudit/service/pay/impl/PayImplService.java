package cn.icloudit.service.pay.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.icloudit.common.api.ShopResult;
import cn.icloudit.entity.PaymentInfo;
import cn.icloudit.entity.PaymentType;
import cn.icloudit.feign.PaymentTypeFeign;
import cn.icloudit.service.pay.PayAdaptService;
import cn.icloudit.service.pay.PayService;
import cn.icloudit.service.pay.yinlian.YinLianPay;
import cn.icloudit.utils.ResultUils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public   class PayImplService implements PayService {
	@Autowired
	private PaymentTypeFeign paymentTypeFeign;
	@Autowired
    private YinLianPay yinLianPay;
	@Override
	public String pay(PaymentInfo paymentInfo) {
		Long typeId = paymentInfo.getTypeId();
		ShopResult paymentTypeMap = paymentTypeFeign.getPaymentType(typeId);
		if (paymentTypeMap == null) {
			log.error("####pay() typeId:{},paymentTypeMap is null");
			return null;
		}
		Map<String, Object> data = (Map<String, Object>) ResultUils.getResultMap(paymentTypeMap);
		String json = new JSONObject().toJSONString(data);
		PaymentType paymentType = new JSONObject().parseObject(json, PaymentType.class);
		if (paymentType == null) {
			return null;
		}
		String typeName = paymentType.getTypeName();
		PayAdaptService payAdaptService=null;
		switch (typeName) {
		case "yinlianPay":
			payAdaptService=yinLianPay;
			break;

		default:
			break;
		}
		return payAdaptService.pay(paymentInfo, paymentType);
	}

}
