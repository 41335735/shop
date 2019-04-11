package cn.icloudit.service;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.icloudit.common.api.ShopResult;
import cn.icloudit.entity.PaymentInfo;

/**
 * 支付接口
 * 
 * @author Administrator
 *
 */
@RequestMapping("/pay")
public interface PaymentInfoService {

	/**
	 * 换取支付令牌
	 * 
	 * @return
	 */
	@RequestMapping("/addPayInfoToken")
	public ShopResult addPayInfoToken(@RequestBody PaymentInfo paymentInfo);

	/**
	 * 使用token查找支付信息
	 * 
	 * @param paymentInfo
	 * @return
	 */
	@RequestMapping("/getPayInfoToken")
	public ShopResult getPayInfoToken(@RequestParam("token") String token);

	/**
	 * 使用token查找支付信息
	 * 
	 * @param paymentInfo
	 * @return
	 */
	@RequestMapping("/getByOrderIdPayInfo")
	public ShopResult getByOrderIdPayInfo(@RequestParam("orderId") String orderId);
	
	/**
	 * 使用token查找支付信息
	 * 
	 * @param paymentInfo
	 * @return
	 */
	@RequestMapping("/updatePayInfo")
	public ShopResult updatePayInfo(@RequestBody PaymentInfo paymentInfo);
}
