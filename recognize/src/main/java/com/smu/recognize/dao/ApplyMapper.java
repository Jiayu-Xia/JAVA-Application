package com.smu.recognize.dao;

import com.smu.recognize.model.Apply;

import java.util.List;

public interface ApplyMapper {
    public List<Apply> findApplyByStatus(int status);
    public List<Apply> findApplyByUse_id(int user_id);
    public boolean insertApply(Apply apply);
    public List<Apply> findApplyByApprovedById(int ApprovedbyId, int a_status);
    public void updateStatus(int app_id,int a_status);
}
