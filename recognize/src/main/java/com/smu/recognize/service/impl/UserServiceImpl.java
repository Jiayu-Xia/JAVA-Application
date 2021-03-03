package com.smu.recognize.service.impl;

import com.smu.recognize.dao.UserMapper;
import com.smu.recognize.model.User;
import com.smu.recognize.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public boolean register(User user) {
        boolean flag=userMapper.insert(user);
        if (flag!=true){
            System.out.println("插入失败");
            return false;
        }
        return true;
    }

    @Override
    public User login(String userName, String password) {
        User user =userMapper.findUserByNameAndPwd(userName,password);
        if (user!=null){
            return user;
        }
        return null;
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public boolean update(User user) {
        boolean flag=userMapper.update(user);
        return flag;
    }

    @Override
    public int findIdByUserName(String userName) {

        User user=userMapper.findIdByUserName(userName);
        return user.getUser_id();
    }

    @Override
    public String findNameByUser_id(int user_id) {
        User user =userMapper.findNameByUser_id(user_id);
        String name=user.getName();
        return name;
    }

    @Override
    public byte[] findFeatureByUser_id(int user_id) {
        User user=userMapper.findFeatureByUser_id(user_id);
        byte[] bytes=user.getFaceFeature();
        return  bytes;
    }

    @Override
    public User findUserById(int user_id) {
        User user= userMapper.findFeatureByUser_id(user_id);
        return user;
    }
}
