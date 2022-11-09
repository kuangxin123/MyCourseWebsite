import request from '@/utils/request'

export default{
    getListData(){
        return request({
            url:`/eduservice/getHotCourse`,
            method:'get'
        })
    }
}