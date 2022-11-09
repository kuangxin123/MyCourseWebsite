package com.kx.educenter.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kx.commonutils.JWTUtils;
import com.kx.commonutils.Result;
import com.kx.educenter.entity.UcenterMember;
import com.kx.educenter.entity.vo.LoginVo;
import com.kx.educenter.entity.vo.RegisterVo;
import com.kx.educenter.service.UcenterMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author kx
 * @since 2022-10-19
 */

@Api(description = "用户控制器")
@RestController
@RequestMapping("/educenter/ucentermember")
//@CrossOrigin
public class UcenterMemberController {
    @Autowired
    private UcenterMemberService ucenterMemberService;

    //登录
    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public Result loginUser(@RequestBody UcenterMember ucenterMember){

        if(StringUtils.isEmpty(ucenterMember.getMobile())||StringUtils.isEmpty(ucenterMember.getPassword())){
            return Result.error();
        }
        //调用service方法实现登录
        //返回token，使用jwt生成
       String token = ucenterMemberService.login(ucenterMember);
        return Result.ok().data("token",token);
    }

    //注册
    @ApiOperation(value = "注册")
    @PostMapping("/registerUser")
    public Result registerUser(@RequestBody RegisterVo registerVo){

        //检验参数

        String register = ucenterMemberService.register(registerVo);
        return Result.ok().data("message",register);
    }

    @ApiOperation(value = "根据token获取用户信息")
    @GetMapping("/getLoginInfo")
    public Result getLoginInfo(HttpServletRequest request){
            String id = JWTUtils.getMemberIdByJwtToken(request);
            UcenterMember member=ucenterMemberService.getLoginInfo(id);
            return Result.ok().data("loginInfo",member);
    }

    //根据用户id获取用户信息
    @GetMapping("/getUcenterPay/{memberId}")
    public UcenterMember getUcenterPay(@PathVariable("memberId") String memberId){
        QueryWrapper<UcenterMember> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",memberId);
        UcenterMember one = ucenterMemberService.getOne(queryWrapper);
        return one;
    }

    @PostMapping("/getInfoUc/{id}")
    public UcenterMember getInfoUc(@PathVariable("id") String id){
        UcenterMember byId = ucenterMemberService.getById(id);
        return byId;
    }

    @GetMapping("getRegisterCount/{day}")
    public Result getRegisterCount(@PathVariable("day")String day){
       Integer count= ucenterMemberService.getRegisterCount(day);
       return Result.ok().data("RegisterCount",count);
    }
}

