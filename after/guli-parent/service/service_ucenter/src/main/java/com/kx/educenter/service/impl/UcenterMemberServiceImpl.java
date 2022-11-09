package com.kx.educenter.service.impl;

import com.alibaba.nacos.common.util.Md5Utils;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kx.commonutils.JWTUtils;
import com.kx.commonutils.Result;
import com.kx.educenter.entity.UcenterMember;
import com.kx.educenter.entity.vo.LoginVo;
import com.kx.educenter.entity.vo.RegisterVo;
import com.kx.educenter.mapper.UcenterMemberMapper;
import com.kx.educenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kx.educenter.util.MD5;
import org.apache.xmlbeans.impl.jam.mutable.MMember;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Member;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author kx
 * @since 2022-10-19
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {
    @Autowired
    private UcenterMemberService memberService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Override
    public String login(UcenterMember ucenterMember) {
        String mobile = ucenterMember.getMobile();
        String passWord= ucenterMember.getPassword();
        //根据手机获取信息
        QueryWrapper<UcenterMember> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("mobile",mobile);
        UcenterMember member = memberService.getOne(queryWrapper);
        //检验密码
        if(!MD5.encrypt(passWord).equals(member.getPassword())){
            return "error";
        }
        //检验是否被禁用
        if(member.getIsDisabled()){
            return "error";
        }

        //使用jwt生成token
        String token= JWTUtils.getJwtToken(member.getId(), member.getNickname());
        return token;
    }

    @Override
    public String register(RegisterVo registerVo) {
        //获取注册信息，进行校验
        String nickname = registerVo.getNickname();
        String mobile = registerVo.getMobile();
        String password = registerVo.getPassword();
        String code = registerVo.getCode();

        if(StringUtils.isEmpty(mobile)||StringUtils.isEmpty(password)||StringUtils.isEmpty(code)||StringUtils.isEmpty(nickname)){
            return "数据为空";
        }
       //从redis获取发送的验证码
        String mobileCode=  redisTemplate.opsForValue().get(mobile);
        if(!code.equals(mobileCode)){
            return "验证码错误";
        }
        //查询数据库是否存在相同的手机号码
        Integer mobile1 = baseMapper.selectCount(new QueryWrapper<UcenterMember>().eq("mobile", mobile));
        if(mobile1>0){
            return "手机号存在";
        }
        //添加注册信息到数据库
        UcenterMember ucenterMember=new UcenterMember();
        ucenterMember.setNickname(nickname);
        ucenterMember.setMobile(mobile);
        ucenterMember.setPassword(MD5.encrypt(password));
        ucenterMember.setIsDisabled(false);
        this.save(ucenterMember);
        return "成功";
    }

    @Override
    public UcenterMember getLoginInfo(String id) {
        QueryWrapper<UcenterMember> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",id);
        UcenterMember ucenterMember = baseMapper.selectOne(queryWrapper);


        return ucenterMember;
    }

    @Override
    public UcenterMember selectSameUser(String openid) {
        QueryWrapper<UcenterMember> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("openid",openid);
        UcenterMember ucenterMember = baseMapper.selectOne(queryWrapper);
        return ucenterMember;
    }

    @Override
    public Integer getRegisterCount(String day) {
        //得到某天用户注册的人数
        Integer count = baseMapper.getRegisterCount(day);
        return count;
    }
}
