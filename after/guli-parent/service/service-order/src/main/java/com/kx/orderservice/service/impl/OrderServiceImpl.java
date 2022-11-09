package com.kx.orderservice.service.impl;

import com.kx.commonutils.JWTUtils;
import com.kx.educenter.entity.UcenterMember;
import com.kx.eduservice.entity.form.CourseWebVo;
import com.kx.orderservice.entity.Order;
import com.kx.orderservice.mapper.OrderMapper;
import com.kx.orderservice.service.EduFeignClient;
import com.kx.orderservice.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kx.orderservice.service.UcenterFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author kx
 * @since 2022-10-30
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private EduFeignClient eduFeignClient;
    @Autowired
    private UcenterFeignClient ucenterFeignClient;
    @Override
    public String createOrder(String courseId, HttpServletRequest request) {
        //创建订单
        String memberIdByJwtToken = JWTUtils.getMemberIdByJwtToken(request);
        CourseWebVo courseForOrder = eduFeignClient.getCourseForOrder(courseId);
        UcenterMember infoUc = ucenterFeignClient.getInfoUc(memberIdByJwtToken);

        Order order=new Order();
        order.setOrderNo(UUID.randomUUID().toString().substring(0,8));
        order.setCourseId(courseId);
        order.setCourseTitle(courseForOrder.getTitle());
        order.setCourseCover(courseForOrder.getCover());
        order.setTeacherName(courseForOrder.getTeacherName());
        order.setMemberId(infoUc.getId());
        order.setNickname(infoUc.getNickname());
        order.setMobile(infoUc.getMobile());
        order.setTotalFee(courseForOrder.getPrice());
        order.setPayType(1);
        order.setStatus(0);
        baseMapper.insert(order);
        return order.getOrderNo();
    }
}
