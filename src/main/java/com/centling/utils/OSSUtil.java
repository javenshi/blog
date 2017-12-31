package com.centling.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

@Component
public class OSSUtil {
    @Value("${OSS.endpoint}")
    private String endpoint;

    @Value("${OSS.access_key_id}")
    private String access_key_id;

    @Value("${OSS.access_key_secret}")
    private String access_key_secret;


    @Value("${OSS.bucket_name}")
    public  String bucket_name;

    String REGION_CN_HANGZHOU = "cn-hangzhou";

    String STS_API_VERSION = "2015-04-01";



    public OSSClient getUploadOSSClient() {
        return new OSSClient(endpoint, access_key_id, access_key_secret);
    }

   /* public  OSSClient getLoadClient() throws Exception {
        String roleSessionName = "alice-001";
        String policy = "{\n" +
                "    \"Version\": \"1\", \n" +
                "    \"Statement\": [\n" +
                "        {\n" +
                "            \"Action\": [\n" +
                "                \"oss:GetBucket\", \n" +
                "                \"oss:GetObject\" ,\n " +
              *//*  "                  \"oss:PutObject\"\n" +*//*
                "            ], \n" +
                "            \"Resource\": [\n" +
                "                \"acs:oss:*:*:*\"\n" +
                "            ], \n" +
                "            \"Effect\": \"Allow\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        // 此处必须为 HTTPS
        ProtocolType protocolType = ProtocolType.HTTPS;
        final AssumeRoleResponse response = assumeRole(token_access_key_id, token_access_key_secret,
                roleArn, roleSessionName, policy, protocolType);
        System.out.println("UTC时间: " + response.getCredentials().getExpiration());
        return new OSSClient(endpoint, response.getCredentials().getAccessKeyId(), response.getCredentials().getAccessKeySecret(), response.getCredentials().getSecurityToken());
    }*/


    /**
     * 上传文件
     */
    public String uploadToOSS(OSSClient ossClient,String Folder, MultipartFile file) {
        String fileFullName = file.getOriginalFilename();
        String imageSuffix=fileFullName.substring(fileFullName.lastIndexOf(".")+1);
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        String Name=uuid+"."+imageSuffix;

        try {
            InputStream is = file.getInputStream();
            Long fileSize = file.getSize();
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(is.available());
            metadata.setCacheControl("no-cache");
            metadata.setHeader("Pragma", "no-cache");
            metadata.setContentEncoding("utf-8");
            metadata.setContentDisposition("filename/filesize=" + Name + "/" + fileSize + "Byte.");
            createFolder(ossClient,bucket_name,Folder);
            PutObjectResult putResult = ossClient.putObject(bucket_name, Folder+Name, is, metadata);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Date expiration = new Date(new Date().getTime() + new Date().getTime());
        return   ossClient.generatePresignedUrl(bucket_name, Folder+Name, expiration).toString();
    }

 /*   *//**
     * 下载文件
     *//*
    public  Object loadFromOSS(OSSClient ossClient, String url) {
        String bucketName = url.substring(0, url.indexOf(","));
        String key = url.substring(url.indexOf(",") + 1);
        Object object = ossClient.getObject(new GetObjectRequest(bucketName, key), new File("D:\\2.jpg"));
        return object;
    }*/

    /**
     * 获得一个有效的url
     */
    public String getUrl(OSSClient ossClient, String key) {
        String bucketName = key.substring(0, key.indexOf(","));
        String openKey = key.substring(key.indexOf(",") + 1);
        Date expiration = new Date(new Date().getTime() + new Date().getTime());
        return ossClient.generatePresignedUrl(bucketName, openKey, expiration).toString();
    }

    /**
     * 创建存储空间
     */
    public String createBucket(OSSClient ossClient, String bucketName) {
        final String bucketNames = bucketName;
        if (!ossClient.doesBucketExist(bucketName)) {
            //创建存储空间
            Bucket bucket = ossClient.createBucket(bucketName);
            return bucket.getName();
        }
        return bucketNames;
    }

    /**
     * 删除存储空间
     */
    public void deleteBucket(OSSClient ossClient, String bucketName) {
        ossClient.deleteBucket(bucketName);
    }

    /**
     * 创建文件夹
     */
    public String createFolder(OSSClient ossClient, String bucketName, String folder) {
        final String keySuffixWithSlash = folder;
        if (!ossClient.doesObjectExist(bucketName, keySuffixWithSlash)) {
            ossClient.putObject(bucketName, keySuffixWithSlash, new ByteArrayInputStream(new byte[0]));
            OSSObject object = ossClient.getObject(bucketName, keySuffixWithSlash);
            String fileDir = object.getKey();
            return fileDir;
        }
        return keySuffixWithSlash;
    }

    /**
     * 删除文件或文件夹
     */
    public void deleteFile(OSSClient ossClient, String bucketName, String folder) {
        ossClient.deleteObject(bucketName, folder);
    }

    @Override
    public String toString() {
        return "OSSConfiguration{" +
                "endpoint='" + endpoint + '\'' +
                ", access_key_id='" + access_key_id + '\'' +
                ", access_key_secret='" + access_key_secret + '\'' +
                ", bucket_name='" + bucket_name + '\'' +
                '}';
    }

  /*  private AssumeRoleResponse assumeRole(String accessKeyId, String accessKeySecret,
                                          String roleArn, String roleSessionName, String policy,
                                          ProtocolType protocolType) throws Exception {

        // 创建一个 Aliyun Acs Client, 用于发起 OpenAPI 请求
        IClientProfile profile = DefaultProfile.getProfile(REGION_CN_HANGZHOU, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        // 创建一个 AssumeRoleRequest 并设置请求参数
        final AssumeRoleRequest request = new AssumeRoleRequest();
        request.setVersion(STS_API_VERSION);
        request.setMethod(MethodType.POST);
        request.setProtocol(protocolType);
        request.setRoleArn(roleArn);
        request.setRoleSessionName(roleSessionName);
        request.setPolicy(policy);
        // 发起请求，并得到response
        final AssumeRoleResponse response = client.getAcsResponse(request);
        return response;

    }*/
}
