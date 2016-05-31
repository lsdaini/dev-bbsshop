package net.shopweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.shopweb.pojo.EasyUIResult;
import net.shopweb.pojo.ShopWebResult;
import net.shopweb.pojo.TbItem;
import net.shopweb.service.ItemService;

/**
 * 商品管理控制层
 * @author sai.liu
 *
 */
@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId) {
		TbItem tbItem = itemService.getItemById(itemId);
		return tbItem;
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIResult getItemList(Integer page,Integer rows) {
		EasyUIResult easyUIResult = itemService.getItemList(page, rows);
		return easyUIResult;
	}
	
	@RequestMapping(value="/item/save", method=RequestMethod.POST)
	@ResponseBody
	private ShopWebResult createItem(TbItem item, String desc, String itemParams) throws Exception {
		ShopWebResult result = itemService.saveItem(item, desc, itemParams);
		return result;
	}
}
