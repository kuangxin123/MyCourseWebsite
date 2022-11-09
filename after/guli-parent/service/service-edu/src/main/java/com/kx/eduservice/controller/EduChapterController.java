package com.kx.eduservice.controller;


import com.kx.commonutils.Result;
import com.kx.eduservice.entity.EduChapter;
import com.kx.eduservice.entity.form.ChapterVo;
import com.kx.eduservice.service.EduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author kx
 * @since 2022-10-17
 */
@RestController
@RequestMapping("/eduservice/eduChapter")
//@CrossOrigin
@Api(description = "章节控制器")
public class EduChapterController {

    @Autowired
    private EduChapterService eduChapterService;

    @ApiOperation(value = "获取章节小节数据")
    @GetMapping("/getList/{courseId}")
    public Result getList(@ApiParam(name = "courseId",value = "课程id") @PathVariable(value = "courseId") String courseId){

        List<ChapterVo> chapterVoList=eduChapterService.getChapterList(courseId);
        return Result.ok().data("list",chapterVoList);
    }

    @ApiOperation(value = "新增章节数据")
    @PostMapping("/insertChapter")
    public Result insertChapter(@ApiParam(name = "chapter",value = "章节对象")@RequestBody EduChapter eduChapter){
        boolean save = eduChapterService.save(eduChapter);
        return  Result.ok();
    }

    @ApiOperation(value = "查询章节数据根据id")
    @GetMapping("/selectChapter/{id}")
    public Result selectChapter(@PathVariable(value = "id") String id){
        EduChapter eduChapter = eduChapterService.getById(id);
        return Result.ok().data("eduChapter",eduChapter);
    }

    @ApiOperation(value = "修改章节数据")
    @PostMapping("/updateChapter")
    public Result updateChapter(@RequestBody EduChapter eduChapter){
        boolean b = eduChapterService.updateById(eduChapter);
        return Result.ok();
    }

    @ApiOperation("/删除章节数据")
    @DeleteMapping ("/deleteChapter/{id}")
    public Result deleteChapter(@PathVariable(value = "id") String id){
        boolean result = eduChapterService.deleteChapter(id);
        if(result){
            return Result.ok();
        }else{
            return Result.error().message("不能删除");
        }
    }
}

