package cn.icloudit.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;

import cn.icloudit.api.entity.User;
import cn.icloudit.base.controller.BaseController;
import cn.icloudit.common.api.ShopResult;
import cn.icloudit.common.constants.Constants;
import cn.icloudit.common.redis.BaseRedisService;
import cn.icloudit.feign.ItemFeign;
import cn.icloudit.utils.CookieUitl;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class IndexController extends BaseController{

	//index页面
	public static final String INDEX = "index";
	
	
	@Autowired
	private ItemFeign itemFeign;
	 
	
	private static final String LOGIN = "login";
	
	@Autowired
	private BaseRedisService baseRedisService;
	
	/**
	 * 首页
	 * @param request
	 * @return
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		ShopResult reusltMapItem = itemFeign.getIndexItem();
		Integer code = reusltMapItem.getStatus();
		if(code.equals(200)) {
			Map<String , Object> mapItem = (Map<String, Object>) reusltMapItem.getData();
			request.setAttribute("mapItem", mapItem);
			
		}
		return INDEX;
	}
	
	/**
	   *  加入购物车
	 * @param request
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/addShopping")
	@ResponseBody
	public ShopResult addShopping(HttpServletRequest request , String itemId) {
		String token = CookieUitl.getUid(request,Constants.USER_TOKEN);
		if(StringUtils.isEmpty(token)) {
			return ShopResult.build(100, "您没有登录，请登录后才可以加入到购物车");
		}
		User user = getUser(token);
		if(user == null) {
			return ShopResult.build(100, "您没有登录，请登录后才可以加入到购物车");
		}
		try {
			String value = baseRedisService.get(user.getId()+"");
			if(StringUtils.isEmpty(value)) {
				JSONObject root= new JSONObject();
				root.put(itemId+"",1);
				baseRedisService.set(user.getId() + "", root.toJSONString(),Constants.WEBUSER_SHOP_TERMVALIDITY);
			}else {
				JSONObject parsObject = new JSONObject().parseObject(value);
				Integer redisTemId = parsObject.getInteger(itemId+"");
				if(redisTemId != null) {
					redisTemId++;
				}
				//添加到redis中
				parsObject.put(itemId,redisTemId);
				baseRedisService.set(user.getId()+"", parsObject.toJSONString(), Constants.WEBUSER_SHOP_TERMVALIDITY);
			}
			return ShopResult.build(200, "加入购物车成功！");
		} catch (Exception e) {
			log.error("addShopping() ERROR:",e);
			return ShopResult.build(400, "加入购物车失败，请稍后！");
		}
	}

	
}
