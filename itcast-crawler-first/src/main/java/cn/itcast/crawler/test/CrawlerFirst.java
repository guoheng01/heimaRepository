package cn.itcast.crawler.test;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class CrawlerFirst {
    public static void main(String[] args) {
        //1.打开浏览器，创建httpClient对象
        CloseableHttpClient httpClient  = HttpClients.createDefault();
        //2.输入网址,发起get请求，创建httpGet对象
        HttpGet httpGet = new HttpGet("http://www.itcast.cn/");
        //3.按回车，发起请求，返回
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //4.解析响应，获取数据
            //4.1判断状态码
            if(response.getStatusLine().getStatusCode()==200){
                //4.2获取响应实体
                HttpEntity httpEntity = response.getEntity();
                String content = null;
                    try {
                        //将响应体转换成字符串
                        content  = EntityUtils.toString(httpEntity, "UTF-8");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                System.out.println(content);

            }
    }
}
