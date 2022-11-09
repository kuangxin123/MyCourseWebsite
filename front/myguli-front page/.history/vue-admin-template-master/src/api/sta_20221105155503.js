import request from '@/utils/request'

export default {

  createStatistics(day) {
    return request({
      url: `/staservice/statisticsDaily/insertRegisterCount/${day}`,
      method: 'get'
    })
  },
  getShowData(searchObj){
        return request({
            url:`/staservice/statisticsDaily/getShowData/${searchObj.type}/${searchObj.begin}/${searchObj.end}`,
            
        })
  }
}