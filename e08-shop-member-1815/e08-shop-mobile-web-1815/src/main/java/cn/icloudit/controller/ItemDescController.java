package cn.icloudit.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.icloudit.base.controller.BaseController;
import cn.icloudit.common.api.ShopResult;
import cn.icloudit.feign.ItemDescFeign;
import cn.icloudit.feign.ItemFeign;
import cn.icloudit.utils.ResultUils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ItemDescController extends BaseController{
	private static final String  ITEMDESC = "itemDesc";

	
	
	@Autowired
	private ItemDescFeign itemDescFeign;
	
	@Autowired
	private ItemFeign itemFeign;
	
	@RequestMapping("/itemDesc")
	public String itemDesc(HttpServletRequest request , Long id) {
		try {
			ShopResult  resultItem = itemFeign.getItem(id);
			Map<String, Object> item = (Map<String, Object>) ResultUils.getResultMap(resultItem);
			request.setAttribute("item", item);
			ShopResult resultItemDesc = itemDescFeign.getItemDesc(id);
			Map<String , Object> itemDesc = (Map<String, Object>) ResultUils.getResultMap(resultItemDesc);
			request.setAttribute("itemDesc", itemDesc);
			return ITEMDESC;
		} catch (Exception e) {
			log.error("###itemDesc() ERROR",e);
			return "common/error";
		}
		
		
	}
	
}
