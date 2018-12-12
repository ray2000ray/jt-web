package com.jt.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class TestHttpClient {
	//测试远程访问tmooc
	/**
	 * 步骤:
	 * 1.定义httpClient请求对象
	 * 2.定义访问url地址
	 * 3.定义请求类型.get/post/put
	 * 4.发起请求, 获取返回值结果
	 * 5.判断返回值的状态是否为200
	 * 6.解析返回值
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@Test
	public void testGet() throws ClientProtocolException, IOException {
		CloseableHttpClient client = 
		HttpClients.createDefault();
		String fileName = "E:/Images";
		File file = new File(fileName);
		if (!file.exists()) {
			file.mkdirs();
		}
		
		
		String url ="http://moe.005.tv/75403_2.html";
		
		HttpGet get = new HttpGet(url);
		
		List<String> imageList = new ArrayList<String>();
		
		CloseableHttpResponse response= client.execute(get);
		
		if(response.getStatusLine().getStatusCode()==200) {
			String result = EntityUtils.toString(response.getEntity());
			imageList = getPattern(result);
		}
		for(String ss: imageList) {
		Long time = System.currentTimeMillis();
		URL url1 = new URL(ss);
		// 打开URL连接
		URLConnection con = (URLConnection)url1.openConnection();
		// 得到URL的输入流
		InputStream input = con.getInputStream();
		// 设置数据缓冲
		byte[] bs = new byte[1024 * 10];
		// 读取到的数据长度
		int len;
		// 输出的文件流保存图片至本地
		String priceName = fileName + "/" + time + ".jpg";
		OutputStream os = new FileOutputStream(priceName);
		while ((len = input.read(bs)) != -1) {
			os.write(bs, 0, len);
		}
		System.out.println("图片"+priceName+"已下载");
		os.close();
		input.close();
		}
		System.out.println("下载全部完成!");
				
	}
	
	//截取字符串中的图片超链接
	public List<String> getPattern(String str) {
		
		Pattern pattern = Pattern.compile("<img.*\\.jpg");
		Pattern pattern2 = Pattern.compile("http.*\\.jpg");
		Matcher matcher = pattern.matcher(str);
		List<String> result = new ArrayList<String>();
		while(matcher.find()) {			
			Matcher matcher2 = pattern2.matcher(matcher.group());
			while(matcher2.find()) {
				result.add(matcher2.group());
				System.out.println(matcher2.group());
			}				
		}
		return result;
	}
	
	
	
}
