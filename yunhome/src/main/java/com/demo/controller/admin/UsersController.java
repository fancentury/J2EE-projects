package com.demo.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.demo.mapper.UsersMapper;
import com.demo.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/users")
@Controller
public class UsersController {

    @Autowired
    private UsersMapper usersMapper;

    @RequestMapping("/show")
    public String show(Model model,
                       @RequestParam(defaultValue = "1") Integer pageNo,
                       @RequestParam(defaultValue = "5") Integer pageSize){
        //1.分页查询
        PageHelper.startPage(pageNo,pageSize);
        List<Users> users = usersMapper.selectAll();
        PageInfo<Users> pageInfo = new PageInfo<>(users);
        //2.存进model
        model.addAttribute("pageInfo",pageInfo);
        //3.跳转到users下的show页面
        return "admin/users/show";
    }

}
