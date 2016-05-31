package net.shopweb.service;

import java.util.List;

import net.shopweb.pojo.EasyUITreeNode;

public interface ItemCatService {

	List<EasyUITreeNode> getCatList(long parentId);
}
