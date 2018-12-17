package com.jt.web.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.po.Item;
import com.jt.common.po.ItemDesc;
import com.jt.common.service.HttpClientService;
import com.jt.web.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{
	@Autowired
	private HttpClientService httpClient;
	
	//@Autowired
	private ObjectMapper objectMapper = new ObjectMapper();
	
	/**
	 * 1.定义url地址
	 * 2.封装参数
	 * 3.发起请求, 解析返回值结果
	 */
	@Override
	public Item findItemByItemId(Long itemId) {
		String url ="http://manage.jt.com/web/item/findItemById";
		Map <String, String> params = new HashMap<>();
		System.out.println("0.itemId:"+itemId);
		params.put("itemId", itemId+"");
		
		String itemJSON = 
		httpClient.doGet(url, params);
		System.out.println("itemJSON:" +itemJSON);
		Item item = null;
		
		//将json转化为对象
		try {
			item = objectMapper.readValue(itemJSON, Item.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return item;
	}
	
	
	public ItemDesc findItemDescByItemId(Long itemId) {
		
		String url= "http://manage.jt.com/web/item/findItemDescByItemId/"+itemId;
		
		String itemDescJSON = httpClient.doGet(url);
		ItemDesc itemDesc = null;
		
		try {
			itemDesc = objectMapper.readValue(itemDescJSON, ItemDesc.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
		return itemDesc;
	}
	
	
	

}
