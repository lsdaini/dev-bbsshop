package net.shopweb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.shopweb.mapper.TbItemParamMapper;
import net.shopweb.pojo.EasyUIResult;
import net.shopweb.pojo.ShopWebResult;
import net.shopweb.pojo.TbItem;
import net.shopweb.pojo.TbItemExample;
import net.shopweb.pojo.TbItemParam;
import net.shopweb.pojo.TbItemParamExample;
import net.shopweb.pojo.TbItemParamExample.Criteria;
import net.shopweb.service.ItemParamService;


/**
 * 商品规格参数模板管理
 * <p>Title: ItemParamServiceImpl</p>
 * @version 1.0
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private TbItemParamMapper itemParamMapper;
	
	@Override
	public ShopWebResult getItemParamByCid(long cid) {
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		//判断是否查询到结果
		if (list != null && list.size() > 0) {
			return ShopWebResult.ok(list.get(0));
		}
		return ShopWebResult.ok();
	}

	@Override
	public ShopWebResult insertItemParam(TbItemParam itemParam) {
		//补全pojo
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		//插入到规格参数模板表
		itemParamMapper.insert(itemParam);
		return ShopWebResult.ok();
	}

}
