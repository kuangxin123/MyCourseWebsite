package com.kx.eduservice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kx.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kx.eduservice.utils.TeacherQuery;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author kx
 * @since 2022-10-09
 */
public interface EduTeacherService extends IService<EduTeacher> {
        IPage<EduTeacher> pageQuery(Page<EduTeacher> page, TeacherQuery teacherQuery);

    Map<String, Object> getTeacherList(Page<EduTeacher> eduTeacherPage);
}
