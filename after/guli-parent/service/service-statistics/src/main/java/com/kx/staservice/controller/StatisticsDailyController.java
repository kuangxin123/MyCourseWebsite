package com.kx.staservice.controller;


import com.kx.commonutils.Result;
import com.kx.staservice.service.StatisticsDailyService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author kx
 * @since 2022-11-04
 */
@RestController
//@CrossOrigin
@RequestMapping("/staservice/statisticsDaily")
@Api(description = "统计分析")
public class StatisticsDailyController {
    @Autowired
    private StatisticsDailyService staService;
    @GetMapping("/insertRegisterCount/{day}")
    public Result insertRegisterCount(@PathVariable("day") String day){
        //远程调用ucenter_member，增加注册人数到stadialy表中
            staService.insertRegisterCount(day);
            return Result.ok();
    }

    @GetMapping("/getShowData/{type}/{begin}/{end}")
    public Result getShowData(@PathVariable("type") String type,@PathVariable("begin")String begin,@PathVariable("end")String end){
      Map<String,Object> map = staService.getShowData(type,begin,end);
      return Result.ok().data("number",map.get("number")).data("staDate",map.get("staDate"));
    }
}

