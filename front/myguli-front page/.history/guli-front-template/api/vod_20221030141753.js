import request from '@/utils/request'

export default{
    getPalyAuth(id){
        return request({
            url:`/servicevod/video/getVideo/${id}`,
            method:'get'
        })
    }
}