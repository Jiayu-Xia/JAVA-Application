package com.smu.recognize.controller;

import com.arcsoft.face.FaceFeature;
import com.arcsoft.face.FaceSimilar;
import com.smu.recognize.Util.Distance;
import com.smu.recognize.faceCompare.FeatureCompare;
import com.smu.recognize.model.Attendance;
import com.smu.recognize.model.User;
import com.smu.recognize.service.AttendanceService;
import com.smu.recognize.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GlobalCoordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//http://192.168.101.14:8085/insertAtt?user_id=1&date=2021.2.20&start_time=08:40;end_time=17:30&address=%E5%AE%89%E5%BE%BD%E6%BB%81%E5%B7%9E%E5%8D%97%E8%B0%AF%E5%8C%BA%E5%8C%97%E6%B9%96%E5%B0%8F%E5%8C%BA&longitude=70&latitude=80&flag=1&status=1
@RestController
@CrossOrigin
@Slf4j
public class AttendanceController {
    //上海海事大学信息工程学院的经纬度
    public static final double longitude=121.905411;
    public static final double latitude=30.876277;
    GlobalCoordinates source = new GlobalCoordinates(latitude, longitude);

    List<Attendance> list=new ArrayList<>();
    Attendance attendance=new Attendance();
    @Autowired
    private AttendanceService service;

    @Autowired
    private UserService userService;

    @RequestMapping("/insertAtt")
    public boolean insert(Attendance attendance){
        //考勤成功的标志
        boolean success=false;
        //内勤外勤的标志
        boolean flag;
        /*
        * 这里需要对考勤的情况进行细分
        * 地点
        * flag：内勤（0）、外勤（1）、地点距离太远
        * 时间
        * status：正常（0）、迟到（1）、早退（2）、缺勤（3）
        *签到签退
        *
        * 上班时间 9：30
        * 下班时间 21：30
        *
        * 上班地点、上海海事大学信息工程学院
        * */
        //考勤位置的经纬度
        GlobalCoordinates to = new GlobalCoordinates(Double.valueOf(attendance.getLatitude()), Double.valueOf(attendance.getLongitude()));
        //考勤位置与上海海事大学之间的距离
        double distance= Distance.getDistanceMeter(source,to,Ellipsoid.Sphere);
        double distance1= Distance.getDistanceMeter(source,to,Ellipsoid.WGS84);
        double distance2= Distance.getDistanceMeter(source,to,Ellipsoid.WGS72);

        log.info("distance"+distance+"\n"
                +"distance1"+distance1
                +"distance2"+distance2);

        //距离超过1km考勤失败
        if (distance>=1000){
            return success;
        }
        success=service.insertAttendance(attendance);

        if (success!=true){
            return success;
        }
        return success;
    }

    @RequestMapping("/selectAllAtt")
    public List<Attendance> selectAllAttendance(int user_id){
        list=service.findAttendanceByUser_id(user_id);
        return list;
    }
    //人脸比对接口
    @RequestMapping("/faceCompare")
    public Boolean faceCompare(byte[] faceFeatureData, int user_id){

        if(faceFeatureData==null){
            log.info("上传的人脸特征为空");
            return null;
        }

        FaceFeature feature=new FaceFeature();
        FaceFeature feature1=new FaceFeature();

        feature.setFeatureData(faceFeatureData);

        feature1.setFeatureData(userService.findFeatureByUser_id(user_id));

        FeatureCompare featureCompare=new FeatureCompare();
        FaceSimilar faceSimilar1=featureCompare.compare(feature,feature1);


        if (faceSimilar1.getScore()>=0.8){
            return true;
        }else {
            return false;
        }

    }
}
