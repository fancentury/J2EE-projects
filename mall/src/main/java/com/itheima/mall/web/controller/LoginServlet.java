package com.itheima.mall.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.mall.common.Result;
import com.itheima.mall.pojo.Member;
import com.itheima.mall.service.MemberService;
import com.itheima.mall.service.impl.MemberServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loginservlet")
public class LoginServlet extends HttpServlet {

    MemberService memberService = new MemberServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        //1、接收用户传过来的手机号和密码
        String mobile = request.getParameter("mobile");
        String pwd = request.getParameter("pwd");
        //2.调用业务层判断手机号和密码
        Member member = memberService.login(mobile,pwd);
        //对member对象进行判断，如果为null则表示登陆失败，不null表示成功
        Result result = new Result();
        if (member == null){
            result.setFlag(false);
            result.setMsg("登录失败");
            //out.print(result);
        }
        else{
            //登录成功后需要将用户信息保存在session中
            HttpSession session = request.getSession();
            session.setAttribute("member",member);

            result.setFlag(true);
            result.setMsg("登录成功");

        }
        //将结果对象转换为json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(result);
        out.print(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
