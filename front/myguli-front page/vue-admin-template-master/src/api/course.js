import request from '@/utils/request'

export default{

    saveCourseInfo(courseInfo) {
        return request({
          url: `/eduservice/eduCourse/insertInfo`,
          method: 'post',
          data:courseInfo
        })
    },
    getCourseFormInfoById(courseId){
        return request({
          url:`/eduservice/eduCourse/getCourseFormInfo/${courseId}`,
          method:'get'
        })
    },
    updateCourseInfo(courseInfo){
      return request({
        url:`/eduservice/eduCourse/updateInfo`,
        method:'post',
        data:courseInfo
      })   
    },
    getPublishinfo(courseId){
      return request({
        url:`/eduservice/eduCourse/getPublishinfo/${courseId}`,
        method:'get'
      })
  },
  publishCourse(courseId){
    return request({
      url:`/eduservice/eduCourse/publishCourse/${courseId}`,
      method:'get'
    })
},
    getCourseInfo(){
      return request({
        url:'/eduservice/eduCourse/getCourseInfo',
        method:'get'
      })
    },
    getSearchObj(current,limit,searchObj){
        return request({
          url:`/eduservice/eduCourse/getCourseInfoByPage/${current}/${limit}`,
          method:'post',
          data:searchObj
        })
    },
    deleteCourseInfo(courseId){
      return request({
        url:`/eduservice/eduCourse/removeCourseInfo/${courseId}`,
        method:'delete'
      })
    }

}
