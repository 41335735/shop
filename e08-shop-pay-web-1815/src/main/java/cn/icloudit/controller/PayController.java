package cn.icloudit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;

import cn.icloudit.common.api.ShopResult;
import cn.icloudit.entity.PaymentInfo;
import cn.icloudit.feign.PaymentInfoFeign;
import cn.icloudit.service.pay.impl.PayImplService;
import cn.icloudit.utils.ResultUils;
@Controller
public class PayController   {
	@Autowired
	private PaymentInfoFeign paymentInfoFeign;
	private static final String PAYGATEWAY = "payGateway";
	@Autowired
	private PayImplService payService;

	@RequestMapping("/payGateway")
	public void payGateway(HttpServletRequest request, String token,HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html;charset=utf-8");
		 ShopResult payInfoToken = paymentInfoFeign.getPayInfoToken(token);
		Map<String, Object> resultMap = (Map<String, Object>) ResultUils.getResultMap(payInfoToken);
		PrintWriter out=resp.getWriter();
		if (resultMap == null) {
			resp.getWriter().write("系统错误!");
			 return ;
		}
		String json = new JSONObject().toJSONString(resultMap);
		PaymentInfo paymentInfo = new JSONObject().parseObject(json, PaymentInfo.class);
		String html = payService.pay(paymentInfo);
		out.println(html);
		out.close();
	}

}
