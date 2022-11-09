import request from '@/utils/request'

const api_name = '/eduservice/teacherfront/getTeacherFrontList/{page}/{limit}'
export default {
  getPageList(page, limit) {   
    return request({
      url: `${api_name}/${page}/${limit}`,
      method: 'get'
    })
  }
}