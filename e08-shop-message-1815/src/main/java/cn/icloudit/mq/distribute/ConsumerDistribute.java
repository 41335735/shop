package cn.icloudit.mq.distribute;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import cn.icloudit.adapter.MessageAdapeter;
import cn.icloudit.service.SMSMailboxService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ConsumerDistribute {

	@Autowired
	private SMSMailboxService smsMailboxService;
	
	@JmsListener(destination = "mail_queue")
	public void distribute(String json){
		log.info("##消息服务*#收到消息，消息内容json:()",json);
		if (StringUtils.isEmpty(json)) {
			return;
		}
		JSONObject jsonObject = new JSONObject().parseObject(json);
		JSONObject header = jsonObject.getJSONObject("header");
		String interfaceType=header.getString("interfaceType");
		MessageAdapeter messageAdapeter = null;
		switch (interfaceType) {
		//发送邮件
		case "sms_mail":
			messageAdapeter = smsMailboxService;
			break;
		default :
				break;
		}

	

		JSONObject content = jsonObject.getJSONObject("content");
		messageAdapeter.distribute(content);
	}
	
}
