package com.kx.eduservice.entity.classify;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName OneSubject
 * @Description TODO
 * @Author kuang
 * @Date 2022/10/16 17:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OneSubject {
    private  String id;
    private String title;
    private List<TwoSubject> children =new ArrayList<>();
}
