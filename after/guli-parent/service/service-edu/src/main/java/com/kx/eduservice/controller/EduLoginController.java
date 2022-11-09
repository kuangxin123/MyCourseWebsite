package com.kx.eduservice.controller;

import com.kx.commonutils.Result;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.ResolutionSyntax;
import java.awt.geom.Area;

/**
 * @ClassName EduLoginController
 * @Description TODO
 * @Author kuang
 * @Date 2022/10/12 13:47
 */
@RestController
@RequestMapping("/eduservice/user")
//@CrossOrigin
public class EduLoginController {
    @PostMapping("/login")
    public Result login(){
        return Result.ok().data("token","admin");
    }

    @GetMapping("/getInfo")
    public Result getInfo(){
        return  Result.ok().data("name","kx").data("avatar","https://www.bilibili.com/favicon.ico");
    }


}
