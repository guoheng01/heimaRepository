package cn.itcast.crawler.test;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * 有参post请求
 */
public class HttpPostParamTest {
    public static void main(String[] args) {
        //7.创建连接池管理器
        PoolingHttpClientConnectionManager phccm = new PoolingHttpClientConnectionManager();
            //7.1设置最大连接数
            phccm.setMaxTotal(200);
            //7.2设置每个服务主机的最大并发数
            phccm.setDefaultMaxPerRoute(20);
        //8.使用连接池管理器发送请求
        doPost(phccm);
        doPost(phccm);
    }
    private static void doPost(PoolingHttpClientConnectionManager phccm){
        //1.打开浏览器，从连接池管理器中获取HTTPClient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(phccm).build();
        //2.输入请求地址 https://www.runoob.com/?s=java
        HttpPost httpPost = new HttpPost("https://www.runoob.com/");
        //3.List集合封装表单请求参数
        ArrayList<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("s","java"));
//        params.add(new BasicNameValuePair("ie","utf-8"));
//        params.add(new BasicNameValuePair("wd","java"));
        //4.创建表单的实体对象，将请求参数放到实体对象中
        CloseableHttpResponse response = null;
        try {
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "utf-8");
            //5.将参数实体对象放到httpPost请求对象中
            httpPost.setEntity(formEntity);
            //6.使用httpClient对象发起请求
           response = httpClient.execute(httpPost);
            //7.解析响应对象
            //7.1判断响应状态码是否为200
            if(response.getStatusLine().getStatusCode() == 200){
                //7.2获取响应体
                HttpEntity entity = response.getEntity();
                //7.3将响应体转换成字符串
                String content = EntityUtils.toString(entity, "utf-8");
                System.out.println(content);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //释放连接
            try {
                response.close();
                //不能关闭HttpClient
                //httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
