package cn.icloudit.feign;


import org.springframework.cloud.openfeign.FeignClient;

import cn.icloudit.service.PaymentInfoService;

@FeignClient("pay-service")
public interface PaymentInfoFeign extends PaymentInfoService {

}
