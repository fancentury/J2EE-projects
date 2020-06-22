package com.itheima.mall.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.mall.common.Result;
import com.itheima.mall.pojo.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/findUserNikNameServlet")
public class findUserNikNameServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();  //无则创建，有则获取
        Member member = (Member)session.getAttribute("member");   //如果已经登陆过member对象不为null,否则为null

        ObjectMapper mapper = new ObjectMapper();
       //返回的结果对象
        Result result = new Result();
        result.setFlag(true);
        result.setMsg("");
        result.setData(member);

        //转换成json
        String s = mapper.writeValueAsString(result);

        response.getWriter().print(s);

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
