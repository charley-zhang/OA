package com.homework.controller;


import com.homework.dto.EmployeeDTO;
import com.homework.dto.requestDTO.LoginRequestDTO;
import com.homework.service.DepartmentService;
import com.homework.service.EmployeeService;
import com.homework.util.CodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.awt.SunHints;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@Api(tags = "登录登出模块")
public class IndexController extends BaseController {

    @Autowired
    private EmployeeService employeeService;

    @ApiOperation(value = "跳转到登录的路由")
//    @RequestMapping("/login")
    @GetMapping("/login")
    public String login(){
        return "login";
    }


    @ApiOperation(value = "登录校验")
    @PostMapping("/checkLogin")
    @ResponseBody
    public Map<String,Object> checkLogin(@RequestBody LoginRequestDTO loginRequestDTO){
        HashMap<String, Object> objectObjectHashMap = new HashMap();
        //是否需要对验证码进行验证
        Boolean needVerify = loginRequestDTO.getNeedVerify();
        if (needVerify && !CodeUtil.checkVerifyCode(loginRequestDTO.getVerifyCode(),httpServletRequest)){
            objectObjectHashMap.put("success",false);
            objectObjectHashMap.put("errMsg","验证码错误，请重新登录");
            return objectObjectHashMap;
        }

        //参数校验
        String username = loginRequestDTO.getUsername();
        String password = loginRequestDTO.getPassword();
        if (username!=null&&password!=null&&!"".equals(username)&&!"".equals(password)){
            EmployeeDTO employeeDTO = employeeService.GetEmIfnoByUsernameAndPassword(username,password);
            if (null!=employeeDTO){
               objectObjectHashMap.put("success",true);
                httpServletRequest.getSession().setAttribute("employeeDTO",employeeDTO);
            }else {
                objectObjectHashMap.put("success",false);
                objectObjectHashMap.put("errMsg","用户名或密码错误");
            }
        }else {
            objectObjectHashMap.put("success",false);
            objectObjectHashMap.put("errMsg","用户名且密码不能为空");
        }
        return objectObjectHashMap;
    }


    @GetMapping("/main")
    @ApiOperation(value = "主页面路由")
    public String mainPage(){
        httpServletRequest.getSession().setAttribute("pageName","首页");
        return "main";
    }
    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    @ResponseBody
    public Map<String,Object> quit(){
        Map<String,Object> map = new HashMap<String, Object>();
        httpServletRequest.getSession().setAttribute("employeeDTO",null);
        map.put("success",true);
        return map;
    }



}
