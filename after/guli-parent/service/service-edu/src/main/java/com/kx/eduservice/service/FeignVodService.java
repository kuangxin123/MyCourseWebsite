package com.kx.eduservice.service;

import com.kx.commonutils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "service-vod")
@Component
public interface FeignVodService {
    @DeleteMapping("/servicevod/video/deleteVideo/{videoId}")
    public Result deleteVideo(@PathVariable("videoId") String videoId);

    //删除多个视频
    @DeleteMapping("/servicevod/video/deleteVideos")
    public Result deleteVideos(@RequestParam("videoList") List<String> videoList);
}
