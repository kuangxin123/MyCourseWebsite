package com.kx.servicevod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.kx.commonutils.Result;
import com.kx.servicevod.service.VideoService;
import com.kx.servicevod.utils.InitVodClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName VodController
 * @Description TODO
 * @Author kuang
 * @Date 2022/10/21 21:14
 */
@RestController
@Api(description = "视频点播控制器")
//@CrossOrigin
@RequestMapping("/servicevod/video")
public class VodController {
    @Resource
    private VideoService videoService;
    @PostMapping("/uploadFile")
    public Result upLoadVideo(MultipartFile file){
            String videoId=videoService.upLoadVideo(file);
            return Result.ok().data("videoId",videoId);
    }

    @DeleteMapping("/deleteVideo/{videoId}")
    public Result deleteVideo(@PathVariable("videoId") String videoId){
        videoService.removeByVideoId(videoId);
        return Result.ok();
    }

    //删除多个视频
    @DeleteMapping("deleteVideos")
    public Result deleteVideos(@RequestParam("videoList") List<String> videoList){
        videoService.removeVideos(videoList);
        return Result.ok();
    }

    @ApiOperation(value = "根据视频id获取视频凭证")
    @GetMapping("/getVideo/{id}")
    public Result getVideo(@PathVariable("id")String id){
        try {
            DefaultAcsClient client = InitVodClient.initVodClient("LTAI5tA5SQpirarcggqNGo1F", "t0DVGgiXwR8uI2ZSxvbGLRtpKZahID");
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            request.setVideoId(id);
            //响应
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            //得到播放凭证
            String playAuth = response.getPlayAuth();
            return Result.ok().message("获取凭证成功").data("playAuth",playAuth);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.ok().message("获取失败");
    }
}
