package com.text_20210511;



import com.homework.dao.TextDao;
import com.homework.service.TextService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


//确定运行环境,junit提供
@RunWith(SpringJUnit4ClassRunner.class)
//spring-text提供的确定配置文件的地址
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
//spring-text提供的确定web开发环境的资源路径，需要提供javax.servlet-api包
@WebAppConfiguration(value = "web")
public class Text {


    @Autowired
    private TextService textService;

    @Autowired
    private TextDao textDao;

    @Test
    public void testquerybyid(){
        com.homework.entity.Text text = textService.queryById(1);
//        com.homework.entity.Text text = textDao.queryById(1);
        System.out.println(text);
    }




}
