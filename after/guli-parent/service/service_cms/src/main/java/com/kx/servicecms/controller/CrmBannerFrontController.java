package com.kx.servicecms.controller;

import com.kx.commonutils.Result;
import com.kx.servicecms.entity.CrmBanner;
import com.kx.servicecms.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName CrmBannerFrontController
 * @Description TODO
 * @Author kuang
 * @Date 2022/10/23 16:06
 */
@RestController
@RequestMapping("/servicecms/frontbanner")
//@CrossOrigin
public class CrmBannerFrontController {
    @Autowired
    private CrmBannerService crmBannerService;

    @GetMapping("/getBanners")
    public Result getBanners(){
    List<CrmBanner> crmBanners = crmBannerService.getBanners();
    return Result.ok().data("bannerlist",crmBanners);
    }
}
