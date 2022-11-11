package com.homework.service.impl;

import com.homework.dao.EmployeeDao;
import com.homework.dto.requestDTO.DepartmentListRequestDTO;
import com.homework.entity.Department;
import com.homework.entity.Employee;
import com.homework.entity.Position;
import com.homework.service.DepartmentService;
import com.homework.service.PositionService;
import io.swagger.annotations.ApiModelProperty;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration:是Spring-test提供的确定配置文件的地址
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
//@WebAppConfiguration:是Spring-test提供的确定web开发环境的资源路径
@WebAppConfiguration
public class DepartmentServiceImplTest {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private EmployeeDao employeeDao;

    @Test
    public void queryByPage() {
//        Map<String, Object> stringObjectMap = departmentService.queryByPage();
//        System.out.println("-----stringObjectMap-----"+stringObjectMap);
    }

    @Test
    public void insert(){
        Department department = new Department();
        department.setName("售后部");
        department.setAddress("china");
        department.setStatus(1);
        departmentService.insert(department);
        for (int i= 0; i < 30; i++){
            department.setName("售后部"+i);
            department.setAddress("china"+i);
            department.setStatus(1);
            departmentService.insert(department);
        }
    }

    @Test
    public void insert1(){
        Position department = new Position();
        for (int i= 0; i < 3; i++){
            department.setPositionName("后勤--"+i);
            department.setCreateTime(new Date());
            department.setStatus(1);
            positionService.insert(department);
        }
    }

    @Test
    public void insertemployee(){
        Employee employee = new Employee();
        for (int i= 111; i < 115; i++){
            employee.setName("测试name"+i);
            employee.setPassword("gs117s61-1ps510sps51gs51es11ss61");
            employee.setLoginName("123456");
            employee.setDepId(30);
            employee.setPositionId(136);
            employee.setStatus(1);
            employeeDao.insert(employee);
        }
    }





}