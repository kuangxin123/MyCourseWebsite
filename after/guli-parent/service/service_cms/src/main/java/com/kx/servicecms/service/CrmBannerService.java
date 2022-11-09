package com.kx.servicecms.service;

import com.kx.servicecms.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author kx
 * @since 2022-10-23
 */
public interface CrmBannerService extends IService<CrmBanner> {

    List<CrmBanner> getBanners();
}
