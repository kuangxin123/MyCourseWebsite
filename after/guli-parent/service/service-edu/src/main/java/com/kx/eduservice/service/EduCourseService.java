package com.kx.eduservice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kx.eduservice.entity.CoursePublishVo;
import com.kx.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kx.eduservice.entity.form.CourseInfoForm;
import com.kx.eduservice.entity.form.CourseQuery;
import com.kx.eduservice.entity.form.CourseWebVo;
import com.kx.eduservice.entity.front.QueryCourse;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author kx
 * @since 2022-10-17
 */
public interface EduCourseService extends IService<EduCourse> {

String saveCourseInfo(CourseInfoForm courseInfoForm);

    CourseInfoForm getCourseFormInfo(String id);

    void updateCourseInfo(CourseInfoForm courseInfoForm);

    CoursePublishVo selectCoursePublishVoById(String id);

    IPage<EduCourse> pageQuery(Page<EduCourse> page, CourseQuery courseQuery);

    boolean removeCourseInfo(String courseId);

    List<EduCourse> getCourseByTeacherId(String id);

    Map<String, Object> getCourseInfoByQuery(Page<EduCourse> page, QueryCourse courseQuery);

    CourseWebVo getCourseAllInfo(String id);
}
