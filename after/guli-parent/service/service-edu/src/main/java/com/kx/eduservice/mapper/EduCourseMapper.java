package com.kx.eduservice.mapper;

import com.kx.eduservice.entity.CoursePublishVo;
import com.kx.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kx.eduservice.entity.form.CourseWebVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author kx
 * @since 2022-10-17
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
        public CoursePublishVo getPublishInfo(String id);

        public  CourseWebVo getCourseAllInfo(String id);
}
