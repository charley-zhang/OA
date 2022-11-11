package com.homework.util;

import org.springframework.beans.factory.SmartInitializingSingleton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5til {
    /**
     * 定义16为数组
     * @param string
     * @return
     */
    private static final char[] SALT={
            '1','3','f','6','-','5','i','`','s',';','0','p','[','7','g','e','d','k','+','n','h'
    };

    public static String getMd5(String string){
        try {
            //获取md5加密对象
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(string.getBytes());
            byte[] bytes = md5.digest();
            char[] chars = new char[bytes.length * 2];
            for (int i = 0,k=0; i < bytes.length; i++) {
                byte aByte = bytes[i];
                chars[k++]=SALT[aByte>>>4 & 0xf];
                chars[k++]=SALT[aByte>>>4 & 0x8];
            }
            return new String(chars);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(getMd5("123456"));
    }
}
