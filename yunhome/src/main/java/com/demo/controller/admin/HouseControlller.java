package com.demo.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.demo.mapper.DistrictMapper;
import com.demo.mapper.TypeMapper;
import com.demo.mapper.UsersMapper;
import com.demo.pojo.District;
import com.demo.pojo.House;
import com.demo.mapper.HouseMapper;
import com.demo.pojo.Type;
import com.demo.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/house")
public class HouseControlller {
    @Autowired
    private HouseMapper houseMapper;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private DistrictMapper districtMapper;
    @Autowired
    private TypeMapper typeMapper;

    @RequestMapping("/addPage")
    public String addHousePage(Model model){
        //查询出所有的用户信息
        List<Users> users = usersMapper.selectLandlord();
        //2.查询出所有的类型信息
        List<Type> types = typeMapper.selectAll();
        //3.查询出所有的区域信息:城区 (和 街道)
        List<District> districts = districtMapper.selectDisArea(1);
        //4.上述集合存入model
        model.addAttribute("users",users);
        model.addAttribute("types",types);
        model.addAttribute("districts",districts);
        System.out.println(districts);
        //5.跳转到新增页面
        return "admin/house/add_house";
    }

    @RequestMapping("/getStreetsById")
    @ResponseBody//城区和街道二级联动
    public  List<District> streets(Integer id){
        return districtMapper.selectDisArea(id);
    }

    @RequestMapping("/add")//实现房源信息的新增
    public String addHouse(House house){
        System.out.println(house);
        houseMapper.addHouse(house);
        return "redirect:/admin";
    }

    @RequestMapping("/delete/{hid}")
    public String deleteHouse(@PathVariable Integer hid){
        houseMapper.delete(hid);
        return "admin/admin";
    }

    @RequestMapping("/show")
    public String show(Model model,
                       @RequestParam(defaultValue = "1") Integer pageNo,
                       @RequestParam(defaultValue = "3") Integer pageSize){
        //1.分页
        PageHelper.startPage(pageNo,pageSize);
        //2.查询
        List<House> houses = houseMapper.getHouse();
        System.out.println(houses);
        //3.封装pageInfo
        PageInfo<House> houseInfo = (PageInfo<House>) new PageInfo<>(houses);
        //4.存model
        model.addAttribute("houseInfo",houseInfo);
        //5.跳转showHouse.html
        return "admin/house/show_house";
    }
}
