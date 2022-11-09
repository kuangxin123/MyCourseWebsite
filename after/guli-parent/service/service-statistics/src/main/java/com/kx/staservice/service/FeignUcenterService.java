package com.kx.staservice.service;

import com.kx.commonutils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-ucenter")
public interface FeignUcenterService {
    @GetMapping("/educenter/ucentermember/getRegisterCount/{day}")
    public Result getRegisterCount(@PathVariable("day")String day);
}
