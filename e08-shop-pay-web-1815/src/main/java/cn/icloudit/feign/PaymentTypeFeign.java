package cn.icloudit.feign;


import org.springframework.cloud.openfeign.FeignClient;

import cn.icloudit.service.PaymentTypeService;

@FeignClient("pay-service")
public interface PaymentTypeFeign extends PaymentTypeService {

}
