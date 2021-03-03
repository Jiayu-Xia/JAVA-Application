package com.smu.recognize;

import com.arcsoft.face.FaceFeature;
import com.arcsoft.face.FaceSimilar;
import com.smu.recognize.faceCompare.FeatureCompare;
import com.smu.recognize.model.User;
import com.smu.recognize.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RecognizeApplicationTests {
    @Autowired
    UserService userService;
    FeatureCompare featureCompare=new FeatureCompare();
    @Test
    void contextLoads() {

        User user=new User();
        User user1=new User();
        User user2=new User();

        user= userService.login("Har","Har");
        user1=userService.login("Har1","Har1");
        user2=userService.login("Xjj","Xjj");

        FaceFeature feature=new FaceFeature();
        FaceFeature feature1=new FaceFeature();
        FaceFeature feature2=new FaceFeature();

        feature.setFeatureData(user.getFaceFeature());
        feature1.setFeatureData(user1.getFaceFeature());
        feature2.setFeatureData(user2.getFaceFeature());

        FaceSimilar faceSimilar=featureCompare.compare(feature,feature1);

        FaceSimilar faceSimilar1=featureCompare.compare(feature,feature2);

        System.out.println("胡安然与胡安然比对结果"+faceSimilar.getScore());
        System.out.println("夏家禹与胡安然比对结果"+faceSimilar1.getScore());
    }

}
