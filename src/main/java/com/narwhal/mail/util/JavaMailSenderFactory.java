package com.narwhal.mail.util;

import com.narwhal.mail.properties.MailProperties;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JavaMailSenderFactory {
    @Autowired
    private MailProperties mailProperties;
    //邮箱后缀名对应的配置文件中邮箱服务器名称
    private final Map<String, JavaMailSender> senders = new ConcurrentHashMap<>();
    @PostConstruct
    //初始化后缀对应的邮箱服务器
    private void initSenders() {
        if (mailProperties == null || mailProperties.getConfigs() == null) {
            throw new IllegalArgumentException("com.narwhal.mail.properties.MailProperties or Configs cannot be null");
        }

        for (Map.Entry<String, MailProperties.MailConfig> entry : mailProperties.getConfigs().entrySet() ) {
            try {
                String name = entry.getKey();
                MailProperties.MailConfig config = entry.getValue();
                String suffix = config.getSuffix();
                JavaMailSender javaMailSender = createJavaMailSender(config);
                if(suffix != null)
                    senders.put(suffix, javaMailSender);
                senders.put(name, javaMailSender);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //通过邮箱参数的后缀获取邮箱服务器
    public JavaMailSender getSenderBySuffix(String email){
        if (email == null)
            throw new IllegalArgumentException("email cannot be null");
        String suffix = email.substring(email.lastIndexOf("@"));
        return senders.get(suffix);
    }
    //根据配置文件中的邮箱服务器名称获取邮箱服务器
    public JavaMailSender getSender(String name)
    {
        try {
            return senders.get(name);
        } catch (Exception e) {
            throw new IllegalArgumentException("name not found");
        }
    }
    //创建自定义邮箱发送器
    public JavaMailSender createJavaMailSender(MailProperties.MailConfig config){
            JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
            javaMailSender.setHost(config.getHost());
            javaMailSender.setPort(config.getPort());
            javaMailSender.setUsername(config.getUsername());
            javaMailSender.setPassword(config.getPassword());
            javaMailSender.setJavaMailProperties(config.getProperties());
            return javaMailSender;
    }
    //在senders中设置自定义后缀与邮箱服务器的关联（后缀不能与配置文件中出现过的相同）
    public void setMappings(String suffix, JavaMailSender javaMailSender){
        if (suffix == null)
            throw new IllegalArgumentException("suffix cannot be null");
        senders.put(suffix, javaMailSender);
    }
}
