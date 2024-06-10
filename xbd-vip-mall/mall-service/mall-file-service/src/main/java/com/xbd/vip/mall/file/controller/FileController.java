package com.xbd.vip.mall.file.controller;

import com.xbd.mall.util.RespResult;
import com.xbd.vip.mall.file.ceph.FileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/file")
public class FileController {

    @Autowired
    private FileHandler fileHandler;

    @Value("${cephurl}")
    private String cephurl;

    /***
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping(value = "/upload")
    //MultipartFile file变量名要和key相同,key-文件
    public RespResult upload(MultipartFile file) throws IOException {
        String fileName=file.getOriginalFilename();
//        System.out.println("fileName = " + fileName);
//        System.out.println("file.getBytes().length = " + file.getBytes().length);
        fileHandler.upload(fileName, file.getBytes());
        //返回文件访问地址
        return RespResult.ok(cephurl + fileName);
    }

    /***
     * 下载
     * @return
     */
    @GetMapping(value = "/download/{filename}")
    public void download(@PathVariable String filename, HttpServletResponse response) throws IOException {
        byte[] bytes = fileHandler.download(filename);
//        System.out.println("bytes.length = " + bytes.length);
        ServletOutputStream os = response.getOutputStream();
        os.write(bytes);
    }

//    @PostMapping(value = "/test")
//    public RespResult test(){
//        System.out.println("测试上传成功");
//        fileHandler.upload("111","11".getBytes());
//        return RespResult.ok(cephurl + "111");
//    }
}
