package com.kx.servicevod.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @ClassName InitVodClient
 * @Description TODO
 * @Author kuang
 * @Date 2022/10/22 10:38
 */
public class InitVodClient {
    public static DefaultAcsClient initVodClient(String accessKeyId,String accessKeySecret){
        String regionId="cn-shanghai";
        DefaultProfile profile=DefaultProfile.getProfile(regionId,accessKeyId,accessKeySecret);
        DefaultAcsClient client=new DefaultAcsClient(profile);
        return client;
    }
}
