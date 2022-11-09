package com.kx.eduservice.entity;

import lombok.Data;

/**
 * @ClassName CoursePublishVo
 * @Description TODO
 * @Author kuang
 * @Date 2022/10/19 0:13
 */
@Data
public class CoursePublishVo {
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}
