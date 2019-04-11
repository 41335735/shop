package cn.icloudit.api.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.icloudit.common.api.ShopResult;

/**
 * 商品详情
 * @author Administrator
 *
 */
@RequestMapping("/item")
public interface ItemDescService {
	/**
	 * 查询商品详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/getItemDesc")
	public ShopResult getItemDesc(@RequestParam("id") Long id);
}
