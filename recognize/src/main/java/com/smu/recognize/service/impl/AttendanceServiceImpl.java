package com.smu.recognize.service.impl;

import com.smu.recognize.dao.AttendanceMapper;
import com.smu.recognize.model.Attendance;
import com.smu.recognize.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service("AttendanceService")
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    private AttendanceMapper attendanceMapper;

    @Override
    public boolean insertAttendance(Attendance attendance) {
        Boolean boo= attendanceMapper.insertAttendance(attendance);
        return boo;
    }

    @Override
    public List<Attendance> selectAllAttendance() {
        return attendanceMapper.selectAll();
    }

    @Override
    public List<Attendance> findAttendanceByDate(String date) {
        return attendanceMapper.findAttendanceByDate(date);
    }

    @Override
    public List<Attendance> findAttendanceByStatus(String status) {
        return attendanceMapper.findAttendanceByStatus(status);
    }

    @Override
    public List<Attendance> findAttendanceByUser_id(int id) {
        return attendanceMapper.findAttendanceByUser_id(id);
    }

    @Override
    public List<Attendance> findAttendanceByFlag(int flag) {
        return attendanceMapper.findAttendanceByFlag(flag);
    }
}
