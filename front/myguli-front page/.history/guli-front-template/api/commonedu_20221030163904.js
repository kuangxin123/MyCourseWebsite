import request from '@/utils/request'

export default {

  getPageList(page, limit, courseId) {
    return request({
      url: `/eduservice/educomment/${page}/${limit}`,
      method: 'get',
      params: {courseId}
    })
  },
  addComment(comment) {
    return request({
      url: `/eduservice/educomment`,
      method: 'post',
      data: comment
    })
  }
}