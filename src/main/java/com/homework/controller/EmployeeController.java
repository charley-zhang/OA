package com.homework.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.homework.dto.requestDTO.EmployeeListRequestDTO;
import com.homework.dto.requestDTO.ToggleEmployeeRequestDTO;
import com.homework.dto.responseDTO.EmployeeResponseDTO;
import com.homework.entity.Employee;
import com.homework.entity.Employee;
import com.homework.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/employee")
@Api(tags = "员工管理模块")
public class EmployeeController extends BaseController{

    @Autowired
    private EmployeeService employeeService;

    @ApiOperation(value = "跳转至列表的路由")
    @GetMapping("/toList")
    public String toList(){
        httpServletRequest.getSession().setAttribute("pageName","员工管理");
        return "employee-list";
    }

    @ApiOperation(value = "跳转至查看员工详情信息的路由")
    @GetMapping("/toEmployeeInfo")
    public String toEmployeeInfo(){
        httpServletRequest.getSession().setAttribute("pageName","员工详情");
        return "employee-info";
    }


    @ApiOperation(value = "跳转至修改员工详情信息的路由")
    @GetMapping("/toEmployeeEdit")
    public String toEmployeeEdit(){
        httpServletRequest.getSession().setAttribute("pageName","修改员工详情");
        return "employee-edit";
    }

    @ApiOperation(value = "跳转至新增员工信息的路由")
    @GetMapping("/toAddEmployee")
    public String toAddEmployee(){
        httpServletRequest.getSession().setAttribute("pageName","新增员工详情");
        return "employee-add";
    }

    @ApiOperation(value = "获取员工列表")
    @PostMapping("/getList")
    @ResponseBody
    public Map<String ,Object> getList(@RequestBody EmployeeListRequestDTO employeeListrequestDTO){
        Map<String, Object> stringObjectHashMap = null;
        try {
            stringObjectHashMap = employeeService.queryByPage(employeeListrequestDTO);
        }catch (Exception e){
            new HashMap<String, Object>();
            stringObjectHashMap.put("success",false);
            stringObjectHashMap.put("errMsg",e.getMessage());
        }
        return stringObjectHashMap;
    }


    @ApiOperation(value = "切换单个员工状态")
    @PostMapping("/toggleEmployeeStatus")
    @ResponseBody
    public Map<String,Object> toggleEmployeeStatus(@RequestBody ToggleEmployeeRequestDTO toggleEmployeeRequestDTO){
         Map<String, Object> stringObjectHashMap = new HashMap<String, Object>();
        try {
            employeeService.toggleStatus(toggleEmployeeRequestDTO);
        }catch (Exception e){
            e.printStackTrace();
            stringObjectHashMap.put("success",false);
            stringObjectHashMap.put("errMsg",e.getMessage());
        }
        stringObjectHashMap.put("success",true);
        return stringObjectHashMap;
    }

    @ApiOperation(value = "根据id查询员工信息")
    @ResponseBody
    @PostMapping("queryEmployeeById")
    public Map<String,Object> queryEmployeeById(){
        Map<String, Object> stringObjectHashMap = new HashMap<String, Object>();
        Integer emId = Integer.decode(httpServletRequest.getParameter("emId"));
        try {
            EmployeeResponseDTO employeeResponseDTO = employeeService.queryById(emId);
            stringObjectHashMap.put("success",true);
            stringObjectHashMap.put("data",employeeResponseDTO);
        }catch (Exception e){
            e.printStackTrace();
            stringObjectHashMap.put("success",false);
            stringObjectHashMap.put("errMsg",e.getMessage());
        }
        return stringObjectHashMap;
    }

    
    @ApiOperation(value = "编辑员工信息")
    @PostMapping("/editEmployee")
    @ResponseBody
    public Map<String,Object> editEmployee(@RequestParam String employeeStr){
        Map<String, Object> stringObjectHashMap = new HashMap<String, Object>();
        ObjectMapper objectMapper = new ObjectMapper();
        Employee employee = null;
        try{
            employee = objectMapper.readValue(employeeStr, Employee.class);
            employeeService.update(employee);
            stringObjectHashMap.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            stringObjectHashMap.put("success",false);
            stringObjectHashMap.put("errMsg",e.getMessage());
        }
        return stringObjectHashMap;
    }


    @ApiOperation(value = "新增员工信息")
    @PostMapping("/insertEmployee")
    @ResponseBody
    public Map<String,Object> insertEmployee(@RequestParam String employeeStr){
        Map<String, Object> stringObjectHashMap = new HashMap<String, Object>();
        ObjectMapper objectMapper = new ObjectMapper();
        Employee employee = null;
        try{
            employee = objectMapper.readValue(employeeStr, Employee.class);
            employeeService.insert(employee);
            stringObjectHashMap.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            stringObjectHashMap.put("success",false);
            stringObjectHashMap.put("errMsg",e.getMessage());
        }
        return stringObjectHashMap;
    }

}
