package com.smu.recognize.service;

import com.smu.recognize.model.Apply;

import java.util.List;

public interface ApplyService {
    //添加
    public Apply addApply(Apply apply);
    //状态查
    public List<Apply> findByStatus(int a_status);
    //id查
    public List<Apply> findByUser_id(int user_id);
    //审批人id和状态
    public List<Apply> findApplyByApprovedbyId(int user_id,int a_status);
    //审批
    public void updateApply(int app_id,int a_status);
}
