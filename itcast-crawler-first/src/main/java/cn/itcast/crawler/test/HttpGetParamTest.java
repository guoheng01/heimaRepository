package cn.itcast.crawler.test;


import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * 有参get请求
 */
public class HttpGetParamTest {

    public static void main(String[] args) {
        //1.打开浏览器:创建HTTPClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        URIBuilder uriBuilder = null;
        CloseableHttpResponse response = null;
        try {
            //https://www.baidu.com/baidu?tn=monline_3_dg&ie=utf-8&wd=java
            //https://www.runoob.com/?s=java
            //2.输入网址，创建urlBuilder对象
            uriBuilder = new URIBuilder("https://www.runoob.com/");
            //3.封装请求参数
            uriBuilder.setParameter("s","java")
//                    .setParameter("ie","utf-8")
//                    .setParameter("wd","java")
                    .build();
            //4.创建get对象
            HttpGet httpGet = new HttpGet(String.valueOf(uriBuilder));
            //5.使用客户端对象发起请求
             response = httpClient.execute(httpGet);
            //6.解析返回对象
            if(response.getStatusLine().getStatusCode() == 200){
                //获取响应体
                HttpEntity entity = response.getEntity();
                //将响应体转成字符串，指定字符编码
                String content = EntityUtils.toString(entity, "utf-8");
                //打印数据长度
                System.out.println(content);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭请求连接
            if(response == null){
                try {
                    response.close();
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
