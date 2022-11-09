package com.kx.eduservice.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName ExcelData
 * @Description TODO
 * @Author kuang
 * @Date 2022/10/15 17:02
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExcelData {
    //设置表头名称
    @ExcelProperty(value = "学号",index = 0)
    private Integer sno;
    @ExcelProperty(value = "姓名",index = 1)
    private String name;
}
