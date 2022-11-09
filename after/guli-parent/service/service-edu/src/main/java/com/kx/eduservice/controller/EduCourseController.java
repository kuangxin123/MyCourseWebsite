package com.kx.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kx.commonutils.JWTUtils;
import com.kx.commonutils.Result;
import com.kx.eduservice.entity.CoursePublishVo;
import com.kx.eduservice.entity.EduCourse;
import com.kx.eduservice.entity.EduVideo;
import com.kx.eduservice.entity.form.ChapterVo;
import com.kx.eduservice.entity.form.CourseInfoForm;
import com.kx.eduservice.entity.form.CourseQuery;
import com.kx.eduservice.entity.form.CourseWebVo;
import com.kx.eduservice.service.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.catalina.startup.ContextRuleSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author kx
 * @since 2022-10-17
 */
@RestController
@RequestMapping("/eduservice/eduCourse")
//@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    FeignVodService feignVodService;
    @Autowired
    private EduVideoService eduVideoService;
    @Autowired
    private EduChapterService eduChapterService;
    @Autowired
    private FeignOrderService feignOrderService;

    @ApiOperation(value = "添加课程基本信息")
    @PostMapping("/insertInfo")
    public Result insertInfo(@ApiParam(value = "课程基本信息") @RequestBody CourseInfoForm courseInfoForm){
        String id = eduCourseService.saveCourseInfo(courseInfoForm);
        return Result.ok().data("id",id);
    }

    @ApiOperation(value = "回显课程表单信息")
    @GetMapping("/getCourseFormInfo/{id}")
    public Result getCourseFormInfo(@PathVariable(value = "id") String id){
        CourseInfoForm courseInfoForm=eduCourseService.getCourseFormInfo(id);
        return Result.ok().data("courseForm",courseInfoForm);
    }

    @ApiOperation(value = "修改课程信息")
    @PostMapping("/updateInfo")
    public Result updateInfo(@ApiParam(value = "课程基本信息")@RequestBody CourseInfoForm courseInfoForm){
        eduCourseService.updateCourseInfo(courseInfoForm);
        return Result.ok();
    }
    @ApiOperation(value = "课程最终发布确认")
    @GetMapping("/getPublishinfo/{id}")
    public Result getPublishInfo(@PathVariable("id") String id){
        CoursePublishVo coursePublishVo=eduCourseService.selectCoursePublishVoById(id);
        return Result.ok().data("coursePublishInfo",coursePublishVo);
    }

    @ApiOperation(value = "发布完成")
    @GetMapping("/publishCourse/{id}")
    public Result publishCourse(@PathVariable String id){
        EduCourse eduCourse=new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");
        eduCourseService.updateById(eduCourse);
        return Result.ok();
    }

    @ApiOperation(value = "课程信息获取")
    @GetMapping("/getCourseInfo")
    public Result getCourseInfo(String id){
        List<EduCourse> list = eduCourseService.list(null);
        return Result.ok().data("CourseQuery",list);
    }

    @ApiOperation(value = "分页查询课程信息")
    @PostMapping("/getCourseInfoByPage/{current}/{limit}")
    public Result getCourseInfoByPage(@PathVariable("current") Integer current, @PathVariable("limit") Integer limit, @RequestBody CourseQuery courseQuery){

        Page<EduCourse> page=new Page<>(current,limit);
       IPage<EduCourse> page1 = eduCourseService.pageQuery(page,courseQuery);
        List<EduCourse> records = page1.getRecords();
        long total = page1.getTotal();
        return Result.ok().data("list",records).data("total",total);
    }

    @ApiOperation(value = "删除课程信息")
    @DeleteMapping("/removeCourseInfo/{courseId}")
    public Result removeCourseInfo(@PathVariable("courseId") String courseId){


        boolean result= eduCourseService.removeCourseInfo(courseId);
       if(result){
           return Result.ok();
       }else{
           return Result.error().message("删除失败");
       }
    }

    @ApiOperation(value = "课程信息详情")
    @GetMapping("/getCourseAllInfo/{courseId}")
    public Result getCourseAllInfo(@ApiParam("课程id")@PathVariable("courseId") String id, HttpServletRequest request){
        //课程信息课程讲师信息
       CourseWebVo courseWebVo=eduCourseService.getCourseAllInfo(id);
        //章节信息
        List<ChapterVo> chapterList =eduChapterService.getChapterList(id);

        String courseId=id;
        String memberId = JWTUtils.getMemberIdByJwtToken(request);
        if(memberId==null){
            return Result.error().message("请先登录");
        }
        boolean orderStatus = feignOrderService.getOrderStatus(courseId, memberId);

        return Result.ok().data("courseList",courseWebVo).data("chapterList",chapterList).data("isBuy",orderStatus);
    }

    @GetMapping("/getCourseForOrder/{courseId}")
    public CourseWebVo getCourseForOrder(@PathVariable("courseId")String courseId){
        CourseWebVo courseAllInfo = eduCourseService.getCourseAllInfo(courseId);
        return courseAllInfo;
    }

}

