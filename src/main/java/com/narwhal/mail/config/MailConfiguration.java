package com.narwhal.mail.config;

import com.narwhal.mail.properties.MailProperties;
import com.narwhal.mail.util.JavaMailSenderFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@Import(MailProperties.class)
@ConditionalOnMissingBean(JavaMailSenderFactory.class)
public class MailConfiguration {
    @Bean
    public JavaMailSenderFactory javaMailSenderFactory() {
        return new JavaMailSenderFactory();
    }


}
