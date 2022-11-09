package com.kx.eduservice.entity.form;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName VideoVo
 * @Description TODO
 * @Author kuang
 * @Date 2022/10/18 8:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "课时信息")
public class VideoVo {
    private String id;
    private String title;
    private String videoSourceId;
}
