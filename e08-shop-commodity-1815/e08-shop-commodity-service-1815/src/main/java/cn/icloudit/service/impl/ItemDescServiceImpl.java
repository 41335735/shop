package cn.icloudit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.icloudit.api.service.ItemDescService;
import cn.icloudit.common.api.ShopResult;
import cn.icloudit.dao.ItemDescDao;
import cn.icloudit.entity.ItemDesc;

@RestController
public class ItemDescServiceImpl implements ItemDescService{

	@Autowired
	private ItemDescDao itemDescDao;
	
	@RequestMapping("/getItemDesc")
	public ShopResult getItemDesc(@RequestParam("id") Long id) {
		ItemDesc itemDesc = itemDescDao.getItemDesc(id);
		return ShopResult.ok(itemDesc);
		
	}

}
