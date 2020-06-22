package com.itheima.mall.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.mall.common.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loginservlet2")
public class LoginServlet2 extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //response.setContentType("application/json");        //表示响应到前端的数据是一个json数据  否则为"text/html"
        PrintWriter out = response.getWriter();

        //1、接收用户传过来的手机号和密码
        String mobile = request.getParameter("mobile");
        String pwd = request.getParameter("pwd");
        /*判断手机号和密码是否正确-传输字符串对应member_login2.html
        if("13888888888".equals(mobile)&&"123456".equals(pwd)){
            out.print("success");
        }else{
            out.print("error");
        }
       */
        /*if("13888888888".equals(mobile)&&"123456".equals(pwd)){
            //out.print("{'flag':true,'message':'登录成功'}");
            out.print("{\"flag\":true,\"message\":\"登录成功\"}");
        }else{
            out.print("{\"flag\":false,\"message\":\"登录失败\"}");
        }
        //response.setContentType("application/json");若不添加该语句会显示undfine，因为浏览器还认为是text文本
        */
        //2、判断手机号和密码是否正确
        if ("13888888888".equals(mobile) && "123456".equals(pwd)) {
            Result result = new Result();
            result.setFlag(true);
            result.setData(null);
            result.setMsg("登陆成功");
            //将result对象转为json
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(result);
            out.print(json);
        } else {
            Result result = new Result(false, null, "登陆失败");
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(result);
            out.print(json);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public static interface CategoryDao {
    }
}
