import request from '@/utils/request'

export default {

  createStatistics(day) {
    return request({
      url: `/staservice/statisticsDaily/insertRegisterCount/${day}`,
      method: 'get'
    })
  },
  getShowData(){

  }
}