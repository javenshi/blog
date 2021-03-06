package com.centling.controller.blog;


import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.centling.config.WebConfigurer;
import com.centling.controller.LoginUserDto;
import com.centling.domain.User;
import com.centling.security.SecurityUtils;
import com.centling.service.UserService;
import com.centling.utils.OSSUtil;
import com.centling.utils.Result;
import com.centling.utils.blogUtils.HttpUtils;
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
import org.springframework.beans.factory.annotation.Value;
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
import java.rmi.ServerException;
import java.util.Properties;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/blogUser")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    OSSUtil ossUtil;
    private final org.slf4j.Logger log = LoggerFactory.getLogger(WebConfigurer.class);

    static final Properties props = new Properties();
    static{

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtpdm.aliyun.com");
        props.put("mail.smtp.port", 80);
        props.put("mail.user", "zxservice@zhixiang.org.cn");
        props.put("mail.password", "ABCDefg123");

    }


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

    @PostMapping(value = "/inser")
    public Result insert(@RequestBody User user) {
        userService.insertUser(user,0);
        return new Result(200,"注册成功");
    }


    @PostMapping(value = "/sendEmail")
    public Result sendEmail(@RequestBody LoginUserDto loginUserDto) {
        int rand = (int) (Math.random() * 999999);
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
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };

        Session mailSession = Session.getInstance(props, authenticator);

        MimeMessage message = new MimeMessage(mailSession);
        try {

            InternetAddress from = new InternetAddress(props.getProperty("mail.user"));
            message.setFrom(from);
            Address[] a = new Address[1];
            a[0] = new InternetAddress( props.getProperty("mail.user"));
            message.setReplyTo(a);

            InternetAddress to = new InternetAddress(loginUserDto.getEmail());
            message.setRecipient(MimeMessage.RecipientType.TO, to);

            message.setSubject("智享注册邮件");

            message.setContent(m, "text/html;charset=UTF-8");

            Transport.send(message);
        }
        catch (MessagingException e) {
            log.info("邮件异常："+e.getMessage());
           e.printStackTrace();

        }
        return new Result(200, "发送成功", rand + "");
    }
}
