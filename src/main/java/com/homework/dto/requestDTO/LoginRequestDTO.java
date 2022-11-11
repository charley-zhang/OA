package com.homework.dto.requestDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;

import java.io.Serializable;

@ApiModel(value = "登录",description = "用户登录的DTO")
public class LoginRequestDTO implements Serializable {

    private static final long serialVersionUID = 1237820235655340984L;

    @ApiModelProperty(value = "用户名",required = true,example = "123456789")
    private String username;
    @ApiModelProperty(value = "密码",required = true,example = "123456789")
    private String password;
    @ApiModelProperty(value = "用户输入的验证码",required = true,example = "123456789")
    private String verifyCode;
    @ApiModelProperty(value = "是否需要校验验证码",required = false,example = "123456789")
    private Boolean needVerify;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public Boolean getNeedVerify() {
        return needVerify;
    }

    public void setNeedVerify(Boolean needVerify) {
        this.needVerify = needVerify;
    }
}
