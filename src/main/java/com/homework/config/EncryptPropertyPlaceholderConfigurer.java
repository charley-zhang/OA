package com.homework.config;

import com.homework.util.DESutil;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.io.IOException;
import java.util.Properties;


//老版本
//public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
//需要解密的字段
//private String[] encryptProp = {"jdbc.driverClassName","jdbc.url","jdbc.username","jdbc.password"};
//
//
//private boolean isEncrypt(String string){
//        for (String string1:encryptProp)
//        if (string1.equals(string))
//        return true;
//        return false;
//        }
//
///**
// * 旧版本用这个方法加载配置
// * @param propertyName  key
// * @param propertyValue   value
// * @return
// */
//@Override
//protected String convertProperty(String propertyName, String propertyValue) {
//        //判断字段是否在加密字段中
//        if (isEncrypt(propertyName))
//        //如果是，就解密后在加载
//        return DESutil.getDecryptString(propertyValue);
//        return propertyValue;
//
//        }
//
//
//        }








//新版本
public class EncryptPropertyPlaceholderConfigurer extends PropertySourcesPlaceholderConfigurer {

    //需要解密的字段
    private String[] encryptProp = {"jdbc.driverClassName","jdbc.url","jdbc.username","jdbc.password"};

    private boolean isEncrypt(String string){
        for (String string1:encryptProp)
            if (string1.equals(string))
                return true;
            return false;
        }



    /**
     * 新版本用这个方法加载配置
     * @param props
     * @throws IOException
     */
    @Override
    protected void loadProperties(Properties props) throws IOException {
        super.loadProperties(props);
        for (String stringPropertyName : props.stringPropertyNames()) {
            //判断字段是否在加密字段中
            if (isEncrypt(stringPropertyName)) {
                System.out.println("***********解密"+stringPropertyName);
                //如果是，就解密后在加载
                String decryptString = DESutil.getDecryptString(props.getProperty(stringPropertyName));
                props.setProperty(stringPropertyName,decryptString);
            }
        }
    }
}
