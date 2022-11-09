package com.kx.msmservice.controller;

import com.kx.commonutils.Result;
import com.kx.msmservice.service.MsmService;
import com.kx.msmservice.util.RomdomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.OverridesAttribute;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName MsmControlelr
 * @Description TODO
 * @Author kuang
 * @Date 2022/10/19 19:47
 */
@RestController
//@CrossOrigin
@RequestMapping("/msmservice")
public class MsmController {
    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @GetMapping("/getCode/{phone}")
    public Result getCode(@PathVariable("phone") String phone){

        //从redis获取验证码，如果获取到直接返回
        String code=redisTemplate.opsForValue().get(phone);

        if(StringUtils.isEmpty(phone)){
            return Result.ok();
        }
        //如果redis获取不到，进行阿里云返回

     code = RomdomUtil.getFourBitRandom();
        System.out.println("**********************************验证码："+code+"**********************************************");
        Map<String,Object> param= new HashMap<>();
        param.put("code",code);
        boolean is=msmService.send(phone,param);
       if(is){
           //发送成功，把发送成功的验证码放到redis中
           //设置有效时间
           redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
           return Result.ok();
       }else{
           return Result.error();
       }
    }
}
