package com.itheima.mall.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.mall.common.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logoutServlet")
public class LogoutServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //销毁session
        HttpSession session = request.getSession();

        session.invalidate();

        Result result = new Result();
        result.setFlag(true);

        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(result);

        response.getWriter().print(s);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
