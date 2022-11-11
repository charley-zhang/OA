package com.homework.interceptor;


import com.homework.dto.EmployeeDTO;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


/**
 * 验证是否登录的拦截器
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);
        Object employeeDTO = request.getSession().getAttribute("employeeDTO");
        if (employeeDTO!=null){
            //如果从session中能获取到对印的信息
            EmployeeDTO employeeDTO1 = (EmployeeDTO) employeeDTO;
            //控制判断
            if (employeeDTO1.getEmId()!=null&&employeeDTO1.getStatus()==1){
                if (requestURI.toLowerCase().contains("login")){
                    response.sendRedirect("/main");
                    return false;
                }
                return true;
            }
        }
        if (requestURI.toLowerCase().contains("login")){
            return true;
        }
        //让其跳转到登录页面
//        PrintWriter writer = response.getWriter();
//        writer.print("<html>");
//        writer.print("<script>");
//        writer.print("window.open('"+request.getContextPath()+"/login','_self)'");
//        writer.print("</script>");
//        writer.print("</html>");
        response.sendRedirect("/login");
        return false;
    }
}
