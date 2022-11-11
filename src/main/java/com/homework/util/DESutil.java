package com.homework.util;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 堆成加密工具
 */
public class DESutil {

    private static String ALGORITHM = "DES";
    private static String KEY_SEED = "myKey";
    private static String CHAR_SET = "UTF-8";
    private static Key key;

    static {
        try {
            //生成DES算法对象
            KeyGenerator instance = KeyGenerator.getInstance(ALGORITHM);
            //设置SHA1安全策略
            SecureRandom sha1PRNG = SecureRandom.getInstance("SHA1PRNG");
            //设置密钥种子
            sha1PRNG.setSeed(KEY_SEED.getBytes());
            instance.init(sha1PRNG);
            key = instance.generateKey();
            instance = null;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


    /**
     * 加密
     */
    public static String getEncryptString(String string){

            //原文按照指定编码格式转换成字节数组
            byte[] bytes = new byte[0];
            try {
                bytes = string.getBytes(CHAR_SET);
                //获取加密对象
                Cipher cipher = Cipher.getInstance(ALGORITHM);
                //初始化加密对象
                cipher.init(Cipher.ENCRYPT_MODE, key);
                byte[] bytes1 = new byte[0];
                bytes1 = cipher.doFinal(bytes);
                return new BASE64Encoder().encode(bytes1);
            } catch (Exception e) {
                throw new RuntimeException("加密异常",e);
            }


    }


    /**
     *解密
     */
    public static String getDecryptString(String string){
        //获取密文的字节数组
        byte[] bytes = new byte[0];
        try {
            bytes = new BASE64Decoder().decodeBuffer(string);
            //获取解密对象
            Cipher  instance = Cipher.getInstance(ALGORITHM);
            //初始化解密对象
            instance.init(Cipher.DECRYPT_MODE,key);
            byte[] bytes1 = instance.doFinal(bytes);
            return new String(bytes1);
        } catch (Exception e) {
            throw new RuntimeException("解密异常",e);
        }
    }




    public static void main(String[] args) {
        System.out.println(getEncryptString("com.mysql.cj.jdbc.Driver"));
        System.out.println(getDecryptString("8zQIdS8RzOBmd4qRctgYdYM/fdvrjnIJHyQH1bD/jNs="));

    }

}
