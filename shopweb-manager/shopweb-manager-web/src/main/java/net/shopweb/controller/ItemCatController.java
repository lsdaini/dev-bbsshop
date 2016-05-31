package net.shopweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.shopweb.pojo.EasyUITreeNode;
import net.shopweb.service.ItemCatService;


/**
 * 商品分类管理controller
 * @version 1.0
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;
	
	@RequestMapping("/list")
	@ResponseBody
	private List<EasyUITreeNode> getCatList(@RequestParam(value="id",defaultValue="0")Long parentId) {
		List<EasyUITreeNode> list = itemCatService.getCatList(parentId);
		return list;
	}
}
