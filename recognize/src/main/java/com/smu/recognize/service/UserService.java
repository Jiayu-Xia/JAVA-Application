package com.smu.recognize.service;

import com.smu.recognize.model.User;

import java.util.List;

public interface UserService {
    //注册
    public boolean register(User user);
    //登录
    public User login(String userName,String password);
    //查询全部
    public List<User> selectAll();
    //用户信息完善
    public boolean update(User user);

    public int findIdByUserName(String userName);

    public String findNameByUser_id(int user_id);

    //查用户人脸特征
    public byte[] findFeatureByUser_id(int user_id);
    //查用户信息
    public User findUserById(int user_id);
}
