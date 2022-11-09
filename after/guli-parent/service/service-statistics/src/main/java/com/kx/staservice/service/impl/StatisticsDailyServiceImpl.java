package com.kx.staservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kx.commonutils.Result;
import com.kx.staservice.entity.StatisticsDaily;
import com.kx.staservice.mapper.StatisticsDailyMapper;
import com.kx.staservice.service.FeignUcenterService;
import com.kx.staservice.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author kx
 * @since 2022-11-04
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {
    @Autowired
    private FeignUcenterService feignUcenterService;

    @Override
    public void insertRegisterCount(String day) {
        Result registerCount = feignUcenterService.getRegisterCount(day);
        Integer registerCount1 = (Integer) registerCount.getData().get("RegisterCount");
        QueryWrapper<StatisticsDaily> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("date_calculated",day);
        baseMapper.delete(queryWrapper);
        //将某天注册人数存入statistic表中
        StatisticsDaily statisticsDaily=new StatisticsDaily();
        statisticsDaily.setDateCalculated(day);
        statisticsDaily.setRegisterNum(registerCount1);
        statisticsDaily.setLoginNum(registerCount1);

        statisticsDaily.setVideoViewNum(RandomUtils.nextInt(100,200));
        statisticsDaily.setCourseNum(RandomUtils.nextInt(100,200));
        baseMapper.insert(statisticsDaily);

    }

    @Override
    public Map<String, Object> getShowData(String type, String begin, String end) {
        QueryWrapper<StatisticsDaily> queryWrapper=new QueryWrapper<>();
       // queryWrapper.select(type);
        queryWrapper.between("date_calculated",begin,end);
        List<StatisticsDaily> statisticsDailies = baseMapper.selectList(queryWrapper);

        //人数数组
        List<Integer> number=new ArrayList<>();
        //日期数组
        List<String> staDate=new ArrayList<>();

        for (int i = 0; i < statisticsDailies.size(); i++) {
            StatisticsDaily statisticsDaily = statisticsDailies.get(i);
            staDate.add(statisticsDaily.getDateCalculated());
            switch (type){
                case "login_num":number.add(statisticsDaily.getLoginNum());break;
                case "register_num":number.add(statisticsDaily.getRegisterNum());break;
                case "video_view_num":number.add(statisticsDaily.getVideoViewNum());break;
                case "course_num":number.add(statisticsDaily.getCourseNum());break;
            }
        }
        Map<String,Object> map=new HashMap<>();
        map.put("number",number);
        map.put("staDate",staDate);
        return map;
    }
}
