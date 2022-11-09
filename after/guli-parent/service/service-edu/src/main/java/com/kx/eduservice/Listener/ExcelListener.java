package com.kx.eduservice.Listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.kx.eduservice.entity.ExcelData;

import java.util.Map;

/**
 * @ClassName ExcelListener
 * @Description TODO
 * @Author kuang
 * @Date 2022/10/15 17:15
 */
public class ExcelListener extends AnalysisEventListener<ExcelData> {

    //一行一行去读取excel内容
    @Override
    public void invoke(ExcelData excelData, AnalysisContext analysisContext) {
        System.out.println(excelData+"###########");

    }

    //读取excel表头内容
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("#############"+headMap);
    }


    //读取完成后执行
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
