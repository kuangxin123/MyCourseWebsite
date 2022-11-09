package com.kx.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kx.eduservice.entity.EduVideo;
import com.kx.eduservice.mapper.EduVideoMapper;
import com.kx.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kx.eduservice.service.FeignVodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author kx
 * @since 2022-10-17
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private FeignVodService feignVodService;
    @Override
    public void removeCourseInfoById(String courseId) {

        //删除多个视频从云端中
        QueryWrapper<EduVideo> queryWrapper1=new QueryWrapper<>();
        queryWrapper1.eq("course_id",courseId);
        queryWrapper1.select("video_source_id");
        List<EduVideo> eduVideos = baseMapper.selectList(queryWrapper1);
        List<String> videoList=new ArrayList<>();
        for (int i = 0; i < eduVideos.size(); i++) {
            EduVideo eduVideo = eduVideos.get(i);
            String videoSourceId = eduVideo.getVideoSourceId();
            if(!StringUtils.isEmpty(videoSourceId))
            videoList.add(videoSourceId);
        }
        if(videoList.size()>0)
        feignVodService.deleteVideos(videoList);

        QueryWrapper<EduVideo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        baseMapper.delete(queryWrapper);
    }
}
