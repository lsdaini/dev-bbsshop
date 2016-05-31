package net.shopweb.service;

import net.shopweb.pojo.EasyUIResult;
import net.shopweb.pojo.ShopWebResult;
import net.shopweb.pojo.TbItemParam;

public interface ItemParamService {

	ShopWebResult getItemParamByCid(long cid);
	
	ShopWebResult insertItemParam(TbItemParam itemParam);
}
