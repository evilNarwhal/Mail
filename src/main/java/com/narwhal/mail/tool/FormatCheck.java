package com.narwhal.mail.tool;

import com.narwhal.mail.constant.FormatType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatCheck {
     /**
     * 校验qq邮箱：数字 + qq.com
     */
    public static boolean QQEmailCheck(String email){
        //定义正则表达式
        String regex = "^\\d+@qq\\.com$";
        //生成编译对象
        Pattern compile = Pattern.compile(regex);
        //生成校验对象
        Matcher matcher = compile.matcher(email);
        //返回比对结果
        return matcher.matches();
    }

    //邮箱+后缀校验
     public static String EmailFormat(FormatType formatType, String suffix) {
         String type = switch (formatType) {
             case NUM_EMAIL -> "[0-9]";
             case GENERIC_EMAIL -> "[A-Za-z0-9+_.-]";
             case LETTER_EMAIL -> "[a-zA-Z]";
         };
         // 构建正则表达式
         StringBuilder regex = new StringBuilder();
         regex.append("^")
                 .append(type)
                 .append("+@")
                 .append(suffix)
                 .append("$");
         return regex.toString();
     }

     //通用邮箱格式校验
     public static String EmailFormat(FormatType formatType) {
         return switch (formatType) {
             case NUM_EMAIL -> "\\d+@[A-Za-z0-9.-]";
             case GENERIC_EMAIL -> "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
             case LETTER_EMAIL -> "^[a-zA-Z]+@[a-zA-Z]+\\.[a-zA-Z]{2,}$";
         };
     }

}
