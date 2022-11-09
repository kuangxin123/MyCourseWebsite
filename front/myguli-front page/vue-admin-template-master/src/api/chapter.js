import request from '@/utils/request'

export default{ 
    getChapterList(courseId){
        return request({
            url:`/eduservice/eduChapter/getList/${courseId}`,
            method:'get'
        })
    },
    //添加章节
    insertChapter(ChapterInfo){
        return request({
            url:`/eduservice/eduChapter/insertChapter`,
            method:'post',
            data:ChapterInfo
        })
    },
    //修改章节
    updateChapter(ChapterInfo){
        return request({
            url:`/eduservice/eduChapter/updateChapter`,
            method:'post',
            data:ChapterInfo
        })
    },
    //查询章节
    selectChapter(chapterId){
        return request({
            url:`/eduservice/eduChapter/selectChapter/${chapterId}`,
            method:'get'
        })
    },
    //删除章节
    deleteChapter(chapterId){
        return request({
            url:`/eduservice/eduChapter/deleteChapter/${chapterId}`,
            method:'delete'
        })
    }
}
