package com.hjc.utils;

import jakarta.annotation.Resource;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.util.Date;
import java.util.HashMap;

@Slf4j
@Component
public class MailUtils {
    @Resource
    private JavaMailSender sender;


    @Value("${spring.mail.username}")
    private String username;

    @Resource
    private TemplateEngine templateEngine;

    public String sendSimpleMessage(String targetEmail, String ccEmail, String bccEmail,
                                    String subject, String text) {
        //SimpleMailMessage是一个比较简易的邮件封装，支持设置一些比较简单内容
        SimpleMailMessage message = new SimpleMailMessage();
        //邮件发送者，这里要与配置文件中的保持一致
        message.setFrom(username);
        //设置邮件发送给谁，可以多个，这里就发给你的邮箱
        message.setTo(targetEmail);
        //抄送,收到邮件用户可以看到其他收件人
        if (ccEmail != null) {
            message.setCc(ccEmail);
        }
        //密送 收到邮件用户看不到其他收件人
        if (bccEmail != null) {
            message.setBcc(bccEmail);
        }
        //设置邮件标题
        message.setSubject(subject);
        //设置邮件内容
        message.setText(text);
        //设置当前时间为发送时间
        message.setSentDate(new Date());
        //OK，万事俱备只欠发送
        sender.send(message);
        log.info("邮件发送成功");
        return "邮件发送成功";
    }

    public String sendTextMail(String targetEmail, String ccEmail, String bccEmail,
                               String subject, String text, File tmpOne) throws MessagingException {
        //创建一个MimeMessage
        MimeMessage message = sender.createMimeMessage();
        //使用MimeMessageHelper来修改MimeMessage中的信息
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        //设置邮件发送给谁，可以多个，这里就发给你的邮箱
        helper.setTo(targetEmail);
        helper.setFrom(username);
        helper.setSentDate(new Date());
        //抄送,收到邮件用户可以看到其他收件人
        if (ccEmail != null) {
            helper.setCc(ccEmail);
        }
        //密送 收到邮件用户看不到其他收件人
        if (bccEmail != null) {
            helper.setBcc(bccEmail);
        }
        //设置邮件标题
        helper.setSubject(subject);
        //设置邮件内容
        helper.setText(text);
        //附件
        helper.addAttachment(tmpOne.getName(), tmpOne);
        //发送修改好的MimeMessage
        sender.send(message);
        log.info("邮件发送成功");
        return "邮件发送成功";
    }

    public String sendHtmlMail(String targetEmail, String ccEmail, String bccEmail,
                             String subject, HashMap<String, String> content) throws MessagingException {
        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(username);
        helper.setSubject(subject);
        helper.setTo(targetEmail);
        //抄送,收到邮件用户可以看到其他收件人
        if (ccEmail != null) {
            helper.setCc(ccEmail);
        }
        //密送 收到邮件用户看不到其他收件人
        if (bccEmail != null) {
            helper.setBcc(bccEmail);
        }
        helper.setSentDate(new Date());
        //生成邮件模板上的内容
        Context context = new Context();
        for (String key : content.keySet()) {
            context.setVariable(key, content.get(key));
        }
        String process = templateEngine.process("mail/sendMail.html", context);
        helper.setText(process, true);
        sender.send(mimeMessage);
        log.info("邮件发送成功");
        return "邮件发送成功";
    }
}
