import request from '@/utils/request'

const api_name = '/eduservice/teacherfront/getTeacherFrontList'
export default {
  getPageList(page, limit) {   
    return request({
      url: `${api_name}/${page}/${limit}`,
      method: 'post'
    })
  },
  getTeacherInfo(teacherId){
    return request({
      utl:``
    })
  }
}