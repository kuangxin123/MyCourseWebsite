package com.kx.eduservice.test;

import com.alibaba.excel.EasyExcel;
import com.kx.eduservice.entity.ExcelData;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ExcelTest
 * @Description TODO
 * @Author kuang
 * @Date 2022/10/15 17:05
 */
public class ExcelTest {
    public static void main(String[] args) {
            String filePath="F:\\kx.xlsx";

        List<ExcelData> list=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new ExcelData(1,"学生"+i));
        }
        EasyExcel.write(filePath,ExcelData.class).sheet("学生表").doWrite(list);
    }
}
