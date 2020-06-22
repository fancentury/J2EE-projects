package com.demo.controller.front;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.demo.mapper.DistrictMapper;
import com.demo.mapper.HouseMapper;
import com.demo.mapper.TypeMapper;
import com.demo.pojo.District;
import com.demo.pojo.House;
import com.demo.pojo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private DistrictMapper districtMapper;
    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private HouseMapper houseMapper;

    @RequestMapping("/list")
    public String homeList(Model model){
        //1、查询城区
        List<District> districts = districtMapper.selectDisArea(1);
        //2、查询房型
        List<Type> types = typeMapper.selectAll();
        //3、存model
        model.addAttribute("districts",districts);
        model.addAttribute("types",types);
        System.out.println(districts);
        System.out.println(types);
        return "front/home_list";
    }

    //异步请求展示房源数据
    @RequestMapping("/showHomeList")
    @ResponseBody
    public PageInfo<House> showHomeList(@RequestParam(defaultValue="1") Integer pageNum,
                                        @RequestParam(defaultValue="8") Integer pageSize,
                                        String district_id, String type_id, String area, String price){
        //将所有的条件封装到map
        Map<String, String> query = new HashMap<String, String>();
        if(area!=null) {//截取面积字符（50-60）
            String[] areas = area.split("-");
            query.put("min_area", areas[0]);
            query.put("max_area", areas[1]);
        }
        if(price!=null) {//截取价钱字符（100-150）
            String[] prices = price.split("-");
            query.put("min_price", prices[0]);
            query.put("max_price", prices[1]);
        }
        query.put("district_id",district_id);
        query.put("type_id",type_id);

        PageHelper.startPage(pageNum, pageSize);
        List<House> homes = houseMapper.selectByQuery(query);
        return new PageInfo<>(homes);
    }


}
