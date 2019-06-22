package cn.bjjoy.bms.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpEntity;

public class TestHttpUtils {

	public static void main(String[] args) {
		// 响应模型

		CloseableHttpClient client = HttpClientBuilder.create().build();
		try {
			Files.readAllLines(Paths.get("e:/liuqishan/httprefreshdata.txt")).stream().forEach(line ->{
				HttpGet get = new HttpGet(line);
				CloseableHttpResponse response = null;
				try {
					response = client.execute(get);
					System.out.println("响应状态为:" + response.getStatusLine());
					
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
