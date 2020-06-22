package com.demo.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "h_users")

public class Users {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer uid;
    private String name;
    private String psw;
    private String sex;
    private Date birth;
    @Column(name = "headImg")
    private String headImg;
    private String role;
}
