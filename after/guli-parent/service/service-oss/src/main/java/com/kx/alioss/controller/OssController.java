package com.kx.alioss.controller;

import com.kx.alioss.service.OssService;
import com.kx.commonutils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName OssController
 * @Description TODO
 * @Author kuang
 * @Date 2022/10/14 14:53
 */
@RestController
//@CrossOrigin
@RequestMapping("/ossservice")
@Api(description = "oss")
public class OssController {

    @Autowired
    OssService ossService;

    @ApiOperation(value = "上传文件功能")
    @PostMapping("/upfile")
    public Result upFile(MultipartFile file){
       String url = ossService.upFile(file);
       return Result.ok().data("url",url);
    }
}
