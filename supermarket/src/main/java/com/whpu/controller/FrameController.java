package com.whpu.controller;

import com.whpu.pojo.User;
import com.whpu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class FrameController {

    @Autowired
    private UserService userService;

    /**
     * 框架跳转请求
     * @return
     */

    @GetMapping("/top")
    public String top() {
        return "top";
    }

    @GetMapping("/left")
    public String left() {
        return "left";
    }

    @GetMapping("/main")
    public String mian() {
        return "main";
    }

    @GetMapping("/index")
    public String userview() {
        return "frame";
    }

    @RequestMapping("/login2")
    public String tologin() {
        System.out.println("ok");
        return "login";
    }

    /**
     * 跳转到对应的模版页面
     * @return
     */
    @RequestMapping("/toProviderList")
    public String toProviderList() {
        return "redirect:/provider/list";
    }
    @RequestMapping("/toBillList")
    public String toBillList(){
        return "redirect:/bill/list";
    }
    @RequestMapping("/toUserlist")
    public String toUserlist() { return "redirect:/user/list"; }
    @RequestMapping("/toUpdatePwd")
    public String toUpdatePwd(HttpSession session,Model model) {
        Long userid = (Long) session.getAttribute("user");
        Integer id = userid.intValue();
        User user = userService.findById(id);
        model.addAttribute("user",user);
        return "updatePwd";
    }

    /**
     * 登录
     * @param request
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpSession session, Model model) {
        String userName = request.getParameter("userName");
        String userPassword = request.getParameter("userPassword");

        User user = userService.findByMobileAndPwd(userName, userPassword);
        if (user == null) {
            //登录失败
            model.addAttribute("error", "登录失败");
            return "/login";
        } else {
            session.setAttribute("user", user.getId());
            session.setAttribute("userName", user.getUserName());
            session.setAttribute("userCode",user.getUserCode());
            return "frame";
        }
    }
}
