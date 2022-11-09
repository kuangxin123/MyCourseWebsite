package com.kx.orderservice.service;

import com.kx.orderservice.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author kx
 * @since 2022-10-30
 */
public interface OrderService extends IService<Order> {

    String createOrder(String courseId, HttpServletRequest request);
}
