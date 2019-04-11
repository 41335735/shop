package cn.icloudit.feign;


import org.springframework.cloud.openfeign.FeignClient;

import cn.icloudit.api.service.IUserService;

@FeignClient("member")
public interface UserFeign extends IUserService {


	
}
