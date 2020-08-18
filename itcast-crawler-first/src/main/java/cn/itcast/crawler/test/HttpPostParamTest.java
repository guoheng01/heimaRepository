package cn.itcast.crawler.test;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
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
        //1.打开浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //2.输入请求地址
        HttpPost httpPost = new HttpPost("https://www.baidu.com/baidu");
        //3.List集合封装表单请求参数
        ArrayList<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("tn","monline_3_dg"));
        params.add(new BasicNameValuePair("ie","utf-8"));
        params.add(new BasicNameValuePair("wd","java"));
        //4.创建表单的实体对象，将请求参数放到实体对象中
        try {
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "utf-8");
            //5.将参数实体对象放到httpPost请求对象中
            httpPost.setEntity(formEntity);
            //6.使用httpClient对象发起请求
            CloseableHttpResponse response = httpClient.execute(httpPost);
            //6.解析响应对象
                //6.1判断响应状态码是否为200
                if(response.getStatusLine().getStatusCode() == 200){
                    //获取响应体
                    HttpEntity entity = response.getEntity();
                    //将响应体转换成字符串
                    String content = EntityUtils.toString(entity, "utf-8");
                    System.out.println(content);
                }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }}
