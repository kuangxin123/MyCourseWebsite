package com.kx.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kx.commonutils.JWTUtils;
import com.kx.commonutils.Result;
import com.kx.educenter.entity.UcenterMember;
import com.kx.eduservice.entity.EduComment;
import com.kx.eduservice.entity.form.Comment;
import com.kx.eduservice.service.EduCommentService;

import com.kx.eduservice.service.SaveVideoUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author kx
 * @since 2022-10-30
 */
@RestController
@RequestMapping("/eduservice/educomment")
//@CrossOrigin
public class EduCommentController {
    @Autowired
    private EduCommentService eduCommentService;
    @Autowired
    private SaveVideoUserService saveVideoUserService;

    @ApiOperation("根据课程id，查询评论分页")
    @GetMapping("/getComment/{page}/{limit}")
    public Result getComment(@PathVariable("page") long page,
             @PathVariable("limit") long limit,
             @RequestParam("courseId") String id){
        QueryWrapper<EduComment> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("course_id",id);
        queryWrapper.orderByDesc("gmt_create");
        Page<EduComment> commentPage=new Page<>(page,limit);
        IPage<EduComment> page1 = eduCommentService.page(commentPage, queryWrapper);
        List<EduComment> commentList = page1.getRecords();

        Map<String, Object> map = new HashMap<>();
        map.put("items", commentList);
        map.put("current", page1.getCurrent());
        map.put("pages", page1.getPages());
        map.put("size", commentPage.getSize());
        map.put("total", commentPage.getTotal());
        map.put("hasNext", commentPage.hasNext());
        map.put("hasPrevious", commentPage.hasPrevious());
        return Result.ok().data(map);
    }

    @ApiOperation("添加评论")
    @PostMapping("/saveComment")
    public Result saveComment(@RequestBody EduComment eduComment, HttpServletRequest request){
        String memberIdByJwtToken = JWTUtils.getMemberIdByJwtToken(request);
        if(StringUtils.isEmpty(memberIdByJwtToken)){
            return Result.error().message("请先登录");
        }

        UcenterMember ucenterPay = saveVideoUserService.getUcenterPay(memberIdByJwtToken);
        eduComment.setMemberId(memberIdByJwtToken);
        eduComment.setNickname(ucenterPay.getNickname());
        eduComment.setAvatar(ucenterPay.getAvatar());
        eduCommentService.save(eduComment);
        return Result.ok().message("评论成功");
    }

}

