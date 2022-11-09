package com.kx.eduservice.service;

import com.kx.commonutils.JWTUtils;
import com.kx.commonutils.Result;
import com.kx.educenter.entity.UcenterMember;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName SaveVideoUserService
 * @Description TODO
 * @Author kuang
 * @Date 2022/10/30 16:11
 */
@Component
@FeignClient(name = "service-ucenter")
public interface SaveVideoUserService {
        //根据id获取用户信息
        //根据用户id获取用户信息
        @GetMapping("/educenter/ucentermember/getUcenterPay/{memberId}")
        public UcenterMember getUcenterPay(@PathVariable("memberId") String memberId);
}
