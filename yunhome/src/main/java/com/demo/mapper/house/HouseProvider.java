package com.demo.mapper.house;

import com.mysql.jdbc.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class HouseProvider {
    public String selectByQuery(Map<String, String> query){
        SQL sql=new SQL(){{
            SELECT(" *  ");
            FROM(" h_house h,h_users u,h_district d,h_type t ");
            WHERE("  h.user_id=u.uid  AND h.district_id=d.did AND h.type_id=t.typeid ");

            //动态拼接条件--区域
            if(!StringUtils.isNullOrEmpty(query.get("district_id"))){
                WHERE("d.parentId=#{district_id} ");
            }
            //动态拼接条件--房型
            if(!StringUtils.isNullOrEmpty(query.get("type_id"))){
                WHERE(" h.type_id=#{type_id} ");
            }
            //动态拼接条件--价格
            if(!StringUtils.isNullOrEmpty(query.get("min_price"))&&!StringUtils.isNullOrEmpty(query.get("max_price"))){
                WHERE(" h.price >= #{min_price} and h.price <= #{max_price}  ");
            }
            //动态拼接条件--面积
            if(!StringUtils.isNullOrEmpty(query.get("min_area"))&&!StringUtils.isNullOrEmpty(query.get("max_area"))){
                WHERE(" h.areas >= #{min_area} and h.areas <= #{max_area}  ");
            }

        }};
        return sql.toString();
    }
}
