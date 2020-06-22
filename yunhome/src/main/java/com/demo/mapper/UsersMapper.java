package com.demo.mapper;

import com.demo.pojo.Users;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UsersMapper  {
    @Select("select * from h_users")
    List<Users> selectAll();

    @Select("select * from h_users where role='房东'")
    List<Users> selectLandlord();

}
