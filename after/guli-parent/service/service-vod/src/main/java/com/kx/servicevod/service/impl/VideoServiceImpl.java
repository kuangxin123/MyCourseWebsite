package com.kx.servicevod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.kx.servicevod.entity.Video;
import com.kx.servicevod.mapper.VideoMapper;
import com.kx.servicevod.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kx.servicevod.utils.InitVodClient;
import com.kx.servicevod.utils.deleteVideo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author kx
 * @since 2022-10-21
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Override
    public String upLoadVideo(MultipartFile file) {
        String fileName=null;
        String title=null;
        InputStream inputStream= null;
        try {
            inputStream = file.getInputStream();
            //上传的文件名字
             fileName=file.getOriginalFilename();
            //上传之后显示的名字
             title=file.getOriginalFilename().substring(0,file.getOriginalFilename().lastIndexOf("."));
        } catch (IOException e) {
            e.printStackTrace();
        }


        UploadStreamRequest request = new UploadStreamRequest("LTAI5tA5SQpirarcggqNGo1F", "t0DVGgiXwR8uI2ZSxvbGLRtpKZahID", title, fileName, inputStream);


        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);

        String videoId=response.getVideoId();  //请求视频点播服务的请求ID

        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
        return videoId;
    }

    @Override
    public void removeByVideoId(String videoId) {
        DefaultAcsClient client= InitVodClient.initVodClient("LTAI5tA5SQpirarcggqNGo1F","t0DVGgiXwR8uI2ZSxvbGLRtpKZahID");
        DeleteVideoResponse response=new DeleteVideoResponse();
        try {
            response= deleteVideo.deleteVideoAPi(client,videoId);
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeVideos(List<String> videoList) {

        //将videoList转换为数组用逗号隔开
        String videos = StringUtils.join(videoList.toArray(), ",");

        DefaultAcsClient client= InitVodClient.initVodClient("LTAI5tA5SQpirarcggqNGo1F","t0DVGgiXwR8uI2ZSxvbGLRtpKZahID");
        DeleteVideoResponse response=new DeleteVideoResponse();
        DeleteVideoRequest request=new DeleteVideoRequest();
        request.setVideoIds(videos);
        try {
            response = client.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }

    }
}
