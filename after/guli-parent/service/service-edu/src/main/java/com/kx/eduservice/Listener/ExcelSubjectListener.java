package com.kx.eduservice.Listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kx.eduservice.ServiceEduMain8001;
import com.kx.eduservice.entity.EduSubject;
import com.kx.eduservice.entity.ExcelSubjectData;
import com.kx.eduservice.service.EduSubjectService;
import org.apache.poi.ss.formula.functions.T;

/**
 * @ClassName ExcelSubjectListener
 * @Description TODO
 * @Author kuang
 * @Date 2022/10/16 9:06
 */
public class ExcelSubjectListener extends AnalysisEventListener<ExcelSubjectData> {

    public EduSubjectService eduSubjectService;
    public ExcelSubjectListener(EduSubjectService service){
        eduSubjectService=service;
    }
//一行一行去读取excel内容
    @Override
    public void invoke(ExcelSubjectData excelSubjectData, AnalysisContext analysisContext) {
        String oneSubjectName = excelSubjectData.getOneSubjectName();
        String twoSubjectName = excelSubjectData.getTwoSubjectName();
        EduSubject eduSubject = existOneName(eduSubjectService, oneSubjectName);
        if(eduSubject==null){//没有重复的
            eduSubject=new EduSubject();
            eduSubject.setTitle(oneSubjectName);
            eduSubject.setParentId("0");
            eduSubjectService.save(eduSubject);
        }
        //获取一级分类id值
        String pid=eduSubject.getId();

        EduSubject eduSubject1 = existTwoName(eduSubjectService, twoSubjectName,pid);
        if(eduSubject1==null){
            eduSubject1=new EduSubject();
            eduSubject1.setParentId(pid);
            eduSubject1.setTitle(twoSubjectName);
            eduSubjectService.save(eduSubject1);
        }

    }
    //判断一级分类是否重复
    public EduSubject existOneName(EduSubjectService eduSubjectService,String name){
        QueryWrapper<EduSubject> wrapper=new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject one = eduSubjectService.getOne(wrapper);
        return one;
    }

    //判断二级分类是否重复
    public EduSubject existTwoName(EduSubjectService eduSubjectService,String name,String pid){
        QueryWrapper<EduSubject> wrapper=new QueryWrapper();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject Two = eduSubjectService.getOne(wrapper);
        return Two;
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
