package cn.icloudit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.icloudit.common.api.ShopResult;
import cn.icloudit.dao.PaymentTypeDao;
import cn.icloudit.entity.PaymentType;
import cn.icloudit.service.PaymentTypeService;

@Service
@RestController
@RequestMapping("/pay")
public class PaymentTypeServiceImpl implements PaymentTypeService {
	@Autowired
	private PaymentTypeDao paymentTypeDao;

	@RequestMapping("/getPaymentType")
	public ShopResult getPaymentType(@RequestParam("id") Long id) {
		PaymentType paymentType = paymentTypeDao.getPaymentType(id);
		if (paymentType == null) {
			return ShopResult.build(400,"未查找到类型");
		}
		return ShopResult.ok(paymentType);
	}

}
