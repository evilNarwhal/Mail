package com.narwhal.mail.config;

import com.narwhal.mail.util.JavaMailSenderFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnMissingBean(JavaMailSenderFactory.class)
@ConditionalOnClass(MailConfiguration.class)
public class MailConfiguration {
    @Bean
    public JavaMailSenderFactory javaMailSenderFactory() {
        return new JavaMailSenderFactory();
    }
}
