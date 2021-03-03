package com.smu.recognize.service;

import com.smu.recognize.model.Attendance;

import java.sql.Date;
import java.util.List;

public interface AttendanceService {
    //插入考勤記錄
    public boolean insertAttendance(Attendance attendance);
    //查詢全部考勤記錄
    public List<Attendance> selectAllAttendance();
    //根据日期查询考勤记录
    public List<Attendance> findAttendanceByDate(String date);
    //根据状态查询考勤记录
    public List<Attendance> findAttendanceByStatus(String status);
    //根据人员id查询考勤记录
    public List<Attendance> findAttendanceByUser_id(int id);
    //根据上下班标志查询考勤记录
    public List<Attendance> findAttendanceByFlag(int flag);
}
