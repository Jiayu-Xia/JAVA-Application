package com.smu.recognize.service.impl;

import com.smu.recognize.dao.ApplyMapper;
import com.smu.recognize.model.Apply;
import com.smu.recognize.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ApplyServiceImpl implements ApplyService {
    @Autowired
    ApplyMapper mapper;
    @Override
    public Apply addApply(Apply apply) {
        mapper.insertApply(apply);
        return apply;
    }

    @Override
    public List<Apply> findByStatus(int a_status) {
        List<Apply> applyList=new ArrayList<>();
        applyList=mapper.findApplyByStatus(a_status);
        return applyList;
    }

    @Override
    public List<Apply> findByUser_id(int user_id) {
        List<Apply> applyList=new ArrayList<>();
        applyList=mapper.findApplyByUse_id(user_id);
        return applyList;
    }

    @Override
    public List<Apply> findApplyByApprovedbyId(int user_id, int a_status) {
        List<Apply> applyList=new ArrayList<>();
        applyList=mapper.findApplyByApprovedById(user_id,a_status);
        return applyList;
    }

    @Override
    public void updateApply(int app_id, int a_status) {
       mapper.updateStatus(app_id,a_status);
    }
}
