package com.atmoon.demo.util;

import com.atmoon.demo.entity.FdfsFile;
import com.atmoon.demo.properties.FileProperties;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author: zy
 */
@Component
public class FastDfsUtil {

    private static final Logger logger = LoggerFactory.getLogger(FastDfsUtil.class);

    @Autowired
    private FileProperties fileProperties;

    public static FastDfsUtil dfsUtil;

    private static TrackerClient trackerClient;

    private static TrackerServer trackerServer;

    private static StorageClient storageClient;

    private static StorageServer storageServer;

    @PostConstruct
    public void init() {
        dfsUtil = this;
        try {
            // 加载配置文件
            Properties props = new Properties();
            ClassPathResource resource = new ClassPathResource(dfsUtil.fileProperties.getConf());
            // 打成jar包后不能通过getFile获取配置文件,只能使用getInputStream
            InputStream inputStream = resource.getInputStream();
            if (inputStream != null) {
                props.load(inputStream);
            }
            // 初始化配置信息
            ClientGlobal.initByProperties(props);
            trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            storageServer = trackerClient.getStoreStorage(trackerServer);
            storageClient = new StorageClient(trackerServer, storageServer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传文件
     * @param file
     * @return
     */
    public static String[] upload(FdfsFile file) {
        long startTime = System.currentTimeMillis();
        String[] uploadResults = null;
        try {
            uploadResults = storageClient.upload_file(file.getContent(), file.getFileType(), null);
        } catch (IOException e) {
            logger.error("IO Exception when uploadind the file:" + file.getName(), e);
            // 连接超时出现java.io.IOException: recv package size -1 != 10
            try {
                uploadResults = storageClient.upload_file(file.getContent(), file.getFileType(), null);
            } catch (Exception ex) {
                logger.error("Non IO Exception when uploadind the file:" + file.getName(), e);
            }
        } catch (Exception e) {
            logger.error("Non IO Exception when uploadind the file:" + file.getName(), e);
        }
        logger.info("upload_file time used:" + (System.currentTimeMillis() - startTime) + " ms");
        if (uploadResults == null) {
            String failStr = "upload file fail, error code:" + storageClient.getErrorCode();
            logger.error(failStr);
            throw new RuntimeException(failStr);
        } else {
            String groupName = uploadResults[0];
            String remoteFileName = uploadResults[1];
            logger.info("upload file successfully!!!" + "group_name:" + groupName + ", remoteFileName:" + " " + remoteFileName);
        }
        return uploadResults;
    }

    /**
     * 获取文件服务器实际路径
     * @return
     */
    public static String getActualUrl() {
        return dfsUtil.fileProperties.getActualUrl();
    }

    /**
     * 保存文件
     * @param multipartFile
     * @return
     */
    public static FdfsFile saveFile(MultipartFile multipartFile) throws IOException {
        FdfsFile file = new FdfsFile();
        String fileName = multipartFile.getOriginalFilename();
        String name = fileName.substring(0,fileName.lastIndexOf("."));
        // 文件名
        file.setName(name);
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        // 后缀名
        file.setFileType(ext);
        // 获取文件流
        byte[] fileBuff = null;
        InputStream inputStream = multipartFile.getInputStream();
        if(inputStream!=null){
            int len1 = inputStream.available();
            fileBuff = new byte[len1];
            inputStream.read(fileBuff);
        }
        inputStream.close();
        file.setContent(fileBuff);
        String[] fileAbsolutePath = upload(file);
        String path=FastDfsUtil.getActualUrl() + fileAbsolutePath[0] + "/" + fileAbsolutePath[1];
        // 文件路径
        file.setPath(path);
        return file;
    }

}
