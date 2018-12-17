package com.jt.web.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.po.User;
import com.jt.common.vo.SysResult;
import com.jt.web.service.UserService;

import redis.clients.jedis.JedisCluster;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private JedisCluster jedisCluster;
	

	
	@RequestMapping("/{moduleName}")//模块跳转
	public String toModule(@PathVariable String moduleName) {
		System.out.println("moduleName:"+moduleName);
		return moduleName;
		
	}
	//实现用户注册
	@RequestMapping("/doRegister")
	@ResponseBody
	public SysResult saveUser(User user) {
				
		try {
			System.out.println("web username:"+user.getUsername()+" Email"+user.getEmail()+"phone"+user.getPhone());
			userService.saveUser(user);
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "用户新增失败");	
	}
	
	
	
	@RequestMapping("/doLogin")
	public SysResult findUserByUP(User user, HttpServletResponse response) {
		try {
			
			//获取后台返回的秘钥
			String token = userService.findUserByUP(user);
			//返回数据不为空, 将token数据写入Cookie中
			if (!StringUtils.isEmpty(token)) {
				Cookie cookie = new Cookie("JT_TICKET", token);
				cookie.setMaxAge(7*24*3600); //保存一周 ;cookie.setMaxAge(0)--表示立即删除 cookie.setMaxAge(-1) --会话关闭后立即删除cookie
				cookie.setPath("/"); //访问cookie的权限, 全网站为"/","/user"表示/user/目录
				response.addCookie(cookie);
				return SysResult.oK();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "用户登录失败");
	}
	
	/**
	 * 1.先删除redis 从cookie中动态获取jt_ticket的值,之后删除
	 * 2.再删除cookie 为cookie设定最大生命周期 0 setPath("/")
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout( HttpServletRequest request, HttpServletResponse response) {
		
		//1.获取cookie数据
		Cookie[] cookies = request.getCookies();
		String token =null;
		
		for (Cookie cookie : cookies) {
			if ("JT_TICKET".equals(cookie.getName())) {
				token = cookie.getValue();
				break;
			}
		}
		//获取token 删除redis
		jedisCluster.del(token);
		//删除cookie
		Cookie cookie = new Cookie("JT_TICKET", "");
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
		//页面跳转到系统首页
		return "redirect:/index.html";
	}
	
}
