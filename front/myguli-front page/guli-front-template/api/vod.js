import request from '@/utils/request'

export default{
    getPlayAuth(id){
        return request({
            url:`/servicevod/video/getVideo/${id}`,
            method:'get'
        })
    }
}