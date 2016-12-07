package com.mozi.chintms.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 文件上传、下载
 * @author zhulm
 *
 */
public class FileOperateUtil {
    public static String FILEDIR = null;
    public static String FILE_PATH = "upload/";
    /**
     * 上传
     * @param request
     * @return 
     * @throws IOException
     */
    public static String upload(HttpServletRequest request) throws IOException{  
    	String filePath = null;
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = mRequest.getFileMap();       
        File file = new File(FILEDIR+FILE_PATH);
        if (!file.exists()) {
            file.mkdir();
        }
        Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, MultipartFile> entry = it.next();
            MultipartFile mFile = entry.getValue();
            if(mFile.getSize() != 0 && !"".equals(mFile.getName())){
            	String fileFormat = mFile.getOriginalFilename().substring(mFile.getOriginalFilename().lastIndexOf("."));
            	filePath = FILE_PATH+initFilePath(mFile.getOriginalFilename())+fileFormat;
                write(mFile.getInputStream(), new FileOutputStream(FILEDIR+filePath));
            }
        }
        return filePath;
    }
    /**
     * 根据文件名获取文件路径
     * @param name
     * @return
     */
    private static String initFilePath(String name) {
        String dir = getFileDir(name) + "";
        //如果文件夹不存在，则创建
        File file = new File(FILEDIR+FILE_PATH + dir);
        if (!file.exists()) {
            file.mkdir();
        }
        Long num = new Date().getTime();
        Double d = Math.random()*num;
        return (dir + "/" + num + d.longValue()).replaceAll(" ", "-");
    }
    /**
     * 根据名字，获取随机值
     * @param name
     * @return
     */
    private static int getFileDir(String name) {
        return name.hashCode() & 0xf;
    }
    
    /**
     * 文件下载
     * @param downloadfFileName
     * @param out
     */
    public static void download(String downloadfFileName, ServletOutputStream out) {
        try {
            FileInputStream in = new FileInputStream(new File(FILEDIR + "/" + downloadfFileName));
            write(in, out);
        } catch (FileNotFoundException e) {
            try {
                FileInputStream in = new FileInputStream(new File(FILEDIR + "/"
                        + new String(downloadfFileName.getBytes("iso-8859-1"),"utf-8")));
                write(in, out);
            } catch (IOException e1) {              
                e1.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }       
    }
    /**
     * 写入数据
     * @param in
     * @param out
     * @throws IOException
     */
    public static void write(InputStream in, OutputStream out) throws IOException{
        try{
            byte[] buffer = new byte[1024];
            int bytesRead = -1;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            out.flush();
        } finally {
            try {
                in.close();
            }
            catch (IOException ex) {
            }
            try {
                out.close();
            }
            catch (IOException ex) {
            }
        }
    }
    
    /**
     * 删除文件
     * @param filePath
     */
    public static void deleteFile(String filePath){
    	File file = new File(FileOperateUtil.FILEDIR+filePath);
    	//如果是文件，则进行删除
    	if(file.isFile()){
    		file.delete();
    	}
    }
}

