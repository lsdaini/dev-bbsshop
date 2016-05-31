package net.shopweb.service;

import net.shopweb.pojo.EasyUIResult;
import net.shopweb.pojo.ShopWebResult;
import net.shopweb.pojo.TbItem;

public interface ItemService {

	TbItem getItemById(long ItemId);
	
	EasyUIResult getItemList(int page,int rows);
	
	ShopWebResult  saveItem(TbItem item, String desc, String itemParams) throws Exception;
}
