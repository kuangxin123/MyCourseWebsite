package com.kx.servicevod.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;

/**
 * @ClassName deleteVideo
 * @Description TODO
 * @Author kuang
 * @Date 2022/10/22 10:31
 */
//删除云端视频
public  class deleteVideo {
    public static DeleteVideoResponse deleteVideoAPi(DefaultAcsClient client,String videoId) throws ClientException {
        DeleteVideoRequest request=new DeleteVideoRequest();
        request.setVideoIds(videoId);
        return client.getAcsResponse(request);
    }
}
