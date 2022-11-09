import request from '@/utils/request'


export default {
  getPageList(page, limit, searchObj) {
    return request({
      url: `/eduservice/getCourseInfoByQuery/${page}/${limit}`,
      method: 'post',
      data: searchObj
    })
  },
  getCourseAllInfo(){
    
  },
  // 获取课程二级分类
  getNestedTreeList2() {
    return request({
      url: `/eduservice/subject/getClassifyData`,
      method: 'get'
    })
  }
}