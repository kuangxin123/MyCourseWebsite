package com.kx.staservice.schedule;

import com.kx.staservice.service.StatisticsDailyService;
import com.kx.staservice.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName ScheduleTask
 * @Description TODO
 * @Author kuang
 * @Date 2022/11/5 14:47
 */
@Component
public class ScheduleTask {
    @Autowired
    private StatisticsDailyService staService;

    @Scheduled(cron = "0 0 1 * * ?")
    public void task2(){
        //获取上一天的日期
        String s = DateUtil.formatDate(DateUtil.addDays(new Date(), -1));
        staService.insertRegisterCount(s);
    }
}
