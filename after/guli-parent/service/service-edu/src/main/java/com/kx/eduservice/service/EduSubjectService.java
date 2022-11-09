package com.kx.eduservice.service;

import com.kx.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kx.eduservice.entity.classify.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author kx
 * @since 2022-10-16
 */
public interface EduSubjectService extends IService<EduSubject> {

    void getExcelData(MultipartFile file, EduSubjectService subjectService);

    List<OneSubject> getClassifySubject();
}
