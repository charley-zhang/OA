package com.homework.util;

import com.google.code.kaptcha.Constants;

import javax.servlet.http.HttpServletRequest;

public class CodeUtil {

    public static boolean checkVerifyCode(String string, HttpServletRequest httpServletRequest){
        String attribute = ((String) httpServletRequest.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY)).toUpperCase();
        if (string==null)
            return false;
        //为了防止用户输入验证码，防止大小写原因失败，因此同意转换为大写
        string = string.toUpperCase();
        return attribute.equals(string);
    }
}
