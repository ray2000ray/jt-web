package com.jt.web.service;

import java.util.List;

import com.jt.common.po.Cart;

public interface CartService {

	public List<Cart> findCartByUserId(Long userId);

	public void updateNum(Long userId, Long itemId, Long num);

	public void deleteCart(Long itemId, Long userId);

	public void saveCart(Cart cart);

	
	
}
