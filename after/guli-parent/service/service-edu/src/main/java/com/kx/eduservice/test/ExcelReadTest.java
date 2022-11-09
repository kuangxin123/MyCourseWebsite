package com.kx.eduservice.test;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.kx.eduservice.Listener.ExcelListener;
import com.kx.eduservice.entity.ExcelData;

/**
 * @ClassName ExcelReadTest
 * @Description TODO
 * @Author kuang
 * @Date 2022/10/15 17:19
 */
public class ExcelReadTest {
    public static void main(String[] args) {
        String filePath="F:\\kx.xlsx";
        EasyExcel.read(filePath, ExcelData.class,new ExcelListener()).sheet().doRead();
    }
}
