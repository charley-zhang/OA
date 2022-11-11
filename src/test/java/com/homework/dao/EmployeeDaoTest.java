package com.homework.dao;

import com.homework.dto.EmployeeDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;


//确定运行环境,junit提供
@RunWith(SpringJUnit4ClassRunner.class)
//spring-text提供的确定配置文件的地址
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
//spring-text提供的确定web开发环境的资源路径，需要提供javax.servlet-api包
@WebAppConfiguration(value = "web")
public class EmployeeDaoTest {

    @Autowired
    private EmployeeDao employeeDao;

    @Test
    public void queryEmIfnoByUsernameAndPassword() {
        EmployeeDTO employeeDTO = employeeDao.queryEmIfnoByUsernameAndPassword("123456", "123456");
        System.out.println(employeeDTO);
    }
}