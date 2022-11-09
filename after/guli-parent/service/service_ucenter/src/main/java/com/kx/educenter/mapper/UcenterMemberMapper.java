package com.kx.educenter.mapper;

import com.kx.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author kx
 * @since 2022-10-19
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    Integer getRegisterCount(String day);
}
