import request from '@/utils/request'

export default {

  createStatistics(day) {
    return request({
      url: `/${day}`,
      method: 'post'
    })
  }
}