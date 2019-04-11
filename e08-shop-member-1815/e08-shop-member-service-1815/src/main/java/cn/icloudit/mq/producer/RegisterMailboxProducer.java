package cn.icloudit.mq.producer;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

@Service("registerMailboxProducer")
public class RegisterMailboxProducer {

	@Autowired
	private JmsMessagingTemplate  jmsMessagingTemplate;
	
	public  void send(Destination destionation,String json) {
		
		jmsMessagingTemplate.convertAndSend(destionation, json);
		 
	}
	
}
