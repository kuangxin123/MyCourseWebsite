import request from '@/utils/request'

export default{ 

    //添加小节
    insertVideo(VideoInfo){
        return request({
            url:`/eduservice/eduVideo/insertVideo`,
            method:'post',
            data:VideoInfo
        })
    },
    //修改小节
    updateVideo(VideoInfo){
        return request({
            url:`/eduservice/eduVideo/updataVideo`,
            method:'post',
            data:VideoInfo
        })
    },

    //删除小节
    deleteVideo(VideoId){
        return request({
            url:`/eduservice/eduVideo/deleteVideo/${VideoId}`,
            method:'delete'
        })
    },
    deleteAliyunVideo(videoId){
        return request({
            url:`/servicevod/video/deleteVideo/${videoId}`,
            method:'delete'
        })
    }
}
