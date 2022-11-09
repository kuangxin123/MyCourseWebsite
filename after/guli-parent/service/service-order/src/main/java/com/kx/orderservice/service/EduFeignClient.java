package com.kx.orderservice.service;

import com.kx.eduservice.entity.form.CourseWebVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "service-edu")
public interface EduFeignClient {

    @GetMapping("/eduservice/eduCourse/getCourseForOrder/{courseId}")
    public CourseWebVo getCourseForOrder(@PathVariable("courseId")String courseId);

}
