package com.smu.recognize.dao;

import com.smu.recognize.model.User;

import java.util.List;

public interface UserMapper {
    //查询全部
    public List<User> selectAll();
    //用户名和密码查询用户
    public User findUserByNameAndPwd(String userName,String password);
    //插入一个
    public boolean insert(User user);
    //更新用户信息
    public boolean update(User user);
    //根据userName查询user_id
    public User findIdByUserName(String userName);

    public User findNameByUser_id(int user_id);

    public User findFeatureByUser_id(int user_id);
}
