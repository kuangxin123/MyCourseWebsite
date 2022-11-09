package com.kx.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kx.eduservice.entity.CoursePublishVo;
import com.kx.eduservice.entity.EduCourse;
import com.kx.eduservice.entity.EduCourseDescription;
import com.kx.eduservice.entity.form.CourseInfoForm;
import com.kx.eduservice.entity.form.CourseQuery;
import com.kx.eduservice.entity.form.CourseWebVo;
import com.kx.eduservice.entity.front.QueryCourse;
import com.kx.eduservice.mapper.EduCourseMapper;
import com.kx.eduservice.service.EduChapterService;
import com.kx.eduservice.service.EduCourseDescriptionService;
import com.kx.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kx.eduservice.service.EduVideoService;
import org.bouncycastle.crypto.prng.drbg.DualECPoints;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.swing.text.EditorKit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author kx
 * @since 2022-10-17
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Autowired
    private EduCourseDescriptionService descriptionService;
    @Autowired
    private  EduVideoService eduVideoService;
    @Autowired
    private EduChapterService eduChapterService;
    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Override
    public String saveCourseInfo(CourseInfoForm courseInfoForm) {
        //保存课程信息
        EduCourse eduCourse=new EduCourse();
        BeanUtils.copyProperties(courseInfoForm,eduCourse);
        boolean save = this.save(eduCourse);

        EduCourseDescription description=new EduCourseDescription();
        description.setId(eduCourse.getId());
        description.setDescription(courseInfoForm.getDescription());
        boolean save1 = descriptionService.save(description);
        return eduCourse.getId();
    }

    @Override
    public CourseInfoForm getCourseFormInfo(String id) {
        //根据id查询课程
        EduCourse eduCourse = baseMapper.selectById(id);
        if(eduCourse==null){
            return null;
        }
        CourseInfoForm courseInfoForm=new CourseInfoForm();
        BeanUtils.copyProperties(eduCourse,courseInfoForm);
        EduCourseDescription description = descriptionService.getById(id);
        courseInfoForm.setDescription(description.getDescription());
        return courseInfoForm;
    }

    @Override
    public void updateCourseInfo(CourseInfoForm courseInfoForm) {
        EduCourse eduCourse=new EduCourse();
        BeanUtils.copyProperties(courseInfoForm,eduCourse);
        int i = baseMapper.updateById(eduCourse);
        EduCourseDescription description=new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoForm,description);
        description.setId(eduCourse.getId());
        descriptionService.updateById(description);
    }

    @Override
    public CoursePublishVo selectCoursePublishVoById(String id) {
        CoursePublishVo coursePublishVo=baseMapper.getPublishInfo(id);
        return coursePublishVo;
    }

    @Override
    public IPage<EduCourse> pageQuery(Page<EduCourse> page, CourseQuery courseQuery) {
        QueryWrapper<EduCourse> queryWrapper=new QueryWrapper<>();
        if(courseQuery==null){
            IPage<EduCourse> eduCourseIPage = baseMapper.selectPage(page, queryWrapper);
            return eduCourseIPage;
        }
        queryWrapper.orderByDesc("gmt_create");
        if(!StringUtils.isEmpty(courseQuery.getTitle())){
            queryWrapper.like("title",courseQuery.getTitle());
        }
        if(!StringUtils.isEmpty(courseQuery.getTeacherId())){
            queryWrapper.eq("teacher_id",courseQuery.getTeacherId());
        }
        if(!StringUtils.isEmpty(courseQuery.getSubjectId())){
            queryWrapper.eq("subject_id",courseQuery.getSubjectId());
        }
        if(!StringUtils.isEmpty(courseQuery.getSubjectParentId())){
            queryWrapper.eq("subject_parent_id",courseQuery.getSubjectParentId());
        }
        IPage<EduCourse> eduCourseIPage = baseMapper.selectPage(page, queryWrapper);
        return eduCourseIPage;
    }

    @Override
    public boolean removeCourseInfo(String courseId) {
        //根据id删除视频信息
        eduVideoService.removeCourseInfoById(courseId);
        //根据id删除章节信息
        eduChapterService.removeCourseInfoById(courseId);
        //删除描述信息
        eduCourseDescriptionService.removeById(courseId);
        boolean b = this.removeById(courseId);
        return b;
    }

    @Override
    public List<EduCourse> getCourseByTeacherId(String id) {
        QueryWrapper<EduCourse>  queryWrapper=new QueryWrapper<>();
        //根据创建时间排序
        queryWrapper.eq("teacher_id",id);
        queryWrapper.orderByDesc("gmt_create");
        List<EduCourse> eduCourses = baseMapper.selectList(queryWrapper);
        return eduCourses;
    }

    /**
     * @Description 根据条件对象查询课程信息
     * @param page
     * @param courseQuery
     * @return
     */
    @Override
    public Map<String, Object> getCourseInfoByQuery(Page<EduCourse> page, QueryCourse courseQuery) {
        QueryWrapper<EduCourse> queryWrapper=new QueryWrapper<>();
        String title = courseQuery.getTitle();
        String subjectId = courseQuery.getSubjectId();
        String priceSort = courseQuery.getPriceSort();
        String subjectParentId = courseQuery.getSubjectParentId();
        String teacherId = courseQuery.getTeacherId();
        String buyCountSort = courseQuery.getBuyCountSort();

        if(!StringUtils.isEmpty(title)){
            queryWrapper.eq("title",title);
        }
        if(!StringUtils.isEmpty(subjectId)){
            queryWrapper.eq("subject_id",subjectId);
        }
        if(!StringUtils.isEmpty(subjectParentId)){
            queryWrapper.eq("subject_parent_id",subjectParentId);
        }
        if(!StringUtils.isEmpty(teacherId)){
            queryWrapper.eq("teacher_id",teacherId);
        }
        if(!StringUtils.isEmpty(priceSort))
        {
            queryWrapper.orderByDesc("price");
        }
        if(!StringUtils.isEmpty(buyCountSort)){
            queryWrapper.orderByDesc("buy_count");
        }
        if(!StringUtils.isEmpty(courseQuery.getGmtCreateSort())){
            queryWrapper.orderByDesc("gmt_create");
        }
        IPage<EduCourse> eduCourseIPage = baseMapper.selectPage(page, queryWrapper);

        List<EduCourse> records = page.getRecords();
        long current = page.getCurrent();
        long pages = page.getPages();
        long size = page.getSize();
        long total = page.getTotal();
        boolean hasNext = page.hasNext();
        boolean hasPrevious = page.hasPrevious();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return map;
    }

    @Override
    public CourseWebVo getCourseAllInfo(String id) {
        CourseWebVo courseWebVo=baseMapper.getCourseAllInfo(id);
        return courseWebVo;
    }
}
