package com.kx.staservice.service;

import com.kx.commonutils.Result;
import com.kx.staservice.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author kx
 * @since 2022-11-04
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    void insertRegisterCount(String day);

    Map<String, Object> getShowData(String type, String begin, String end);
}
