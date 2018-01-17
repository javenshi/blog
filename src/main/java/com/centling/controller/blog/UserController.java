package com.centling.controller.blog;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.centling.controller.LoginUserDto;
import com.centling.domain.User;
import com.centling.security.SecurityUtils;
import com.centling.service.UserService;
import com.centling.utils.Result;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

@RestController
@RequestMapping("/api/blogUser")
public class UserController {
    @Autowired
    UserService userService;
    private final static String CLIENT_ID = "3191489564";
    private final static String CLIENT_SERCRET = "bad088883841d9d1be1a59011ac98fd7";
    private final static String GET_TOKEN_URL = "https://api.weibo.com/oauth2/access_token";
    private final static String REDIRECT_URI = "http://39.108.12.206";
    private final static String GET_USER_INFO = "https://api.weibo.com/2/users/show.json";



    @PostMapping(value = "/weiboReturn/{code}")
    public Result weiboReturn(@PathVariable String code) {
        System.out.println(code);
        JSONObject userInfo=null;
        try {
            String access_token = "";
            String uid = "";
            JSONObject token = getAccessToken(code);
            access_token = token.getString("access_token");
            uid = token.getString("uid");
             userInfo = getUserInfo(access_token, uid);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return new Result(userInfo);

    }

    private JSONObject getAccessToken(String code) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        StringBuilder sb = new StringBuilder();
        sb.append("grant_type=authorization_code");
        sb.append("&client_id=" + CLIENT_ID);
        sb.append("&client_secret=" + CLIENT_SERCRET);
        sb.append("&redirect_uri=" + REDIRECT_URI);
        sb.append("&code=" + code);
        HttpPost post = new HttpPost(GET_TOKEN_URL+"?client_id="+CLIENT_ID+"&client_secret="+CLIENT_SERCRET+"&grant_type=authorization_code&redirect_uri="+REDIRECT_URI+"&code="+code);
        StringEntity stringEntity = null;
        String result = null;
        try {
            stringEntity = new StringEntity(sb.toString());

            //post.setEntity(stringEntity);
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
            return json;
        }
    }

    private JSONObject getUserInfo(String access_token, String uid) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        StringBuilder sb = new StringBuilder();
        sb.append("?access_token=" + access_token);
        sb.append("&uid=" + uid);
        HttpPost post = new HttpPost(GET_USER_INFO);
        StringEntity stringEntity = null;
        String result = null;
        try {
            stringEntity = new StringEntity(sb.toString());
            post.setEntity(stringEntity);
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
            return json;
        }
    }
    /*  @PostMapping(value = "/login")
      public Result login(@RequestBody User user) {

         return userService.login(user);
      }
      @GetMapping(value = "/cheackName/{name}")
      public Result cheackName(@PathVariable String name) {
          return userService.cheackName(name, 0);
      }
      @PostMapping(value = "/inser")
      public Result insert(@RequestBody User user) {
          userService.insert(user);
          return new Result(200,"注册成功");
      }
      @PostMapping(value = "/valUser")
      public Result insert1(@RequestBody String name) {
         if(name.equals(SecurityUtils.getCurrentUser())){
             return new Result();
         }
         return new Result(202,"");
      }

      @PostMapping(value = "/sendEmail")
      public Result sendEmail(@RequestBody LoginUserDto loginUserDto) {
          String from = "ZXService@zhixiang.org.cn";
          Properties prop = new Properties();
          prop.put("mail.host", "smtp.zhixiang.org.cn");
          prop.put("mail.transport.protocol", "smtp");
          prop.put("mail.smtp.auth", "true");
          Session session = Session.getInstance(prop);
          session.setDebug(true);
          Transport ts = null;
          int rand = (int) (Math.random() * 999999);
          try {
              ts = session.getTransport();
              ts.connect("smtp.zhixiang.org.cn", 25, from, "1234.abcd");
              MimeMessage mm = new MimeMessage(session);
              mm.setFrom(new InternetAddress(from));
              mm.setRecipient(Message.RecipientType.TO, new InternetAddress(loginUserDto.getEmail()));

              mm.setSubject("智享网注册邮件！");
              String m = "<div style=\"background-color:#ECECEC; padding: 35px;\">\n" +
                      "<table cellpadding=\"0\" align=\"center\" style=\"width: 600px; margin: 0px auto; text-align: left; position: relative; border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 5px; border-bottom-left-radius: 5px; font-size: 14px; font-family:微软雅黑, 黑体; line-height: 1.5; box-shadow: rgb(153, 153, 153) 0px 0px 5px; border-collapse: collapse; background-position: initial initial; background-repeat: initial initial;background:#fff;\">\n" +
                      "<tbody>\n" +
                      "<tr>\n" +
                      "<th valign=\"middle\" style=\"height: 25px; line-height: 25px; padding: 15px 35px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #409EFF; background-color:#409EFF; border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 0px; border-bottom-left-radius: 0px;\">\n" +
                      "<font face=\"微软雅黑\" size=\"5\" style=\"color: rgb(255, 255, 255); \">智享</font>\n" +
                      "</th>\n" +
                      "</tr>\n" +
                      "<tr>\n" +
                      "<td>\n" +
                      "<div style=\"padding:25px 35px 40px; background-color:#fff;\">\n" +
                      "<h2 style=\"margin: 5px 0px; \"><font color=\"#333333\" style=\"line-height: 20px; \"><font style=\"line-height: 22px; \" size=\"4\">亲爱的：" + loginUserDto.getUserName() + "</font></font></h2>\n" +
                      "<p>首先感谢您加入智享！\n" +
                      "这是您的注册验证码：<span style='color:#409EFF;'>" + rand + "</span><br>\n" +
                      "请您在发表言论时，遵守当地法律法规。<br>\n" +
                      "智享网，智享天下。 <a href='http://www.zhixiang.org.cn' target='_blank'>http://www.zhixiang.org.cn</a><br>\n" +
                      "如果您有什么疑问可以联系管理员，Email: admin@zhixiang.org.cn。</p>\n" +
                      "<p align=\"right\">智享官方团队</p>\n" +
                      "</div>\n" +
                      "</td>\n" +
                      "</tr>\n" +
                      "</tbody>\n" +
                      "</table>\n" +
                      "</div>";
              mm.setContent(m, "text/html;charset=gbk");
              ts.sendMessage(mm, mm.getAllRecipients());
          } catch (Exception e) {
              e.printStackTrace();
          }
          return new Result(200, "发送成功", rand + "");
      }*/
}
