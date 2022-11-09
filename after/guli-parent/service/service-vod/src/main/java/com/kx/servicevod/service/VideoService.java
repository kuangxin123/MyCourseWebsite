package com.kx.servicevod.service;

import com.kx.servicevod.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author kx
 * @since 2022-10-21
 */
public interface VideoService extends IService<Video> {

    String upLoadVideo(MultipartFile file);

    void removeByVideoId(String videoId);

    void removeVideos(List<String> videoList);
}
