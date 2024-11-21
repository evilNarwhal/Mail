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
### 核心类：JavaMailSenderConditional
用于根据配置文件生成JavaMailSender
建立邮箱服务器名称与JavaMailSender的map集合并注入到Spring容器中


### 工具类： FormatCheck
用于不同组成不同后缀邮箱格式校验
#### 提供的组成：
    //只有数字
    NUM_EMAIL,
    //只有字母
    LETTER_EMAIL,
    //数字+小写字母+大写字母
    GENERIC_EMAIL


