package com.kx.eduservice.entity.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName CourseQuery
 * @Description TODO
 * @Author kuang
 * @Date 2022/10/20 16:47
 */
@Data
@ApiModel(value = "Course查询对象", description = "课程查询对象封装")
public class CourseQuery {

        private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "课程名称")
        private String title;

        @ApiModelProperty(value = "讲师id")
        private String teacherId;

        @ApiModelProperty(value = "一级类别id")
        private String subjectParentId;

        @ApiModelProperty(value = "二级类别id")
        private String subjectId;

    }
