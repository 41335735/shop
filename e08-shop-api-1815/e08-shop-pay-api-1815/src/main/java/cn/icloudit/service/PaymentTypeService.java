package cn.icloudit.service;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.icloudit.common.api.ShopResult;

@RequestMapping("/pay")
public interface PaymentTypeService {
	@RequestMapping("/getPaymentType")
	public ShopResult getPaymentType(@RequestParam("id") Long id);

}
