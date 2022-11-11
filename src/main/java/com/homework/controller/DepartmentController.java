package com.homework.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.homework.dto.ToggleDepartmentRequestDTO;
import com.homework.dto.requestDTO.DepartmentListRequestDTO;
import com.homework.entity.Department;
import com.homework.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/department")
@Api(tags = "部门管理模块")
public class DepartmentController extends BaseController{


    @Autowired
    private DepartmentService departmentService;

    @ApiOperation(value = "跳转至列表的路由")
    @GetMapping("/toList")
    public String toList(){
        httpServletRequest.getSession().setAttribute("pageName","部门管理");
        return "department";
    }

    @ApiOperation(value = "跳转至部门修改的路由")
    @GetMapping("/toDepartmentEdit")
    public String toDepartmentEdit(){
        httpServletRequest.getSession().setAttribute("pageName","编辑部门");
        return "department-edit";
    }

    @ApiOperation(value = "跳转至查看某个部门信息的路由")
    @GetMapping("/toDepartmentInfo")
    public String toDepartmentInfo(){
        httpServletRequest.getSession().setAttribute("pageName","部门详情");
        return "department-info";
    }

    @ApiOperation(value = "跳转至新增部门的路由")
    @GetMapping("/toAddDepartment")
    public String toAddDepartment(){
        httpServletRequest.getSession().setAttribute("pageName","新增详情");
        return "department-add";
    }


    @ApiOperation(value = "获取部门列表")
    @PostMapping("/getList")
    @ResponseBody
    public Map<String ,Object> getList(@RequestBody DepartmentListRequestDTO departmentListrequestDTO){
        Map<String, Object> stringObjectHashMap = null;
        try {
            stringObjectHashMap = departmentService.queryByPage(departmentListrequestDTO);
        }catch (Exception e){
            new HashMap<String, Object>();
            stringObjectHashMap.put("success",false);
            stringObjectHashMap.put("errMsg",e.getMessage());
        }
        return stringObjectHashMap;
    }

    @ApiOperation(value = "切换单个部门状态")
    @PostMapping("/toggleDepartmentStatus")
    @ResponseBody
    public Map<String,Object> toggleDepartmentStatus(@RequestBody ToggleDepartmentRequestDTO toggleDepartmentRequestDTO){
        Map<String, Object> stringObjectHashMap = new HashMap<String, Object>();
        try {
            departmentService.toggleStatus(toggleDepartmentRequestDTO);
        }catch (Exception e){
            e.printStackTrace();
            stringObjectHashMap.put("success",false);
            stringObjectHashMap.put("errMsg",e.getMessage());
        }
        stringObjectHashMap.put("success",true);
        return stringObjectHashMap;
    }


    @ApiOperation(value = "根据id查询部门信息")
    @ResponseBody
    @PostMapping("queryDepartmentById")
    public Map<String,Object> queryDepartmentById(){
        Map<String, Object> stringObjectHashMap = new HashMap<String, Object>();
        Integer depId = Integer.decode(httpServletRequest.getParameter("depId"));
        try {
            Department department = departmentService.queryById(depId);
            stringObjectHashMap.put("success",true);
            stringObjectHashMap.put("data",department);
        }catch (Exception e){
            e.printStackTrace();
            stringObjectHashMap.put("success",false);
            stringObjectHashMap.put("errMsg",e.getMessage());
        }
        return stringObjectHashMap;
    }

    @ApiOperation(value = "编辑部门信息")
    @PostMapping("/editDepartment")
    @ResponseBody
    public Map<String,Object> editDepartment(@RequestParam String departmentStr){
        Map<String, Object> stringObjectHashMap = new HashMap<String, Object>();
        ObjectMapper objectMapper = new ObjectMapper();
        Department department = null;
        try{
            department = objectMapper.readValue(departmentStr, Department.class);
            departmentService.update(department);
            stringObjectHashMap.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            stringObjectHashMap.put("success",false);
            stringObjectHashMap.put("errMsg",e.getMessage());
        }
        return stringObjectHashMap;
    }

    @ApiOperation(value = "新增部门信息")
    @PostMapping("/insertDepartment")
    @ResponseBody
    public Map<String,Object> insertDepartment(@RequestParam String departmentStr){
        Map<String, Object> stringObjectHashMap = new HashMap<String, Object>();
        ObjectMapper objectMapper = new ObjectMapper();
        Department department = null;
        try{
            department = objectMapper.readValue(departmentStr, Department.class);
            departmentService.insert(department);
            stringObjectHashMap.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            stringObjectHashMap.put("success",false);
            stringObjectHashMap.put("errMsg",e.getMessage());
        }
        return stringObjectHashMap;
    }

    @PostMapping("/getActiveList")
    @ResponseBody
    public Map<String,Object> getActiveList(){
        Map<String, Object> stringObjectHashMap = null;
        try{
            stringObjectHashMap = departmentService.queryActiveList();
        }catch (Exception e){
            stringObjectHashMap = new HashMap<String, Object>();
            stringObjectHashMap.put("success",false);
            stringObjectHashMap.put("errMsg",e.getMessage());
        }
        return stringObjectHashMap;
    }
}
