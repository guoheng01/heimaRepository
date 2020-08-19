package cn.itcast.crawler.test;


import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * 有参get请求
 */
public class HttpGetParamTest {

    public static void main(String[] args) {

        //8.创建连接池管理器
        PoolingHttpClientConnectionManager phccm = new PoolingHttpClientConnectionManager();
            //8.1设置最大连接数
            phccm.setMaxTotal(200);
            //8.2设置每个服务主机最大并发数
            phccm.setDefaultMaxPerRoute(20);
        //9.使用连接池管理器发送请求
            doGet(phccm);
            doGet(phccm);
    }
    private static void doGet(PoolingHttpClientConnectionManager phccm){

        //1.打开浏览器，从连接池管理器中获取HTTPClient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(phccm).build();
        URIBuilder uriBuilder = null;
        CloseableHttpResponse response = null;
        try {
            //https://www.baidu.com/baidu?tn=monline_3_dg&ie=utf-8&wd=java
            //https://www.runoob.com/?s=java
            //2.输入网址，创建urlBuilder对象
            uriBuilder = new URIBuilder("https://www.baidu.com/baidu");
            //3.封装请求参数
            uriBuilder.setParameter("s","java")
                    .setParameter("ie","utf-8")
                    .setParameter("wd","java")
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
            //7.关闭请求连接
            if(response == null){
                try {
                    response.close();
                    //从连接池获取的连接不需要关闭
                    // httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
