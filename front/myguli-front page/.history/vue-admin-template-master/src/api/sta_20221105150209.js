import request from '@/utils/request'

export default {

  createStatistics(day) {
    return request({
      url: `/staservice/statisticsDaily/${day}`,
      method: 'post'
    })
  }
}