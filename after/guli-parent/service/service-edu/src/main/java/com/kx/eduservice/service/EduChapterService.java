package com.kx.eduservice.service;

import com.kx.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kx.eduservice.entity.form.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author kx
 * @since 2022-10-17
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterList(String courseId);

    boolean deleteChapter(String id);

    void removeCourseInfoById(String courseId);
}
