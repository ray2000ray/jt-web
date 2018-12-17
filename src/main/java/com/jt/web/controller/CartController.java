package com.jt.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.common.po.Cart;
import com.jt.common.vo.SysResult;
import com.jt.web.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartService cartService;
	
	Cart cart = new Cart();
	
	//跳转到购物车页面
	@RequestMapping("/show")
	public String findCartById(Model model) {
		Long userId =7L;
		List<Cart> cartList= (List<Cart>)cartService.findCartByUserId(userId);
		model.addAttribute("cartList",cartList);	
		return "cart";
	}
	
	//实现商品数量修改
	@RequestMapping("/update/num/{itemId}/{num}")
	public SysResult updateNum(@PathVariable Long itemId, @PathVariable Long num) {
		
		try {
			Long userId = 7L;
			
			cartService.updateNum(userId, itemId, num);
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "商品修改失败");
		
	}
	
	/**
	 * 购物车删除, 跳转到购物车列表页面
	 * 转发:
	 * 参数:可以携带参数
	 * 请求路径地址:转化变化
	 * 请求次数:1
	 * 
	 * 使用绝对路径进行转发, 多次转发会出现拼接出位置地址的问题
	 * 
	 * 重定向:
	 * 参数:不能携带参数
	 * 请求次数: 多次 访问旧的网址
	 * 
	 * 
	 */
	@RequestMapping("/delete/{itemId}")
	public String deleteCart(Long itemId) {
		Long userId = 7L;
		cartService.deleteCart(itemId, userId);
		return "redirect:/cart/show.html";
	}
	
	@RequestMapping("/save/{itemId}")
	public String saveCart(@PathVariable Long itemId) {
		Long userId = 7L;
		cart.setItemId(itemId);
		cart.setUserId(userId);
		cartService.saveCart(cart);		
		//如果操作正常, 则重定向到购物车列表页面
		return "redirect:/cart/show.html";
		
	}
	
	
}
