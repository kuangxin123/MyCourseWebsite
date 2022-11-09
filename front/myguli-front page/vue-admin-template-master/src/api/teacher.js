import request from '@/utils/request'

export default{

    getTeacherListByPage(current,limit,teacherQuery) {
        return request({
          url: `/eduservice/getTeacherByPage1/${current}/${limit}`,
          method: 'post',
          data: teacherQuery
        })
    },

    deleteTeacherByid(id){
        return request({
            url: `/eduservice/deleteTeacherById/${id}`,
            method: 'delete'
        })
    },
    insertTeacher(teacher){
        return request({
            url:`/eduservice/saveTeacher`,
            method:'post',
            data: teacher
        })
    },

    getTeacherInfo(id){
        return request({
            url:`/eduservice/getTeacherInfo/${id}`,
            method:'get'
        })
    },
    updateTeacherById(teacher){
        return request({
            url:`/eduservice/updateTeacherById`,
            method:'post',
            data:teacher
        })
    },
    getList(){
        return request({
            url:'/eduservice/getAllTeacher',
            method:'get'
        })
    }


}
