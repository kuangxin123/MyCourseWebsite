package com.kx.eduservice.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ExcelSubjectData
 * @Description TODO
 * @Author kuang
 * @Date 2022/10/16 9:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelSubjectData {
    @ExcelProperty(index = 0)
    private String oneSubjectName;
    @ExcelProperty(index = 1)
    private String twoSubjectName;
}
