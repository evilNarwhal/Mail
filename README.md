# Mail
## 目的：实现多种不同平台的邮件发送功能,灵活地配置和使用多个邮箱账号，满足不同场景的需求。
对spring-mail进行简单封装，可根据配置文件生成多种JavaMailSender
## 使用说明：
1. 在pom.xml中引入依赖：
2. 在application.yml中配置邮件信息，支持多个邮箱账号
### 配置示例:
    spring: 
        mail:
            configs:
              qq:
                host: smtp.qq.com
                port: 465
                username: xxxxxxxxx@qq.com
                password: xxxxxxxxxxxxx
                properties:
                  mail:
                    smtp:
                      ssl:
                        enable: true
                suffix: "@qq.com"
              gmail:
                host: smtp.gmail.com
                port: 465
                username: xxxxxx@gmail.com
                password: xxxxxxxxxx
                properties:
                  mail:
                    smtp:
                      ssl:
                        enable: true
                suffix: "@gmail.com"
其中suffix为邮箱后缀，如：@qq.com,用于接收邮箱账号并通过后缀匹配对应的sender
### 核心类：JavaMailSenderFactory
通过自动注入JavaMailSenderFactory，即可调用相应方法获取到对应的JavaMailSender
#### getSenderBySuffix(String email)
通过邮箱参数的后缀获取邮箱服务器
#### getSender(String name)
通过配置文件中的名称获取邮箱服务器（如示例中的qq，gmail等）
#### createJavaMailSender(MailProperties.MailConfig config)
创建自定义sender，简化spring-mail的原装配方法
#### setMappings(String suffix, JavaMailSender javaMailSender)
设置自定义后缀与邮箱服务器的关联（后缀不能与配置文件中出现过的相同）
### 工具类： FormatCheck
用于不同组成不同后缀邮箱格式校验
#### 提供的组成：
    //只有数字
    NUM_EMAIL,
    //只有字母
    LETTER_EMAIL,
    //数字+小写字母+大写字母
    GENERIC_EMAIL


