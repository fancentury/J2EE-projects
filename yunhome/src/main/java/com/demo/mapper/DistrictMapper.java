package com.demo.mapper;

import com.demo.pojo.District;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DistrictMapper {
    @Select("select * from h_district where parentId=#{did}")
    @Results(@Result(column = "dis_name",property = "disName"))
    List<District> selectDisArea(Integer did);
}
