package com.itheima.mall.web.controller;

import cn.hutool.core.bean.BeanUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.mall.common.Result;
import com.itheima.mall.pojo.Member;
import com.itheima.mall.service.MemberService;
import com.itheima.mall.service.impl.MemberServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;

@WebServlet( "/registerServlet")
public class registerServlet extends HttpServlet {

    MemberService memberService = new MemberServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //如果前台数据很多的时候，可以使用BeanUtils.Populate()方法将表单的数据封装到实体对象中去
    //前提是要保存的的表单数据的名字要和实体对象属性名一致
        Map<String, String[]> parameterMap = request.getParameterMap();
        Member member = new Member();

        try {
            BeanUtils.populate(member,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //设置用户的注册时间
        member.setRegister_time(new Date());

        //调用业务层完成添加的任务
        boolean result = memberService.regist(member);
        //根据注册结果返回对应的信息
        Result json = new Result();
        if (result == true){
            json.setFlag(true);
            json.setMsg("登录成功");
        }
        else{
            json.setFlag(false);
            json.setMsg("登录失败");
        }
        ObjectMapper mapper = new ObjectMapper();
        String jsondate = mapper.writeValueAsString(result);
        response.getWriter().print(jsondate);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
