import request from '@/utils/request'

const api_name = '/edu/teacher'
export default {
  getPageList(page, limit) {   
    return request({
      url: `${api_name}/${page}/${limit}`,
      method: 'get'
    })
  }
}