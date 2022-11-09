import request from '@/utils/request'
export default {
  //登录
  submitLoginUser(userInfo) {
    return request({
      url: `/educenter/ucentermember/login`,
      method: 'post',
      data: userInfo
    })
  },
  //根据token获取用户信息
  getLoginInfo() {
    return request({
      url: `/educenter/ucentermember/getLoginInfo`,
      method: 'get',
     // headers: {'token': cookie.get('guli_token')}
    })
    //headers: {'token': cookie.get('guli_token')} 
  }
}