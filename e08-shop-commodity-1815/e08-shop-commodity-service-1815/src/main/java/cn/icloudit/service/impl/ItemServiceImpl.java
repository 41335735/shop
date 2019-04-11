package cn.icloudit.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import cn.icloudit.api.service.ItemService;
import cn.icloudit.common.api.ShopResult;
import cn.icloudit.dao.ItemCatDao;
import cn.icloudit.dao.ItemDao;
import cn.icloudit.entity.Item;
import cn.icloudit.entity.ItemCat;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ItemServiceImpl  implements ItemService{

	@Autowired 
	public ItemDao itemDao;
	
	@Autowired 
	public ItemCatDao itemCatDao;
	 
	@RequestMapping("/getIndexItem")
	public ShopResult getIndexItem() {
		//查询所有类型
		List<ItemCat> listItemCat = itemCatDao.allItemCat();
		//result用来存放以类别名称为键，以该类别List为值得对象
		Map<String , Object> result = new HashMap<String , Object>();
		for(ItemCat itemCat : listItemCat) {
			int id = itemCat.getId();
			String name = itemCat.getName();
			List<Item> listItem = itemDao.getIndexItem(new Long(id));
			if(!(listItem.isEmpty() && listItem.size() <= 0)) {
				result.put(name, listItem);
			}
			
		}
		
		return ShopResult.ok(result);
	}

	

	@Override
	public ShopResult getItem(Long id) {
		Item item = itemDao.getItem(id);
		if(item == null) {
			return ShopResult.build(400, "没有查到结果");
		}
		return ShopResult.ok(item);
	}

	
	
	
}
