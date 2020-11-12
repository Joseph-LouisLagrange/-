package com.alpha.classpie.util;

import com.alpha.classpie.type.UserNameType;

/**
 * @author 杨能
 * @create 2020/11/7
 */
public class FormatRecognitionUtil {
    public static UserNameType identifyUserName(String username){
        if(username.matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")){
            //邮箱
            return UserNameType.EMAIL;
        }else if(username.matches("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$")){
            //手机号
            return UserNameType.PHONE;
        }else {
            return UserNameType.ACCOUNT;
        }
    }
}
