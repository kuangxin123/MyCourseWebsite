package com.kx.eduservice.service;

import com.kx.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author kx
 * @since 2022-10-17
 */
public interface EduVideoService extends IService<EduVideo> {

    void removeCourseInfoById(String courseId);
}
