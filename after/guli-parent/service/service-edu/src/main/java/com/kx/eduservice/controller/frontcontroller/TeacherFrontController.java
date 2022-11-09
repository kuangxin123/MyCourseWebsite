package com.kx.eduservice.controller.frontcontroller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kx.commonutils.Result;
import com.kx.eduservice.entity.EduCourse;
import com.kx.eduservice.entity.EduTeacher;
import com.kx.eduservice.service.EduCourseService;
import com.kx.eduservice.service.EduTeacherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName TeacherFrontController
 * @Description TODO
 * @Author kuang
 * @Date 2022/10/26 16:04
 */
@RestController
//@CrossOrigin
@RequestMapping("/eduservice/teacherfront")
public class TeacherFrontController {
    @Autowired
    private EduTeacherService eduTeacherService;

    @Autowired
    private EduCourseService courseService;
    @PostMapping("/getTeacherFrontList/{page}/{limit}")
    public Result getTeacherFrontList(@PathVariable long page,@PathVariable long limit){

        Page<EduTeacher> eduTeacherPage=new Page<>(page,limit);
        Map<String,Object> map = eduTeacherService.getTeacherList(eduTeacherPage);
        return Result.ok().data(map);
    }
    @ApiOperation(value = "根据讲师id查询讲师课程信息")
    @GetMapping("/getTeacherCourse/{id}")
    public Result getTeacherCourse(@ApiParam("讲师id") @PathVariable("id") String id){
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        List<EduCourse> eduCourse=courseService.getCourseByTeacherId(id);
        return Result.ok().data("teacher",eduTeacher).data("courseList",eduCourse);
    }

}
