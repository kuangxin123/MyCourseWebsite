package com.kx.educenter.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName ConstantPropertiesUtil
 * @Description TODO
 * @Author kuang
 * @Date 2022/10/24 20:27
 */
@Component
public class ConstantPropertiesUtil implements InitializingBean {
    @Value(value = "${wx.open.app_id}")
    private String appId;
    @Value(value = "${wx.open.app_secret}")
    private  String appSecret;
    @Value(value = "${wx.open.redirect_url}")
    private String redirectUrl;

    public static String WX_OPEN_APP_ID;
    public static String WX_OPEN_APP_SECRECT;
    public static String WX_OPEN_APP_REDIRECT_URL;


    @Override
    public void afterPropertiesSet() throws Exception {
        WX_OPEN_APP_ID=appId;
        WX_OPEN_APP_SECRECT=appSecret;
        WX_OPEN_APP_REDIRECT_URL=redirectUrl;
    }
}
