package cn.icloudit.base.controller;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;

import cn.icloudit.api.entity.User;
import cn.icloudit.common.api.ShopResult;
import cn.icloudit.feign.UserFeign;

@Controller
public class BaseController {

	@Autowired
	private UserFeign userFeign;
	
	public User getUser(String token) {
		ShopResult userMap = userFeign.getUser(token);
		Integer code  = userMap.getStatus();
		if(!code.equals(200)) {
			return null;
		}
		LinkedHashMap linkedHashMap = (LinkedHashMap) userMap.getData();
		String json = new JSONObject().toJSONString(linkedHashMap);
		User user = new JSONObject().parseObject(json,User.class);
		return user;
	}
	
	public String setError(HttpServletRequest request,String msg ,String address) {
		request.setAttribute("error", msg);
		return address;
	}
}
