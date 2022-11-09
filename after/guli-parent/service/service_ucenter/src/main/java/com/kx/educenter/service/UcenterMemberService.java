package com.kx.educenter.service;

import com.kx.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kx.educenter.entity.vo.LoginVo;
import com.kx.educenter.entity.vo.RegisterVo;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author kx
 * @since 2022-10-19
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(UcenterMember ucenterMember);

    String register(RegisterVo registerVo);

    UcenterMember getLoginInfo(String id);

    UcenterMember selectSameUser(String openid);

    Integer getRegisterCount(String day);
}
