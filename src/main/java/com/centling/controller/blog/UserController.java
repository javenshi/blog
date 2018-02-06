package com.centling.controller.blog;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.centling.controller.LoginUserDto;
import com.centling.domain.User;
import com.centling.security.SecurityUtils;
import com.centling.service.UserService;
import com.centling.utils.OSSUtil;
import com.centling.utils.Result;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
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
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/blogUser")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    OSSUtil ossUtil;



    @PostMapping(value = "/weiboReturn/{code}")
    public Result weiboReturn(@PathVariable String code) {
        return userService.weiboLogin(code);
    }
    @PostMapping(value = "/qqReturn/{code}")
    public Result qqReturn(@PathVariable String code) {
        return userService.qqLogin(code);
    }
 @PostMapping(value = "/getUserById/{id}")
    public Result getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }




     @PostMapping(value = "/login")
      public Result login(@RequestBody User user) {

         return userService.login(user);
      }
      @PostMapping(value = "/updateUser")
      public Result updateUser(@RequestBody User user) {

         return userService.updateUser(user);
      }
    @RequestMapping(value = "/upl",method= RequestMethod.POST,produces= MediaType.APPLICATION_JSON_VALUE)
    public Result upl(@RequestParam(value="file",required=false)MultipartFile file){
        OSSClient ossClient= ossUtil.getUploadOSSClient();
        String key=ossUtil.uploadToOSS(ossClient,"userater/",file);
        return new Result(key);
    }
      /*
      @GetMapping(value = "/cheackName/{name}")
      public Result cheackName(@PathVariable String name) {
          return userService.cheackName(name, 0);
      }

      @PostMapping(value = "/valUser")
      public Result insert1(@RequestBody String name) {
         if(name.equals(SecurityUtils.getCurrentUser())){
             return new Result();
         }
         return new Result(202,"");
      }

     */
    @PostMapping(value = "/inser")
    public Result insert(@RequestBody User user) {
        userService.insertUser(user,0);
        return new Result(200,"注册成功");
    }
    @PostMapping(value = "/sendEmail")
    public Result sendEmail(@RequestBody LoginUserDto loginUserDto) {
        int rand = (int) (Math.random() * 999999);
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.qq.com");
        javaMailSender.setPort(465);
        javaMailSender.setUsername("2388386839@qq.com");
        javaMailSender.setPassword("gpygdpumzbjqebbe");
        Properties properties = new Properties();
        properties.setProperty("mail.host", "smtp.qq.com");
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        javaMailSender.setJavaMailProperties(properties);
        MimeMessage mailMessage=javaMailSender.createMimeMessage();
        try {

            MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true, "utf-8");
            helper.setFrom("2388386839@qq.com");// 设置发件人
            helper.setTo(loginUserDto.getEmail());// 设置收件人
            String m = "<div style=\"background-color:#ECECEC; padding: 35px;\">\n" +
                    "<table cellpadding=\"0\" align=\"center\" style=\"width: 600px; margin: 0px auto; text-align: left; position: relative; border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 5px; border-bottom-left-radius: 5px; font-size: 14px; font-family:微软雅黑, 黑体; line-height: 1.5; box-shadow: rgb(153, 153, 153) 0px 0px 5px; border-collapse: collapse; background-position: initial initial; background-repeat: initial initial;background:#fff;\">\n" +
                    "<tbody>\n" +
                    "<tr>\n" +
                    "<th valign=\"middle\" style=\"height: 25px; line-height: 25px; padding: 15px 35px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #409EFF; background-color:#2a2f32; border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 0px; border-bottom-left-radius: 0px;\">\n" +
                    "<img src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHgAAAAUCAYAAABGUvnzAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAApxJREFUeNrsWeFN8zAQdRH/KROQEcIElAkaJqBM0HaCj05QmACYgDBBwwQNE1AmIBuEu49nyRjHsZMiHOGTrIBzsiM/v7t3VyECtrquk/rTHn9p/zsaazFgOwz8++7wTOmgNw0+t6PRKFdAmeDPiubLFgDHvLbFhdfiS/ZGT9taJe1ViWje7HGxhQSWxqv2bgMQm/bY1Pux18hgP3AZtBmzkMYpsWOH+ZoeBf1/rvkzC5nhzOQLGuyf0Vhj/rSJ/TSe9/DJZQTYzyqMFUJkooTSsRKG5eHOGVwC/kKZvyc/frel54ze3eubILTnuCD/eG3Pb+T0UMQc7G8JDtskcCRbpeXwXRoALAm8AuvZbI186wrWGP48jiPA/pYb5uYKw/j9i8pgi6ByFT87PfS3pJHak/ERYJV5al7jEIvDrBT2rCSoKKcyVU1rKvnBJWpYlLqJwQK5Pmg7GICaXijlEofhKxzwFsDzhbixhN5CB95gy4aoYcu/UtBFBncENkNYloLqSgqlz+j4H3QupbjezbVamIGfchin+WuH7dIOAuvFI/xH08C9VmtMqNxvF4DGu6yDe+637Vj/zkI/y1HAYXmq1KhnHZZ5MJVGHb+HL9glausEuXe1r/X/MpMnPTtMWd+LpnTH3tFdS4d0hqH3omX+vSG2LB1B4Vy6QOMitYkngGW7BLI0Y7X+hLnMcHHytr53BLilLOG87OF/4ujHALrk0VTYf5Q4EoG2K4cC8E+JmaVDjTxFROBIcNtQtxcxRPcHwochqTC3OXVguMwpWkK+AMDj0PvOQwb4xNLMMIqzDoKKc+2jUuOWyL9zuOyi7P2BZkdPFT3xARhK2WTW35VjHdwP5BnyoO8BP/mwXtkvEV9/fapCVcgu9iHAADsFkZstTZVnAAAAAElFTkSuQmCC\">\n" +
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
                    "如果您有什么疑问可以联系管理员，Email: ZXService@zhixiang.org.cn。</p>\n" +
                    "<p align=\"right\">智享官方团队</p>\n" +
                    "</div>\n" +
                    "</td>\n" +
                    "</tr>\n" +
                    "</tbody>\n" +
                    "</table>\n" +
                    "</div>";
            helper.setSubject("智享网注册邮件!");// 设置主题
            helper.setText(m,true);// 邮件体
            javaMailSender.send(mailMessage);// 发送邮件

        } catch (Exception e) {
           e.printStackTrace();
        }




        return new Result(200, "发送成功", rand + "");
    }
    /*   @PostMapping(value = "/sendEmail")
    public Result sendEmail(@RequestBody LoginUserDto loginUserDto) {
        String from = "ZXService@zhixiang.org.cn";
        Properties prop = new Properties();
        prop.put("mail.host", "zhixiang.org.cn");
        prop.put("mail.transport.protocol", "smtp");
        prop.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(prop);
        session.setDebug(true);
        Transport ts = null;
        int rand = (int) (Math.random() * 999999);
        try {
            ts = session.getTransport();
            ts.connect("zhixiang.org.cn", 25, from, "1234.abcd");
            MimeMessage mm = new MimeMessage(session);
            mm.setFrom(new InternetAddress(from));
            mm.setRecipient(Message.RecipientType.TO, new InternetAddress(loginUserDto.getEmail()));

            mm.setSubject("智享网注册邮件！");
            String m = "<div style=\"background-color:#ECECEC; padding: 35px;\">\n" +
                    "<table cellpadding=\"0\" align=\"center\" style=\"width: 600px; margin: 0px auto; text-align: left; position: relative; border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 5px; border-bottom-left-radius: 5px; font-size: 14px; font-family:微软雅黑, 黑体; line-height: 1.5; box-shadow: rgb(153, 153, 153) 0px 0px 5px; border-collapse: collapse; background-position: initial initial; background-repeat: initial initial;background:#fff;\">\n" +
                    "<tbody>\n" +
                    "<tr>\n" +
                    "<th valign=\"middle\" style=\"height: 25px; line-height: 25px; padding: 15px 35px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #409EFF; background-color:#2a2f32; border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 0px; border-bottom-left-radius: 0px;\">\n" +
                    "<img src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHgAAAAUCAYAAABGUvnzAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAApxJREFUeNrsWeFN8zAQdRH/KROQEcIElAkaJqBM0HaCj05QmACYgDBBwwQNE1AmIBuEu49nyRjHsZMiHOGTrIBzsiM/v7t3VyECtrquk/rTHn9p/zsaazFgOwz8++7wTOmgNw0+t6PRKFdAmeDPiubLFgDHvLbFhdfiS/ZGT9taJe1ViWje7HGxhQSWxqv2bgMQm/bY1Pux18hgP3AZtBmzkMYpsWOH+ZoeBf1/rvkzC5nhzOQLGuyf0Vhj/rSJ/TSe9/DJZQTYzyqMFUJkooTSsRKG5eHOGVwC/kKZvyc/frel54ze3eubILTnuCD/eG3Pb+T0UMQc7G8JDtskcCRbpeXwXRoALAm8AuvZbI186wrWGP48jiPA/pYb5uYKw/j9i8pgi6ByFT87PfS3pJHak/ERYJV5al7jEIvDrBT2rCSoKKcyVU1rKvnBJWpYlLqJwQK5Pmg7GICaXijlEofhKxzwFsDzhbixhN5CB95gy4aoYcu/UtBFBncENkNYloLqSgqlz+j4H3QupbjezbVamIGfchin+WuH7dIOAuvFI/xH08C9VmtMqNxvF4DGu6yDe+637Vj/zkI/y1HAYXmq1KhnHZZ5MJVGHb+HL9glausEuXe1r/X/MpMnPTtMWd+LpnTH3tFdS4d0hqH3omX+vSG2LB1B4Vy6QOMitYkngGW7BLI0Y7X+hLnMcHHytr53BLilLOG87OF/4ujHALrk0VTYf5Q4EoG2K4cC8E+JmaVDjTxFROBIcNtQtxcxRPcHwochqTC3OXVguMwpWkK+AMDj0PvOQwb4xNLMMIqzDoKKc+2jUuOWyL9zuOyi7P2BZkdPFT3xARhK2WTW35VjHdwP5BnyoO8BP/mwXtkvEV9/fapCVcgu9iHAADsFkZstTZVnAAAAAElFTkSuQmCC\">\n" +
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
                    "如果您有什么疑问可以联系管理员，Email: ZXService@zhixiang.org.cn。</p>\n" +
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
