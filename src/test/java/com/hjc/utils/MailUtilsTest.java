package com.hjc.utils;

import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MailUtilsTest {

    @Resource
    private MailUtils mailUtils;

    @Test
    void sendSimpleMessage() {
        String s = mailUtils.sendSimpleMessage("2020885569@qq.com", null, null,
                "测试邮件", "测试邮件内容");
        System.out.println("s = " + s);
    }

    @Test
    void sendTextMail() throws MessagingException {
        String s = mailUtils.sendTextMail("2020885569@qq.com", null, null,
                "测试邮件", "测试邮件内容", new File("D:\\项目-代驾.drawio.png"));
        System.out.println("s = " + s);
    }

    @Test
    void sendHtmlMail() throws MessagingException {
        String s = mailUtils.sendHtmlMail("2020885569@qq.com", null, null,
                "测试邮件",
                new HashMap<String, String>() {{
                    put("nickname", "hjc");
                    put("username", "hjc");
                    put("id", "18");
                }});
        System.out.println("s = " + s);
    }
}