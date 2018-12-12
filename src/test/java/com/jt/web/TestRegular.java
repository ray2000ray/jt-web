package com.jt.web;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegular {

	public static void main(String[] args) {
		String str= "<div style=\"text-align: center;\">\r\n" + 
				"	<span style=\"color: rgb(255, 0, 0);\"><a href=\"https://pan.baidu.com/s/1EDNF4LmQeAO8d6Q1Cpd1Xw\" target=\"_blank\"><img alt=\"\" src=\"http://www.005.tv/uploads/allimg/181210/32-1Q210135J2624.png\" style=\"width: 200px; height: 152px;\" /></a></span></div>\r\n" + 
				"<div style=\"text-align: center;\">\r\n" + 
				"	<img alt=\"场景壁纸,壁纸,场景，二次元，萌图\" src=\"http://www.005.tv/uploads/allimg/181212/55-1Q2121456424M.jpg\" style=\"width: 800px; height: 533px;\" /></div>\r\n" + 
				"<div style=\"text-align: center;\">\r\n" + 
				"	id=782958</div>\r\n" + 
				"<div style=\"text-align: center;\">\r\n" + 
				"	<img alt=\"场景壁纸,壁纸,场景，二次元，萌图\" src=\"http://www.005.tv/uploads/allimg/181212/55-1Q212145G1357.jpg\" style=\"width: 800px; height: 600px;\" /></div>\r\n" + 
				"<div style=\"text-align: center;\">\r\n" + 
				"	id=1719875</div>\r\n" + 
				"<div style=\"text-align: center;\">\r\n" + 
				"	<img alt=\"场景壁纸,壁纸,场景，二次元，萌图\" src=\"http://www.005.tv/uploads/allimg/181212/55-1Q212145I5264.jpg\" style=\"width: 800px; height: 600px;\" /></div>\r\n" + 
				"<div style=\"text-align: center;\">\r\n" + 
				"	id=3337773</div>\r\n" + 
				"<div style=\"text-align: center;\">\r\n" + 
				"	<img alt=\"场景壁纸,壁纸,场景，二次元，萌图\" src=\"http://www.005.tv/uploads/allimg/181212/55-1Q212145J6101.jpg\" style=\"width: 800px; height: 500px;\" /></div>\r\n" + 
				"<div style=\"text-align: center;\">\r\n" + 
				"	id=5290131</div>\r\n" + 
				"<div style=\"text-align: center;\">\r\n" + 
				"	<img alt=\"场景壁纸,壁纸,场景，二次元，萌图\" src=\"http://www.005.tv/uploads/allimg/181212/55-1Q212145R5318.jpg\" style=\"width: 800px; height: 500px;\" /></div>\r\n";
		Pattern pattern = Pattern.compile("<img.*\\.jpg");
		Pattern pattern2 = Pattern.compile("http.*\\.jpg");
		Matcher matcher = pattern.matcher(str);
		List result = new ArrayList();
		while(matcher.find()) {			
			Matcher matcher2 = pattern2.matcher(matcher.group());
			while(matcher2.find()) {
				result.add(matcher2.group());
				System.out.println(matcher2.group());
			}				
		}
		System.out.println(result);
	}
	
}

