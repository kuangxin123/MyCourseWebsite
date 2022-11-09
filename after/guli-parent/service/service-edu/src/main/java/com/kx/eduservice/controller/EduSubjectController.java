package com.kx.eduservice.controller;


import com.kx.commonutils.Result;
import com.kx.eduservice.entity.classify.OneSubject;
import com.kx.eduservice.service.EduSubjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author kx
 * @since 2022-10-16
 */
@RestController
@RequestMapping("/eduservice/subject")
//@CrossOrigin
public class EduSubjectController {
    @Autowired
    EduSubjectService subjectService;

    @ApiOperation(value = "Excel批量导入")
    @PostMapping("/getExcelData")
    public Result getExcelData(MultipartFile file){
        subjectService.getExcelData(file,subjectService);
        return Result.ok();
    }

    ///eduservice/subject/getClassifyData`,
    @ApiOperation(value = "课程分类显示")
    @GetMapping("/getClassifyData")
    public Result getClassifySubject(){
       List<OneSubject> list = subjectService.getClassifySubject();
        return  Result.ok().data("list",list);
    }
}

