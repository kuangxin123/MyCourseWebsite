import request from '@/utils/request'

export default{

  getSubjectData() {
    return request({
      url: `/eduservice/subject/getClassifyData`,
      method: 'get'
        })
}

}
