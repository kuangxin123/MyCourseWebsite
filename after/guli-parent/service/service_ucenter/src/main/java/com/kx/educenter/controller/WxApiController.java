package com.kx.educenter.controller;

import com.google.gson.Gson;
import com.kx.commonutils.JWTUtils;
import com.kx.educenter.entity.UcenterMember;
import com.kx.educenter.service.UcenterMemberService;
import com.kx.educenter.util.ConstantPropertiesUtil;
import com.kx.educenter.util.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * @ClassName WxApiController
 * @Description TODO
 * @Author kuang
 * @Date 2022/10/24 20:31
 */
@Controller
//@CrossOrigin
@RequestMapping("/api/ucenter/wx")
public class WxApiController {

    @Autowired
    private UcenterMemberService ucenterMemberService;

    @GetMapping("/callback")
    public String  callback(String code,String state){
        //获取code值，临时票据，类似于验证码
        String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid=%s" +
                "&secret=%s" +
                "&code=%s" +
                "&grant_type=authorization_code";

        //拼接三个参数：id密钥和code值
        String accessTokenUrl = String.format(baseAccessTokenUrl,
                ConstantPropertiesUtil.WX_OPEN_APP_ID,
                ConstantPropertiesUtil.WX_OPEN_APP_SECRECT,
                code);
        //拿着code请求微信固定的地址，得到两个值access_token和openId
        //使用httpclient发送请求得到返回结果
        try {
            String info = HttpClientUtils.get(accessTokenUrl);
            Gson gson =new Gson();
            HashMap<String,String> hashMap = gson.fromJson(info, HashMap.class);
            String access_token = hashMap.get("access_token");
            String openid = hashMap.get("openid");

           UcenterMember ucenterMember= ucenterMemberService.selectSameUser(openid);

            //根据openid查询是否存在相同微信信息
            if(ucenterMember==null){
                //拿着得到的access_token和openid ,再去请求微信提供的固定地址，获取到扫码人信息

                String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                        "?access_token=%s" +
                        "&openid=%s";
                String userInfoUrl = String.format(baseUserInfoUrl, access_token, openid);
                //发送请求
                String s = HttpClientUtils.get(userInfoUrl);
                //获取返回userinfo字符串扫描人信息
                HashMap hashMap1 = gson.fromJson(s, HashMap.class);
                //昵称
                String nickname=(String)hashMap1.get("nickname");

                //头像
                String avatar=(String)hashMap1.get("headimgurl");
                UcenterMember member=new UcenterMember();
                member.setNickname(nickname);
                member.setAvatar(avatar);
                member.setOpenid(openid);
                //把扫码人信息添加到数据库
                //openid
                //nickname
                //avatar
                boolean save = ucenterMemberService.save(member);

                //使用jwt根据member对象生成token字符串
                String jwtToken = JWTUtils.getJwtToken(member.getId(), member.getNickname());
                //最后返回首页面通过路径传递token字符串
                return "redirect:http://localhost:3000?token="+jwtToken;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "信息错误";
    }
    @GetMapping("/login")
    public String genQrConnect(){
        // 微信开放平台授权baseUrl
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";

        String redirectUrl= ConstantPropertiesUtil.WX_OPEN_APP_REDIRECT_URL;

        try {
            redirectUrl=URLEncoder.encode(redirectUrl,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String state = "imhelen";
        //生成qrcodeUrl
        String qrcodeUrl = String.format(
                baseUrl,
                ConstantPropertiesUtil.WX_OPEN_APP_ID,
                redirectUrl,
                "atguigu");

        return "redirect:" + qrcodeUrl;


    }
}
