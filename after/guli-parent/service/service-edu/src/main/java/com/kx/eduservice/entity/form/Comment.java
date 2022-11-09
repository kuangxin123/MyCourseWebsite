package com.kx.eduservice.entity.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Comment
 * @Description TODO
 * @Author kuang
 * @Date 2022/10/30 16:10
 */
@Data
@AllArgsConstructor@NoArgsConstructor
public class Comment {
    private String content;
    private String courseId;
}
