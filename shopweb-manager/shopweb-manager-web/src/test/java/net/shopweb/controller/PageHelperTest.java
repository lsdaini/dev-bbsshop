package net.shopweb.controller;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.shopweb.mapper.TbItemMapper;
import net.shopweb.pojo.TbItem;
import net.shopweb.pojo.TbItemExample;

public class PageHelperTest {
	@Test
	public void testPageHelper(){
		//创建spring容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:config/applicationContext-*.xml");
		//从spring容器中获取mapper对象
		TbItemMapper mapper = applicationContext.getBean(TbItemMapper.class);
		//执行查询，分页
		TbItemExample example = new TbItemExample();
		//分页处理
		PageHelper.startPage(1, 10);
		List<TbItem> list = mapper.selectByExample(example);
		//取出商品列表
		for (TbItem tbItem : list) {
			System.out.println(tbItem.getTitle());
		}
		//取出分页信息
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
		System.out.println("共有商品信息："+total+"条");
	}
}
