package com.narwhal.mail;

import com.narwhal.mail.util.JavaMailSenderFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
class MailApplicationTests {
	@Autowired
	private JavaMailSenderFactory javaMailSenderFactory;
	@Test
	void contextLoads() {
		JavaMailSender sender = javaMailSenderFactory.getSender("1215349793@qq.com");
		//创建简单文本邮件对象
        SimpleMailMessage message = new SimpleMailMessage();
        //设置邮件信息主题
        message.setSubject("青藤招新程序");
		message.setText("hello");
        //设置收件人
        message.setTo("tf0800638gangc@163.com");
        //设置发件人
        message.setFrom("1215349793@qq.com");
		sender.send(message);
	}

}
