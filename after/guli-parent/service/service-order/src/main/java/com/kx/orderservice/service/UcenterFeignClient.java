package com.kx.orderservice.service;

import com.kx.educenter.entity.UcenterMember;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//@RequestMapping("/educenter/ucentermember")
@Component
@FeignClient(value = "service-ucenter")
public interface UcenterFeignClient {
    @PostMapping("/educenter/ucentermember/getInfoUc/{id}")
    public UcenterMember getInfoUc(@PathVariable("id") String id);
}
