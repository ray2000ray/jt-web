package com.jt.web.service;

import com.jt.common.po.Item;
import com.jt.common.po.ItemDesc;

public interface ItemService {

	Item findItemByItemId(Long itemId);

	ItemDesc findItemDescByItemId(Long itemId);

}
