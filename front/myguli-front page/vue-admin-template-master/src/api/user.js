import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/eduservice/user/login',
    method: 'post',
    data
  })
}

//http://localhost:9528/dev-api/vue-admin-template/user/login

export function getInfo(token) {
  return request({
    url: '/eduservice/user/getInfo',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/vue-admin-template/user/logout',
    method: 'post'
  })
}
