package com.centling.controller.blog;

import com.centling.controller.LoginUserDto;
import com.centling.domain.User;
import com.centling.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

@RestController
@RequestMapping("/api/blogUser")
public class UserController {

    @PostMapping(value = "/sendEmail")
    public Result sendEmail(@RequestBody LoginUserDto loginUserDto) {
        String from = "ZXService@zhixiang.org.cn";
        Properties prop=new Properties();
        prop.put("mail.host","smtp.zhixiang.org.cn" );
        prop.put("mail.transport.protocol", "smtp");
        prop.put("mail.smtp.auth", "true");
        Session session=Session.getInstance(prop);
        session.setDebug(true);
        Transport ts= null;
        int rand= (int) (Math.random() * 999999);
        try {
            ts = session.getTransport();
        ts.connect("smtp.zhixiang.org.cn",25,from, "1234.abcd");
        MimeMessage mm=new MimeMessage(session);
        mm.setFrom(new InternetAddress(from));
        mm.setRecipient(Message.RecipientType.TO, new InternetAddress(loginUserDto.getEmail()));

        mm.setSubject("智享网注册邮件！");
        String m="<div style=\"background-color:#ECECEC; padding: 35px;\">\n" +
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
                "<h2 style=\"margin: 5px 0px; \"><font color=\"#333333\" style=\"line-height: 20px; \"><font style=\"line-height: 22px; \" size=\"4\">亲爱的："+loginUserDto.getUserName()+"</font></font></h2>\n" +
                "<p>首先感谢您加入智享！\n" +
                "这是您的注册验证码：<span style='color:#409EFF;'>"+rand+"</span><br>\n" +
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
        return new Result(200,"发送成功",rand+"");
    }
}
