package com.kx.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kx.eduservice.Listener.ExcelSubjectListener;
import com.kx.eduservice.entity.EduSubject;
import com.kx.eduservice.entity.ExcelSubjectData;
import com.kx.eduservice.entity.classify.OneSubject;
import com.kx.eduservice.entity.classify.TwoSubject;
import com.kx.eduservice.mapper.EduSubjectMapper;
import com.kx.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.xml.bind.marshaller.NoEscapeHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author kx
 * @since 2022-10-16
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {


    @Override
    public void getExcelData(MultipartFile file, EduSubjectService subjectService){
        try {
            InputStream inputStream=file.getInputStream();
            EasyExcel.read(inputStream, ExcelSubjectData.class,new ExcelSubjectListener(subjectService)).sheet().doRead();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OneSubject> getClassifySubject() {


        //获取一级分类数据(parentid=0)
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("parent_id","0");
       List<EduSubject> oneEduSubject = baseMapper.selectList(queryWrapper);

        //获取二级分类数据
        QueryWrapper queryWrapper1=new QueryWrapper();
        queryWrapper.ne("parent_id","0");
        List<EduSubject> TwoEduSubject = baseMapper.selectList(queryWrapper1);

        List<OneSubject> finalList=new ArrayList<>();



        //封装一级分类数据
        for (int i = 0; i < oneEduSubject.size(); i++) {
            EduSubject eduSubject = oneEduSubject.get(i);
            OneSubject oneSubject=new OneSubject();
            oneSubject.setId(eduSubject.getId());
            oneSubject.setTitle(eduSubject.getTitle());
            finalList.add(oneSubject);
            List<TwoSubject> twoSubjectList =new ArrayList<>();
            for (int j = 0; j < TwoEduSubject.size(); j++) {
                EduSubject eduSubject1 = TwoEduSubject.get(j);
                TwoSubject twoSubject=new TwoSubject();
                if(eduSubject1.getParentId().equals(oneSubject.getId())){
                    BeanUtils.copyProperties(eduSubject1,twoSubject);
                    twoSubjectList.add(twoSubject);
                }
            }
            oneSubject.setChildren(twoSubjectList);
        }

        //封装二级分类数据
        return finalList;
    }
}
