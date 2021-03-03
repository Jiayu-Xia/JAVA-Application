package com.smu.recognize.dao;

import com.smu.recognize.model.Attendance;

import java.sql.Date;
import java.util.List;


public interface AttendanceMapper {
    //查询全部
    public List<Attendance> selectAll();
    //根据日期查询全部记录
    public List<Attendance> findAttendanceByDate(String date);
    //根据状态查询记录
    public List<Attendance> findAttendanceByStatus(String status);
    //根据人员id查询考勤记录
    public List<Attendance> findAttendanceByUser_id(int id);
    //根据上下班标志查询考勤记录
    public List<Attendance> findAttendanceByFlag(int flag);
    //插入考勤記錄
    public boolean insertAttendance(Attendance attendance);

}
