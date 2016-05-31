package net.shopweb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.shopweb.mapper.TbItemDescMapper;
import net.shopweb.mapper.TbItemMapper;
import net.shopweb.mapper.TbItemParamItemMapper;
import net.shopweb.pojo.EasyUIResult;
import net.shopweb.pojo.ShopWebResult;
import net.shopweb.pojo.TbItem;
import net.shopweb.pojo.TbItemDesc;
import net.shopweb.pojo.TbItemExample;
import net.shopweb.pojo.TbItemParamItem;
import net.shopweb.service.ItemService;
import net.shopweb.utils.IDUtils;
/**
 * 商品管理
 * @author sai.liu
 *
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	
	@Override
	public TbItem getItemById(long ItemId) {
		return itemMapper.selectByPrimaryKey(ItemId);
	}
	/**
	 * 分页查询商品信息
	 */
	@Override
	public EasyUIResult getItemList(int page, int rows) {
		TbItemExample example = new TbItemExample();
		//分页处理
		PageHelper.startPage(page, rows);
		List<TbItem> list = itemMapper.selectByExample(example);
		//创建返回对象
		EasyUIResult easyUIResult = new EasyUIResult();
		easyUIResult.setRows(list);
		//取出分页信息
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		easyUIResult.setTotal(pageInfo.getTotal());
		return easyUIResult;
	}
	@Override
	public ShopWebResult saveItem(TbItem item, String desc, String itemParam) throws Exception {
		//item补全
		//生成商品ID
		Long itemId = IDUtils.genItemId();
		item.setId(itemId);
		// '商品状态，1-正常，2-下架，3-删除',
		item.setStatus((byte) 1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		//插入到数据库
		itemMapper.insert(item);
		//添加商品描述信息
		ShopWebResult result = insertItemDesc(itemId, desc);
		if (result.getStatus() != 200) {
			throw new Exception();
		}
		//添加规格参数
		result = insertItemParamItem(itemId, itemParam);
		if (result.getStatus() != 200) {
			throw new Exception();
		}
		return ShopWebResult.ok();
	}
	
	/**
	 * 添加商品描述
	 * <p>Title: insertItemDesc</p>
	 * <p>Description: </p>
	 * @param desc
	 */
	private ShopWebResult insertItemDesc(Long itemId, String desc) {
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemDescMapper.insert(itemDesc);
		return ShopWebResult.ok();
	}
	
	/**
	 * 添加规格参数
	 * <p>Title: insertItemParamItem</p>
	 * <p>Description: </p>
	 * @param itemId
	 * @param itemParam
	 * @return
	 */
	private ShopWebResult insertItemParamItem(Long itemId, String itemParam) {
		//创建一个pojo
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setItemId(itemId);
		itemParamItem.setParamData(itemParam);
		itemParamItem.setCreated(new Date());
		itemParamItem.setUpdated(new Date());
		//向表中插入数据
		itemParamItemMapper.insert(itemParamItem);
		
		return ShopWebResult.ok();
		
	}
}
