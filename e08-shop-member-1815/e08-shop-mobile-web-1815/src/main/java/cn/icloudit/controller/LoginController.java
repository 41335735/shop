package cn.icloudit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.icloudit.api.entity.User;
import cn.icloudit.base.controller.BaseController;
import cn.icloudit.common.api.ShopResult;
import cn.icloudit.common.constants.Constants;
import cn.icloudit.feign.UserFeign;
import cn.icloudit.utils.CookieUitl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController extends BaseController {

	private static final String LOGIN = "login";
	private static final String INDEX = "index";
	
	@Autowired
	private UserFeign userFeign;
	
	@RequestMapping("/locaLogin")
	public String locaLogin() {
		
		return LOGIN;
	}
	
	@RequestMapping("/login")
	public String login(User user , HttpServletRequest request, HttpServletResponse response) {
		/* log.debug("phone:" + user.getPhone() + ",password:" + user.getPassword()); */
		ShopResult login = userFeign.login(user);
		
		Integer code = login.getStatus();
		if(!code.equals(200)) {
			String msg = (String) login.getMsg();
			log.debug("msg" + msg);
			return setError(request, msg, LOGIN);
		}
		String token =(String) login.getData();
		CookieUitl.addCookie(response, "token" ,token, Constants.WEBUSER_COOKIE_TOKEN_TERMVALIDITY);
		
		
		//CookieUitl.setCookie(request, response, "token" +"",token);
		return "redirect:/index";
	}
	
}
