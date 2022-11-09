package com.kx.eduservice.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kx.commonutils.Result;
import com.kx.eduservice.entity.EduTeacher;
import com.kx.eduservice.service.EduTeacherService;
import com.kx.eduservice.utils.TeacherQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author kx
 * @since 2022-10-09
 */
@RestController
@RequestMapping("/eduservice")
@Api(description = "讲师管理")
//@CrossOrigin
public class EduTeacherController {
    @Autowired
    EduTeacherService eduTeacherService;

    @ApiOperation(value = "所有讲师列表")
    @GetMapping("/getAllTeacher")
    public Result<EduTeacher> getAllTeacher(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return Result.ok().data("items",list);
    }


    @ApiOperation(value = "根据id删除讲师")
    @DeleteMapping("/deleteTeacherById/{id}")
    public Result<EduTeacher> deleteTeacherById(@ApiParam(name = "id",value = "讲师ID",required = true) @PathVariable(value = "id") String id){
        boolean b = eduTeacherService.removeById(id);
        return Result.ok();
    }
    @ApiOperation(value = "根据id查询讲师")
    @GetMapping(value = "/getTeacherInfo/{id}")
    public Result<EduTeacher> getTeacherById(@PathVariable(value = "id") String id){
        EduTeacher teacher = eduTeacherService.getById(id);
        return Result.ok().data("teacher",teacher);
    }

    @ApiOperation(value = "讲师分页列表")
    @GetMapping("/getTeacherByPage/{currentPage}/{limit}")
    public Result<EduTeacher> getTeacherByPage(@ApiParam(name = "currentPage",value = "当前页码",required = true)@PathVariable(value = "currentPage") int currentPage,
                                               @ApiParam(name = "limit",value = "每页显示条数",required = true)@PathVariable(value = "limit") int limit){
        Page<EduTeacher> page=new Page<>(currentPage,limit);
        IPage<EduTeacher> page1 = eduTeacherService.page(page, null);
        List<EduTeacher> records = page1.getRecords();
        long total = page1.getTotal();
        long total1 = page.getPages();
        return Result.ok().data("data",records).data("total",total1);
    }

    @ApiOperation(value = "讲师分页列表条件查询")
    @PostMapping("/getTeacherByPage1/{currentPage}/{limit}")
    public Result<EduTeacher> getTeacherByPage1(@ApiParam(name = "currentPage",value = "当前页码",required = true)@PathVariable(value = "currentPage") int currentPage,
                                                @ApiParam(name = "limit",value = "每页显示条数",required = true)@PathVariable(value = "limit") int limit,
                                                @ApiParam(name="teacherQuery",value = "查询对象",required = false)@RequestBody TeacherQuery teacherQuery){
        Page<EduTeacher> page=new Page<>(currentPage,limit);
        IPage<EduTeacher> page1 = eduTeacherService.pageQuery(page, teacherQuery);
        List<EduTeacher> records = page1.getRecords();
        long total = page1.getTotal();
        return Result.ok().data("data",records).data("total",total);
    }


    //http://localhost:8001/eduservice/saveTeacher
    @ApiOperation(value = "新增讲师")
    @PostMapping("/saveTeacher")
    public Result saveTeacher(@ApiParam(name = "teacher",value = "讲师对象",required = true)
                              @RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);
        return Result.ok();
    }

    //http://localhost:8001/eduservice/updateTeacherById
    @ApiOperation(value = "根据ID修改讲师")
    @PostMapping("/updateTeacherById")
    public Result updateById(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher teacher){
        eduTeacherService.updateById(teacher);
        return Result.ok();
    }
}

