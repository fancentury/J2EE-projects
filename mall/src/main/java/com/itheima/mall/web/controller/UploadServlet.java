package com.itheima.mall.web.controller;

import com.itheima.mall.common.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@WebServlet("/upload/*")
@MultipartConfig  //该注解代表此Servlet能够接收文件上传
public class UploadServlet extends BaseServlet {

    public void uploadThumbnail(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
        //1、从请求获取上传上来的文件
        Part part = request.getPart("pic");
        //2、从part请求头中获取文件的扩展名
        String header = part.getHeader("Content-Disposition");
        String suffix= header.substring(header.lastIndexOf("."),header.length()-1);   //.gif
        //3、在服务器中产生一个唯一文件名
        String fileName = UUID.randomUUID()+suffix;
        //4、获取img文件件的绝对路径
        String realPath = request.getServletContext().getRealPath("/img");
        System.out.println(fileName);
        System.out.println(realPath);

        //该输出流用将文件写到指定的位置
        OutputStream os = new FileOutputStream(realPath+"/"+fileName);
        //获取到上传上来的文件的输入流
        InputStream inputStream = part.getInputStream();
        //完成拷贝
        byte[] buffer = new byte[1024];

        int length = inputStream.read(buffer);
        while(length!=-1){
            os.write(buffer,0,length);
            length = inputStream.read(buffer);
        }

        os.close();
        inputStream.close();

        //将图片名返回到前台
        Result result = new Result(true,fileName,"成功");
        wirteJson(response,result);

    }

    public void ckeditorupload(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
        //1、从请求获取上传上来的文件
        Part part = request.getPart("upload");
        //2、从part请求头中获取文件的扩展名
        String header = part.getHeader("Content-Disposition");
        String suffix= header.substring(header.lastIndexOf("."),header.length()-1);   //.gif
        //3、在服务器中产生一个唯一文件名
        String fileName = UUID.randomUUID()+suffix;
        //4、获取img文件件的绝对路径
        String realPath = request.getServletContext().getRealPath("/img");
        System.out.println(fileName);
        System.out.println(realPath);

        //该输出流用将文件写到指定的位置
        OutputStream os = new FileOutputStream(realPath+"/"+fileName);
        //获取到上传上来的文件的输入流
        InputStream inputStream = part.getInputStream();
        //完成拷贝
        byte[] buffer = new byte[1024];

        int length = inputStream.read(buffer);
        while(length!=-1){
            os.write(buffer,0,length);
            length = inputStream.read(buffer);
        }

        os.close();
        inputStream.close();


        //ckeditor返回时的数据格式({"uploaded":1,"url": 要回显图片路径})
        Map map = new HashMap();
        map.put("uploaded",1);
        map.put("url","/img/"+fileName);

        wirteJson(response,map);

    }

}
