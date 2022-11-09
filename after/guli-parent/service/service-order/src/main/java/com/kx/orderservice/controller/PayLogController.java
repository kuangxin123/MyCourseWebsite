package com.kx.orderservice.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.kx.commonutils.Result;
import com.kx.orderservice.service.PayLogService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author kx
 * @since 2022-10-30
 */
@RestController
@RequestMapping("/orderservice/paylog")
//@CrossOrigin
public class PayLogController {
    @Autowired
    private PayLogService payLogService;

    @ApiOperation("生成二维码")
    @GetMapping("/createNative/{orderNo}")
    public Result createNative(@PathVariable String orderNo){
        Map map=payLogService.createNative(orderNo);
        return Result.ok().data(map);
    }

    @ApiOperation("获取支付状态接口")
    @GetMapping("/queryPayStatus/{orderNo}")
    public Result queryPayStatus(@PathVariable String orderNo){
        //调用查询接口
        Map<String, String> map = payLogService.queryPayStatus(orderNo);
        if (map == null) {//出错
            return Result.error().message("支付出错");
        }
        if (map.get("trade_state").equals("SUCCESS")) {//如果成功
            //更改订单状态
            payLogService.updateOrderStatus(map);
            return Result.ok().message("支付成功");
        }

        return Result.ok().code(25000).message("支付中");
    }
}

