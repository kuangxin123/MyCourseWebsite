import request from '@/utils/request'

export default{
    //查询前两条banner数据
    getListBanner(){
        return request({
            url:`/servicecms/frontbanner/getBanners`,
            method:'get'
        })
    },
    //课程全部信息
    getCourseAllInfo(){
        return request()
    }
}