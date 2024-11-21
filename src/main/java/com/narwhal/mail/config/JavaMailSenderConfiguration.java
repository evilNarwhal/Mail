package com.narwhal.mail.config;

import com.narwhal.mail.properties.MailProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@AutoConfiguration
@EnableConfigurationProperties(MailProperties.class)
@Slf4j
public class JavaMailSenderConfiguration {
    @Autowired
    private MailProperties mailProperties;
    private final Map<String, JavaMailSender> senders = new ConcurrentHashMap<>();

    //创建自定义邮箱发送器
    private JavaMailSender createJavaMailSender(MailProperties.MailConfig config) {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(config.getHost());
        javaMailSender.setPort(config.getPort());
        javaMailSender.setUsername(config.getUsername());
        javaMailSender.setPassword(config.getPassword());
        javaMailSender.setJavaMailProperties(config.getProperties());
        return javaMailSender;
    }

    @Bean
    public Map<String, JavaMailSender> getSenders() {
        if (mailProperties == null || mailProperties.getConfigs() == null) {
            throw new IllegalArgumentException("com.narwhal.mail.properties.MailProperties or Configs cannot be null");
        }

        for (Map.Entry<String, MailProperties.MailConfig> entry : mailProperties.getConfigs().entrySet()) {
            try {
                String name = entry.getKey();
                MailProperties.MailConfig config = entry.getValue();
                JavaMailSender javaMailSender = createJavaMailSender(config);
                senders.put(name, javaMailSender);
            } catch (Exception e) {
                log.error("Failed to create JavaMailSender for name: " + entry.getKey());
            }
        }
        return senders;
    }
}