package com.homework.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.homework.dto.TogglePositionRequestDTO;
import com.homework.dto.requestDTO.PositionListRequestDTO;
import com.homework.entity.Position;
import com.homework.service.PositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/position")
@Api(tags = "职位管理模块")
public class PositionCountroller extends BaseController {

    @Autowired
    private PositionService positionService;

    @ApiOperation(value = "跳转至列表的路由")
    @GetMapping("toList")
    public String toList(){
        httpServletRequest.getSession().setAttribute("pageName","职位管理");
        return "position-list";
    }

    @ApiOperation(value = "跳转至新增职位的路由")
    @GetMapping("/toAddPosition")
    public String toAddPosition(){
        httpServletRequest.getSession().setAttribute("pageName","新增详情");
        return "position-add";
    }

    @ApiOperation(value = "跳转至职位修改的路由")
    @GetMapping("/toPositionEdit")
    public String toPositionEdit(){
        httpServletRequest.getSession().setAttribute("pageName","编辑职位");
        return "position-edit";
    }

    @ApiOperation(value = "跳转至查看某个职位信息的路由")
    @GetMapping("/toPositionInfo")
    public String toPositionInfo(){
        httpServletRequest.getSession().setAttribute("pageName","职位详情");
        return "position-info";
    }

    @ApiOperation(value = "获取职位列表")
    @PostMapping("/getList")
    @ResponseBody
    public Map<String ,Object> getList(@RequestBody PositionListRequestDTO positionListrequestDTO){
        Map<String, Object> stringObjectHashMap = null;
        try {
            stringObjectHashMap = positionService.queryByPage(positionListrequestDTO);
        }catch (Exception e){
            new HashMap<String, Object>();
            stringObjectHashMap.put("success",false);
            stringObjectHashMap.put("errMsg",e.getMessage());
        }
        return stringObjectHashMap;
    }

    @ApiOperation(value = "切换单个职位状态")
    @PostMapping("/togglePositionStatus")
    @ResponseBody
    public Map<String,Object> togglePositionStatus(@RequestBody TogglePositionRequestDTO togglePositionRequestDTO){
        Map<String, Object> stringObjectHashMap = new HashMap<String, Object>();
        try {
            positionService.toggleStatus(togglePositionRequestDTO);
        }catch (Exception e){
            e.printStackTrace();
            stringObjectHashMap.put("success",false);
            stringObjectHashMap.put("errMsg",e.getMessage());
        }
        stringObjectHashMap.put("success",true);
        return stringObjectHashMap;
    }


    @ApiOperation(value = "根据id查询职位信息")
    @ResponseBody
    @PostMapping("queryPositionById")
    public Map<String,Object> queryPositionById(){
        Map<String, Object> stringObjectHashMap = new HashMap<String, Object>();
        Integer positionId = Integer.decode(httpServletRequest.getParameter("positionId"));
        try {
            Position position = positionService.queryById(positionId);
            stringObjectHashMap.put("success",true);
            stringObjectHashMap.put("data",position);
        }catch (Exception e){
            e.printStackTrace();
            stringObjectHashMap.put("success",false);
            stringObjectHashMap.put("errMsg",e.getMessage());
        }
        return stringObjectHashMap;
    }

    @ApiOperation(value = "编辑职位信息")
    @PostMapping("/editPosition")
    @ResponseBody
    public Map<String,Object> editPosition(@RequestParam String positionStr){
        Map<String, Object> stringObjectHashMap = new HashMap<String, Object>();
        ObjectMapper objectMapper = new ObjectMapper();
        Position position = null;
        try{
            position = objectMapper.readValue(positionStr, Position.class);
            positionService.update(position);
            stringObjectHashMap.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            stringObjectHashMap.put("success",false);
            stringObjectHashMap.put("errMsg",e.getMessage());
        }
        return stringObjectHashMap;
    }

    @ApiOperation(value = "新增职位信息")
    @PostMapping("/insertPosition")
    @ResponseBody
    public Map<String,Object> insertPosition(@RequestParam String positionStr){
        Map<String, Object> stringObjectHashMap = new HashMap<String, Object>();
        ObjectMapper objectMapper = new ObjectMapper();
        Position position = null;
        try{
            position = objectMapper.readValue(positionStr, Position.class);
            positionService.insert(position);
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
            stringObjectHashMap = positionService.queryActiveList();
        }catch (Exception e){
            stringObjectHashMap = new HashMap<String, Object>();
            stringObjectHashMap.put("success",false);
            stringObjectHashMap.put("errMsg",e.getMessage());
        }
        return stringObjectHashMap;
    }
    
}
