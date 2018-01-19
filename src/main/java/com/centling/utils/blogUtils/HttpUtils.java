package com.centling.utils.blogUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.centling.controller.blog.UserController;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class HttpUtils {
    private final org.slf4j.Logger log = LoggerFactory.getLogger(HttpUtils.class);

    public static JSONObject post(String url){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        String result = null;
        try {
            result = EntityUtils.toString(httpClient.execute(post).getEntity());
            httpClient.close();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (ClientProtocolException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            return new JSONObject(JSON.parseObject(result));
        }
    }
    public static JSONObject get(String url){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        String result = null;
        try {
            result = EntityUtils.toString(httpClient.execute(get).getEntity());
            httpClient.close();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (ClientProtocolException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            return new JSONObject(JSON.parseObject(result));
        }
    }
}
