package com.example.demo.Mapper;

import com.example.demo.Model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modify) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModify})")
    public void insert(User user);
}