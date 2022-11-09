package com.kx.eduservice.controller.frontcontroller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kx.commonutils.Result;
import com.kx.eduservice.entity.EduCourse;
import com.kx.eduservice.entity.EduTeacher;
import com.kx.eduservice.entity.form.CourseQuery;
import com.kx.eduservice.entity.front.QueryCourse;
import com.kx.eduservice.service.EduCourseService;
import com.kx.eduservice.service.EduTeacherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.RequestWrapper;
import java.util.List;
import java.util.Map;

/**
 * @ClassName EduCourseFrontController
 * @Description TODO
 * @Author kuang
 * @Date 2022/10/23 17:03
 */
@RestController
//@CrossOrigin
@RequestMapping("/eduservice")
public class EduCourseFrontController {
    @Autowired
    private EduCourseService eduCourseService;
    @Autowired
    private EduTeacherService eduTeacherService;

    @GetMapping("/getHotCourse")
    public Result getHotCourse(){
        QueryWrapper<EduCourse> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("view_count");
        queryWrapper.last("limit 4");
        List<EduCourse> list = eduCourseService.list(queryWrapper);

        QueryWrapper<EduTeacher> queryWrapper1=new QueryWrapper<>();
        queryWrapper1.orderByDesc("id");
        queryWrapper1.last("limit 8");
        List<EduTeacher> list1 = eduTeacherService.list(queryWrapper1);

        return Result.ok().data("HotCourse",list).data("HotTeacher",list1);

    }

    @ApiOperation(value = "根据条件查询课程并分页")
    @PostMapping("/getCourseInfoByQuery/{page}/{limit}")
    public Result getCourseInfoByQuery(@ApiParam(name = "page",value = "当前页码")
                                           @PathVariable(value = "page") long page,
                                       @ApiParam(name = "limit",value = "每页记录数")
                                       @PathVariable("limit") long limit,
                                       @ApiParam(name = "CourseQuery",value = "条件封装对象")
                                       @RequestBody QueryCourse courseQuery){
        Page<EduCourse> eduCoursePage=new Page<>(page,limit);
      Map<String,Object> map = eduCourseService.getCourseInfoByQuery(eduCoursePage,courseQuery);
      return Result.ok().data(map);
    }
}
