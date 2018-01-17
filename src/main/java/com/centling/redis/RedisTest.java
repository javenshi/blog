package com.centling.redis;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RedisTest {
    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String  url="https://api.weibo.com/oauth2/access_token?client_id=3191489564&client_secret=bad088883841d9d1be1a59011ac98fd7&grant_type=authorization_code&redirect_uri=http://39.108.12.206&code=d76bd1a8e204af1c3e4155bd6caedc50";

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

            JSONObject json = new JSONObject(JSON.parseObject(result));
            System.out.println(json);
        }
    }
}
