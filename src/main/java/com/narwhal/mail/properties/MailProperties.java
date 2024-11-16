package com.narwhal.mail.properties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Properties;

@Configuration
@Data
@ConfigurationProperties(prefix = "spring.mail")
public class MailProperties {
    private Map<String, MailConfig> configs;

    @Data
    @Component
    public static class MailConfig {
        private String host;
        private int port;
        private String username;
        private String password;
        private Properties properties = new Properties();
        // 对应的邮箱后缀
        private String suffix;
    }
}



