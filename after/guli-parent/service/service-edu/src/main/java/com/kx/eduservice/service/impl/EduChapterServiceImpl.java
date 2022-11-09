package com.kx.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kx.eduservice.entity.EduChapter;
import com.kx.eduservice.entity.EduVideo;
import com.kx.eduservice.entity.form.ChapterVo;
import com.kx.eduservice.entity.form.VideoVo;
import com.kx.eduservice.mapper.EduChapterMapper;
import com.kx.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kx.eduservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.QuerydslRepositoryInvokerAdapter;
import org.springframework.stereotype.Service;

import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author kx
 * @since 2022-10-17
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;
    @Override
    public List<ChapterVo> getChapterList(String courseId) {



        List<ChapterVo> chapterVoList=new ArrayList<>();
        //获取章节信息
        QueryWrapper<EduChapter> queryWrapper=new QueryWrapper();
        queryWrapper.eq("course_id",courseId);
        List<EduChapter> eduChapters = baseMapper.selectList(queryWrapper);
        //获取课时信息
        QueryWrapper<EduVideo> queryWrapper1=new QueryWrapper<>();
        queryWrapper1.eq("course_id",courseId);
        List<EduVideo> eduVideos = eduVideoService.list(queryWrapper1);

        //填充章节vo数据
        for (int i = 0; i <eduChapters.size(); i++) {
            ChapterVo chapterVo=new ChapterVo();
            EduChapter eduChapter = eduChapters.get(i);
            BeanUtils.copyProperties(eduChapter,chapterVo);
            chapterVoList.add(chapterVo);
            //填充课时vo数据
            List<VideoVo> videoVoList=new ArrayList<>();

            for (int j = 0; j < eduVideos.size(); j++) {
                EduVideo eduVideo=eduVideos.get(j);
                if(eduChapter.getId().equals(eduVideo.getChapterId())){
                    VideoVo videoVo=new VideoVo();
                    BeanUtils.copyProperties(eduVideo,videoVo);
                    videoVoList.add(videoVo);
                }
                chapterVo.setChildren(videoVoList);
            }
        }
        return chapterVoList;
    }

    @Override
    public boolean deleteChapter(String id) {
        QueryWrapper<EduVideo> wrapper=new QueryWrapper<>();
        wrapper.eq("chapter_id",id);
        //根据id查询是否存在视频，如果有则提示用户尚有子节点
        int count = eduVideoService.count(wrapper);
        if(count>0){
            return false;
        }else{
            int i = baseMapper.deleteById(id);
            return true;
        }
    }

    @Override
    public void removeCourseInfoById(String courseId) {
        QueryWrapper<EduChapter> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        baseMapper.delete(queryWrapper);
    }
}
