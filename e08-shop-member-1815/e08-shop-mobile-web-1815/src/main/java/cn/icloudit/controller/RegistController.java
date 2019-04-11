package cn.icloudit.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.icloudit.api.entity.User;
import cn.icloudit.base.controller.BaseController;
import cn.icloudit.common.api.ShopResult;
import cn.icloudit.feign.UserFeign;

@Controller
public class RegistController extends BaseController {

	private static final String LOCAREGIST = "locaRegist";
	private static final String LOGIN = "login";
	
	@Autowired
	private UserFeign userFeign;
	
	@RequestMapping("/locaRegist")
	public String locaRegist() {
		return LOCAREGIST;
	}
	
	@RequestMapping("/regist")
	public String regist(User user,HttpServletRequest request) {
		try {
			ShopResult regisMap = userFeign.regist(user);
			Integer code = (Integer) regisMap.getStatus();
			if(!code.equals(200)) {
				String msg = (String) regisMap.getMsg();
				return setError(request,msg,LOCAREGIST);
			}
			
			return LOGIN;
			
		}catch(Exception e){
			
			return setError(request,"注册失败！",LOCAREGIST);
		}
		
	}
}
