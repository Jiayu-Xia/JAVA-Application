package com.smu.recognize.controller;

import com.smu.recognize.Util.MultipartFileToFile;
import com.smu.recognize.faceEngine.FaceEngineTest;
import com.smu.recognize.model.User;
import com.smu.recognize.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

//相当于@Controller+@ResponseBody
@RestController
@CrossOrigin
@Slf4j
public class UserController {

    List<User> userList=new ArrayList<>();
    @Autowired
    private UserService userService;
    User user=new User();
    FaceEngineTest engine=new FaceEngineTest();

    @RequestMapping("/hello")
    public String hello()
    {
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/img/headImg/";
        System.out.println(path);

        return "你好，夏家禹";
    }

    @CrossOrigin
    @RequestMapping("/login")
    public User login(String userName,String password){
        User user=userService.login(userName,password);
        if (user!=null){
            return user;
        }else
            return null;
    }
    @CrossOrigin
    @RequestMapping("/register")
    public User register(String userName, String password, byte[] faceFeatureData, HttpServletResponse response) throws IOException {
/*        int a1 =faceFeature.available();
          System.out.println(a1);
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100]; //buff用于存放循环读取的临时数据
        int rc = 0;
        while ((rc = faceFeature.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] faceFeatureData = swapStream.toByteArray(); //in_b为转换之后的结果
        swapStream.flush();

        for (byte a:faceFeatureData){
            System.out.println(String.valueOf(a));
        }*/
        user.setUserName(userName);
        user.setPassword(password);
        user.setFaceFeature(faceFeatureData);
        boolean flag=userService.register(user);
        if (flag!=true){
            return null;
        }
        return user;
    }

    @RequestMapping("/register1")
    public void register(@RequestParam("picture") MultipartFile file,String userName,String password){
        if (file!=null){
            log.info("文件上传成功");
        }

        try {
            File file1= MultipartFileToFile.multipartFileToFile(file);
            //获取人脸特征值
            user.setFaceFeature(engine.getFaceFeature(file1));

            String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/img/headImg/";
            File file2=new File(path);
            if (!file2.exists()){
                file2.mkdirs();
            }
            String originalFileName = file1.getName();//获取原始图片的扩展名

            String[] string=originalFileName.split("\\.");
            int suffixIndex=string.length-1;
            originalFileName=string[suffixIndex];

            String newFileName = userName+"."+originalFileName;
            String newFilePath=path+newFileName; //新文件的路径

            file.transferTo(new File(newFilePath));
            log.info(newFilePath);

            user.setPicture(newFilePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
        user.setUserName(userName);
        user.setPassword(password);

        userService.register(user);
    }

    @RequestMapping("/selectAll")
    public List<User> selectAll(){
        List<User> userList=new ArrayList<>();
        userList=userService.selectAll();
/*        for (User user2:userList){
            byte[] faceFeature=user2.getFaceFeature();
            log.info("查询用户");
//            Byte a=null;
            for (byte a:faceFeature){
                System.out.println(String.valueOf(a));
            }
        }*/
        return userList;
    }
    //更新用户信息
    @RequestMapping("/update")
    public User upate_user(User user){
        userService.update(user);
        return user;
    }
    //根据user_id查询name
    @RequestMapping("/findName")
    public String findUserName(int user_id){
        String name=userService.findNameByUser_id(user_id);
        return name;
    }
    //user_id查用户信息
    @RequestMapping("/findInfo")
    public User findUserInfo(int user_id){
        user=userService.findUserById(user_id);
        return user;
    }

    @RequestMapping("/findPhoto")
    public byte[] findUserPhoto(int user_id){
        user=userService.findUserById(user_id);
        FileInputStream inputStream = null;
        String path=user.getPicture();
        File file=new File(path);
        byte[] imageByte =new byte[(int) file.length()];

        try {
            inputStream=new FileInputStream(file);
            inputStream.read(imageByte);
            inputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        log.info("返回头像数据");
        return imageByte;
    }

}
