package cn.icloudit.service.impl;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.icloudit.common.api.ShopResult;
import cn.icloudit.common.redis.BaseRedisService;
import cn.icloudit.dao.PaymentInfoDao;
import cn.icloudit.entity.PaymentInfo;
import cn.icloudit.service.PaymentInfoService;
import cn.icloudit.utils.DateUtils;

@RestController
@RequestMapping("/pay")
public class PaymentInfoServiceImpl  implements PaymentInfoService {
	@Autowired
	private PaymentInfoDao paymentInfoDao;
	@Autowired
	private BaseRedisService baseRedisService;
	

	@Override
	@RequestMapping("/addPayInfoToken")
	public ShopResult addPayInfoToken(@RequestBody PaymentInfo paymentInfo) {
		paymentInfo.setCreated(DateUtils.getTimestamp());
		paymentInfo.setUpdated(DateUtils.getTimestamp());
		paymentInfoDao.savePaymentType(paymentInfo);
		Integer paymentInfoId = paymentInfo.getId();
		if (paymentInfoId == null) {
			return ShopResult.build(400,"系统错误,没有获取到支付id");
		}
		String token = UUID.randomUUID().toString();
		baseRedisService.setString(token, paymentInfoId + "");
		return  ShopResult.ok(token);
	}

	@Override
	@RequestMapping("/getPayInfoToken")
	public ShopResult getPayInfoToken(@RequestParam("token") String token) {
		if (StringUtils.isEmpty(token)) {
			return ShopResult.build(400,"token不能为空!");
		}
		String payInfoId = baseRedisService.get(token);
		Long newPayInfoId = Long.parseLong(payInfoId);
		PaymentInfo paymentInfo = paymentInfoDao.getPaymentInfo(newPayInfoId);
		return  ShopResult.ok(paymentInfo);
	}

	@Override
	@RequestMapping("/getByOrderIdPayInfo")
	public ShopResult getByOrderIdPayInfo(@RequestParam("orderId")String orderId) {
		PaymentInfo paymentInfo = paymentInfoDao.getByOrderIdPayInfo(orderId);
		if(paymentInfo==null){
			return ShopResult.build(400,"未查询到支付信息");
		}
		return ShopResult.ok(paymentInfo);
	}

	@Override
	@RequestMapping("/updatePayInfo")
	public ShopResult updatePayInfo(@RequestBody PaymentInfo paymentInfo) {
		paymentInfoDao.updatePayInfo(paymentInfo);
		return ShopResult.ok();
	}

}
