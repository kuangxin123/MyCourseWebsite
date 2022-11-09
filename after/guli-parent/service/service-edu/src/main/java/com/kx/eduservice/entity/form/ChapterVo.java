package com.kx.eduservice.entity.form;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ChapterVo
 * @Description TODO
 * @Author kuang
 * @Date 2022/10/18 8:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "章节信息")
public class ChapterVo {
    private String id;
    private String title;
    private List<VideoVo> children = new ArrayList<>();
}
