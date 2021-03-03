package com.smu.recognize.controller;

import com.smu.recognize.model.Apply;
import com.smu.recognize.service.ApplyService;
import com.smu.recognize.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@Slf4j
public class ApplyController {
    List<Apply> list=new ArrayList<>();

    @Autowired
    private ApplyService service;
    @Autowired
    private UserService userService;

    /**
     * 插入申请记录
     * @param apply
     * @return
     */
    @CrossOrigin
    @RequestMapping("/insertApply")
    public Apply insert(Apply apply){
        int id=userService.findIdByUserName(apply.getApprovedby());
        apply.setApprovedbyId(id);

        service.addApply(apply);
        log.info(apply.toString());
        return apply;
    }

    /**
     * 根据申请状态查询申请记录
     * @param a_status
     * @return
     */
    @RequestMapping("/findByStatus")
    public List<Apply> findByStatus(int a_status){
        list=service.findByStatus(a_status);
        log.info(list.toString());
        return list;
    }

    /**
     * 根据用户id查询申请记录
     * @param user_id
     * @return
     */
    @RequestMapping("/findByUser_id")
    public List<Apply> findByUser_id(int user_id){
        list=service.findByUser_id(user_id);
        log.info(list.toString());
        return list;
    }

    /**
     * 根据审批人id和审批状态查询申请记录
     * @param approvedById
     * @param a_status
     * @return
     */
    @RequestMapping("/findApplyByApprovedbyId")
    public List<Apply> findApplyByApprovedbyId(int approvedById,int a_status){
        //int user_id=userService.findIdByUserName(userName);
        list=service.findApplyByApprovedbyId(approvedById,a_status);
        return list;
    }
    //审批接口
    @RequestMapping("/approve")
    public List<Apply> approve(int app_id, int a_status, int user_id){
        service.updateApply(app_id,a_status);
        log.info("审批成功");
        return service.findApplyByApprovedbyId(user_id,0);
    }
}
