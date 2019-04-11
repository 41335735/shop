package cn.icloudit.api.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.icloudit.common.api.ShopResult;

/**
 * 功能说明 ： 商品服务
 * @author Administrator
 *
 */
@RequestMapping("/item")
public interface ItemService {

	/**
	 * 首页商品展示
	 * @return
	 */
	@RequestMapping("/getIndexItem")
	public ShopResult getIndexItem();
	
	/**
	 * 查询商品
	 * @param id
	 * @return
	 */
	@RequestMapping("/getItem")
	public ShopResult getItem(@RequestParam("id") Long id);
}
