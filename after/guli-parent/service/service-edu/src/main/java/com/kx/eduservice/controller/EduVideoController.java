package com.kx.eduservice.controller;


import com.kx.commonutils.Result;
import com.kx.eduservice.entity.EduVideo;
import com.kx.eduservice.service.EduVideoService;
import com.kx.eduservice.service.FeignVodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author kx
 * @since 2022-10-17
 */
@RestController
@RequestMapping("/eduservice/eduVideo")
//@CrossOrigin
public class EduVideoController {
    @Autowired
    private EduVideoService eduVideoService;
    @Autowired
    private FeignVodService feignVodService;
        @PostMapping("/insertVideo")
        public Result insertVideo(@RequestBody EduVideo eduVideo){
            boolean save = eduVideoService.save(eduVideo);
            return Result.ok();
        }

        @DeleteMapping("/deleteVideo/{id}")
    public Result deleteVideo(@PathVariable("id") String id){
            EduVideo byId = eduVideoService.getById(id);
            String videoSourceId = byId.getVideoSourceId();
            if(!StringUtils.isEmpty(videoSourceId)) {
                Result result = feignVodService.deleteVideo(videoSourceId);
            }
            //根据视频id删除云端视频
            boolean b = eduVideoService.removeById(id);
            return Result.ok();
        }

        @PostMapping("/updataVideo")
    public Result updateVideo(@RequestBody EduVideo eduVideo){
            boolean b = eduVideoService.updateById(eduVideo);
            return Result.ok();
        }
}

