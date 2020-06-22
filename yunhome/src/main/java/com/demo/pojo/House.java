package com.demo.pojo;

import lombok.Data;

@Data
public class House {
    private Integer hid;
    private Users users;//关联user_id
    private District district;//关联district_id
    private Type houseType;//关联type_id
    private Double price;
    private Double areas;
    private String title;
    private String mark;
    private String equipment;
    private String address;
    private String imgs;

    //实现新增，原始字段
    private Integer user_id;
    private Integer district_id;
    private Integer type_id;
}
