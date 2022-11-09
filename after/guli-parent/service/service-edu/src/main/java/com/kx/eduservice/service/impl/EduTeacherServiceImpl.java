package com.kx.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kx.eduservice.entity.EduTeacher;
import com.kx.eduservice.mapper.EduTeacherMapper;
import com.kx.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kx.eduservice.utils.TeacherQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author kx
 * @since 2022-10-09
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Autowired
    EduTeacherMapper eduTeacherMapper;

    @Override
    public IPage<EduTeacher> pageQuery(Page<EduTeacher> page, TeacherQuery teacherQuery) {
        QueryWrapper<EduTeacher> wrapper= new QueryWrapper<>();
        wrapper.orderByDesc("sort");
        if(teacherQuery==null){
            IPage<EduTeacher> eduTeacherIPage = eduTeacherMapper.selectPage(page, wrapper);
            return eduTeacherIPage;
        }

        if(!StringUtils.isEmpty(teacherQuery.getName())){
            wrapper.like("name",teacherQuery.getName());
        }
        if(!StringUtils.isEmpty(teacherQuery.getLevel())){
            wrapper.like("level",teacherQuery.getLevel());
        }
        if(!StringUtils.isEmpty(teacherQuery.getBegin())){
            wrapper.ge("gmt_create",teacherQuery.getBegin());
        }

        if(!StringUtils.isEmpty(teacherQuery.getEnd())){
            wrapper.le("gmt_modified",teacherQuery.getEnd());
        }

        IPage<EduTeacher> eduTeacherIPage = eduTeacherMapper.selectPage(page, wrapper);
        return eduTeacherIPage;
    }

    @Override
    public Map<String, Object> getTeacherList(Page<EduTeacher> eduTeacherPage) {
        QueryWrapper<EduTeacher> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        IPage<EduTeacher> eduTeacherIPage = baseMapper.selectPage(eduTeacherPage, queryWrapper);
        List<EduTeacher> records = eduTeacherIPage.getRecords();
        long pages = eduTeacherIPage.getPages();
        long total = eduTeacherIPage.getTotal();
        long size = eduTeacherIPage.getSize();
        long current = eduTeacherIPage.getCurrent();

        boolean hasNext = eduTeacherPage.hasNext();
        boolean hasPrevious = eduTeacherPage.hasPrevious();
        //把分页数据获取出来，放到map集合
        Map<String, Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);
        return map;
    }
}
