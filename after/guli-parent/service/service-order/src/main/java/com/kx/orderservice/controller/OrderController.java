package com.kx.orderservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kx.commonutils.Result;
import com.kx.orderservice.entity.Order;
import com.kx.orderservice.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author kx
 * @since 2022-10-30
 */
@RestController
@RequestMapping("/orderservice")
//@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;

    //根据课程id和用户id创建订单，返回订单id
    @PostMapping("createOrder/{courseId}")
    public Result createOrder(@PathVariable("courseId")String courseId, HttpServletRequest request){
        String orderId=orderService.createOrder(courseId,request);
        return Result.ok().data("orderId",orderId);
    }
    @ApiOperation("通过订单id获取订单信息")
    @GetMapping("/getOrder/{orderId}")
    public Result getOrder(@PathVariable("orderId")String orderId){
        QueryWrapper<Order> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("order_no",orderId);
        Order order=orderService.getOne(queryWrapper);
        return Result.ok().data("order",order);
    }

    //根据courseId和用户Id查询订单状态
    @GetMapping("/getOrderStatus/{courseId}/{memberId}")
    public boolean getOrderStatus(@ApiParam(name = "courseId",value = "课程id")@PathVariable("courseId")String courseId, @ApiParam(name = "memberId",value = "用户id")@PathVariable("memberId")String memberId){
        QueryWrapper<Order> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        queryWrapper.eq("member_id",memberId);
        queryWrapper.eq("status",1);
        int count = orderService.count(queryWrapper);
        if(count>0){
            return true;
        }else{
            return false;
        }
    }
}

