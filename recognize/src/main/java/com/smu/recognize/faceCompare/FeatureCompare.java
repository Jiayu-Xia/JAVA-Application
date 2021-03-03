package com.smu.recognize.faceCompare;

import com.arcsoft.face.*;
import com.arcsoft.face.enums.DetectMode;
import com.arcsoft.face.enums.ErrorInfo;

public class FeatureCompare {
    private FaceEngine faceEngine=new FaceEngine("E:\\arcsoft_lib");
    private EngineConfiguration engineConfiguration=new EngineConfiguration();

    public FaceSimilar compare(FaceFeature faceFeature1,FaceFeature faceFeature2){
        engineConfiguration.setDetectMode(DetectMode.ASF_DETECT_MODE_IMAGE);
        FunctionConfiguration functionConfiguration=new FunctionConfiguration();
        functionConfiguration.setSupportFaceRecognition(true);
        engineConfiguration.setFunctionConfiguration(functionConfiguration);
        //初始化引擎
        int errorCode=faceEngine.init(engineConfiguration);
        if (errorCode!= ErrorInfo.MOK.getValue()){
            System.out.println("初始化引擎失败");
        }
        //人脸比对
        FaceSimilar faceSimilar=new FaceSimilar();
        errorCode=faceEngine.compareFaceFeature(faceFeature1,faceFeature2,faceSimilar);

        System.out.println("相似度"+faceSimilar.getScore());
        if (faceEngine!=null){
                faceEngine.unInit();
        }
        return faceSimilar;
    }
}
