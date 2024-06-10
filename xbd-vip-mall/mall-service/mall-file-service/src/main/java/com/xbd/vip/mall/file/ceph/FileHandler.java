package com.xbd.vip.mall.file.ceph;

import org.javaswift.joss.model.Container;
import org.javaswift.joss.model.StoredObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileHandler {


    @Autowired
    private Container container;
    /****
     * 文件上传
     */
    public void upload(String filename,byte[] buffer){
        //获取容器对象
        StoredObject object = container.getObject(filename);
        //文件上传
        object.uploadObject(buffer);
    }

    /***
     * 文件下载
     */

    public byte[] download(String filename){
        //获取容器中远程存储的信息
        StoredObject object = container.getObject(filename);
        //执行文件下载
        byte[] bytes = object.downloadObject();
        return bytes;
    }
}
