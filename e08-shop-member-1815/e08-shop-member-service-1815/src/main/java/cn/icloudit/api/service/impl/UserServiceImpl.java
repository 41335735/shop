package cn.icloudit.api.service.impl;

import java.util.UUID;

import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cn.icloudit.api.entity.User;
import cn.icloudit.api.service.IUserService;
import cn.icloudit.common.api.ShopResult;
import cn.icloudit.common.constants.Constants;
import cn.icloudit.common.constants.DBTableName;
import cn.icloudit.common.constants.MQInterfaceType;
import cn.icloudit.common.redis.BaseRedisService;
import cn.icloudit.dao.IUserDAO;
import cn.icloudit.mq.producer.RegisterMailboxProducer;
import cn.icloudit.utils.DateUtils;
import cn.icloudit.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController // 响应以json格式输出
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDAO userDao;

	@Value("${messages.queue}")
	private String MESSAGES_QUEUE;
	
	@Autowired
	private RegisterMailboxProducer registerMailboxProducer;
	
	@Autowired
	private BaseRedisService baseRedisService;
	
	@Override
	// @RequestBody表示请求以json格式传入
	public ShopResult regist(@RequestBody User user) {
		if (StringUtils.isEmpty(user.getUserName())) {
			return ShopResult.build(400, "用户名称不能为空!");
		}
		if (StringUtils.isEmpty(user.getPassword())) {
			return ShopResult.build(400, "密码不能为空!");
		}
		try {
			user.setCreated(DateUtils.getTimestamp());
			user.setUpdated(DateUtils.getTimestamp());
			String phone = user.getPhone();
			String password = user.getPassword();
			user.setPassword(md5PassSalt(phone, password));
			userDao.save(user, DBTableName.TABLE_MB_USER);

			// 队列
			Destination activeMQQueue = new ActiveMQQueue(MESSAGES_QUEUE);
			// 组装报文格式
			String mailMessage = mailMessage(user.getEmail(), user.getUserName());
			log.info("###regist() 注册发送邮件报文mailMessage:{}", mailMessage);
			registerMailboxProducer.send(activeMQQueue, mailMessage);

			return ShopResult.ok();
		} catch (Exception e) {
			log.error("###regist() ERROR:", e);
			return ShopResult.build(500, "注册失败");
		}
	}

	public String md5PassSalt(String phone, String password) {
		String newPass = MD5Util.MD5(phone + password);
		return newPass;

	}

	
	/**
{
 "header":{
   "interfaceType":"接口类型"
  }
 "content":{
     "mail":"",
     "username":""
  }
}
	 * @param email
	 * @param userName
	 * @return
	 */
	private String mailMessage(String email, String userName) {
		JSONObject root = new JSONObject();
		JSONObject header = new JSONObject();
		header.put("interfaceType", MQInterfaceType.SMS_MAIL);
		JSONObject content = new JSONObject();
		content.put("mail", email);
		content.put("userName", userName);
		root.put("header", header);
		root.put("content", content);
		return root.toJSONString();
	}

	@Override
	public ShopResult login(@RequestBody User user) {
		//往数据进行查找数据
			String phone = user.getPhone();
			String password = user.getPassword();
			String newPassWord = md5PassSalt(phone, password);
			User userPhoneAndPwd = userDao.getUserPhoneAndPwd(phone,newPassWord);
			if(userPhoneAndPwd == null) {
				return ShopResult.build(400,"账户或密码错误");
			}
			String token = UUID.randomUUID().toString();
			Integer userId = userPhoneAndPwd.getId();
			baseRedisService.set(token, userId + "", Constants.USER_TOKEN_TERMVALTIDITY);
			return new ShopResult(token);
			
	}

	@Override
	public ShopResult getUser(@RequestParam("token") String token) {
		if(StringUtils.isEmpty(token)) {
			return ShopResult.build(400,"TOKEN不能为空");
		}
		System.err.println(1111111111);
		String userId = baseRedisService.get(token);
		if(StringUtils.isEmpty(userId)) {
			return ShopResult.build(400,"用户已经过期");
		}
		Long newUserIdl = Long.parseLong(userId);
		User userInfo = userDao.getUserInfo(newUserIdl);
		userInfo.setPassword(null);
		return new ShopResult(userInfo);
	}

}
