package com.kx.alioss.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.internal.OSSUtils;
import com.aliyun.oss.model.CannedAccessControlList;
import com.kx.alioss.service.OssService;
import com.kx.alioss.utils.OssNameUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @ClassName OssServiceImpl
 * @Description TODO
 * @Author kuang
 * @Date 2022/10/14 14:52
 */
@Service
public class OssServiceImpl implements OssService {
    @Override
    public String upFile(MultipartFile file) {

        String filename=file.getOriginalFilename();
        String s = UUID.randomUUID().toString();
        filename=s+filename;
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(OssNameUtils.ENDPOINT, OssNameUtils.KEYID, OssNameUtils.KEYSECRET);
        //构建日期路径：avatar/2019/02/26/文件名
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String format=dateFormat.format(date);
        //构建日期路径：avatar/2019/02/26/文件名
      //  String format = new DateTime().toString("yyyy/MM/dd");

        String path=format+"/"+filename;
        InputStream inputStream = null;
        try {
            if (!ossClient.doesBucketExist(OssNameUtils.BUCKETNAME)) {
                //创建bucket
                ossClient.createBucket(OssNameUtils.BUCKETNAME);
                //设置oss实例的访问权限：公共读
                ossClient.setBucketAcl(OssNameUtils.BUCKETNAME, CannedAccessControlList.PublicRead);
            }
            inputStream = file.getInputStream();
            //第二个参数：是上传到oss的文件路径和文件名称
            ossClient.putObject(OssNameUtils.BUCKETNAME, path, inputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 创建PutObject请求。

        //https://solitude-kx.oss-cn-beijing.aliyuncs.com/IMG_20220601_132209.jpg
        String url="https://"+OssNameUtils.BUCKETNAME+"."+OssNameUtils.ENDPOINT+"/"+path;
        ossClient.shutdown();
        return url;
    }
}
