package com.kx.eduservice.service;

//import org.springframework.context.annotation.ComponentScan;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-order")
public interface FeignOrderService {
    @GetMapping("/orderservice/getOrderStatus/{courseId}/{memberId}")
    public boolean getOrderStatus(@ApiParam(name = "courseId",value = "课程id")@PathVariable("courseId")String courseId, @ApiParam(name = "memberId",value = "用户id")@PathVariable("memberId")String memberId);
}
