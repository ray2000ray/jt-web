package com.jt.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.po.Item;
import com.jt.common.po.ItemDesc;
import com.jt.web.service.ItemService;

@Controller
@RequestMapping("/items")
public class ItemController {
	@Autowired
	ItemService itemService;
	
	//查询商品信息, 之后进行页面展现
	@RequestMapping("/{itemId}")
	public String findItemById(@PathVariable Long itemId, Model model) {
		System.out.println("itemId:"+itemId);
		Item item = itemService.findItemByItemId(itemId);
		model.addAttribute("item", item);
		ItemDesc itemDesc = itemService.findItemDescByItemId(itemId);
		model.addAttribute("itemDesc", itemDesc);
		//查询产品详细信息
		
		return "item";
	}
	
}
