package com.tianshi.songzeyang.util;

import jakarta.annotation.Resource;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MailUtil
{

    @Resource
    private JavaMailSender javaMailSender;

    private static final Logger logger = LoggerFactory.getLogger(MailUtil.class);
    // 发件人邮箱
    private static final String FROM_EMAIL = "3539567386@qq.com";

    // 生成6位随机数字验证码
    public static String generateEmailCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }

    // 发送邮箱验证码
    public boolean sendEmailCode(String toEmail, String code) {
        try {
            // 1. 创建MIME邮件对象
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            // 2. 设置邮件信息
            helper.setFrom(FROM_EMAIL); // 发件人
            helper.setTo(toEmail); // 收件人
            helper.setSubject("银行管理系统 - 注册验证码"); // 标题
            helper.setText("尊敬的用户：\n您的注册验证码为：" + code + "\n有效期5分钟，请及时输入。", false); // 内容
            // 3. 发送邮件
            javaMailSender.send(mimeMessage);
            logger.info("验证码 {} 已发送至邮箱：{}", code, toEmail);
            System.out.println("验证码 " + code + " 已发送至邮箱：" + toEmail);
            return true;
        } catch (Exception e) {
            logger.error("发送邮箱验证码失败！邮箱：{}，异常：{}", toEmail, e.getMessage());
            System.out.println("发送邮箱验证码失败！邮箱：" + toEmail + "，异常：" + e.getMessage());
            return false;
        }
    }
}