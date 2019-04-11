package cn.icloudit.entity;

import cn.icloudit.common.api.enity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentType extends BaseEntity {

	/**
	 * 支付平台
	 */
	private String typeName;
	/**
	 * 同步通知
	 */
	private String frontUrl;

	/**
	 * 异步通知
	 */
	private String backUrl;
	/**
	 * 商户id
	 */
	private String merchantId;

}