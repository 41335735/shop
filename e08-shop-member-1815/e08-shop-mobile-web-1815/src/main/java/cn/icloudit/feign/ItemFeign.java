package cn.icloudit.feign;

import org.springframework.cloud.openfeign.FeignClient;

import cn.icloudit.api.service.ItemService;

@FeignClient("commodity")
public interface ItemFeign extends ItemService{

}
