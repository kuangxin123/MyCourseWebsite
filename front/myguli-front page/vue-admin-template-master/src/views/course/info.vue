<template>

  <div class="app-container">
    <h2 style="text-align: center;">发布新课程</h2>

    <el-steps :active="1" process-status="wait" align-center style="margin-bottom: 40px;">
      <el-step title="填写课程基本信息"/>
      <el-step title="创建课程大纲"/>
      <el-step title="提交审核"/>
    </el-steps>

    <el-form label-width="120px">
        <el-form-item label="课程标题">
          <el-input v-model="courseInfo.title" placeholder=" 示例：机器学习项目课：从基础到搭建项目视频课程。专业名称注意大小写"/>
        </el-form-item>

        <!-- 所属分类 TODO -->

        <!-- 所属分类：级联下拉列表 -->
          <!-- 一级分类 -->
          <el-form-item label="课程类别">
            <el-select
              v-model="courseInfo.subjectParentId"
              placeholder="请选择" @change="subjectOneChanged">
              <el-option
                v-for="subject in subjectNestedList"
                :key="subject.id"
                :label="subject.title"
                :value="subject.id"/>
            </el-select>
          <!-- 二级分类 -->
        <el-select v-model="courseInfo.subjectId" placeholder="请选择">
          <el-option
            v-for="subject in subSubjectList"
            :key="subject.id"
            :label="subject.title"
            :value="subject.id"/>
        </el-select>
        </el-form-item>
        <!-- 课程讲师 -->
      <el-form-item label="课程讲师">
        <el-select
          v-model="courseInfo.teacherId"
          placeholder="请选择">
          <el-option
            v-for="teacher in teacherList"
            :key="teacher.id"
            :label="teacher.name"
            :value="teacher.id"/>
        </el-select>
      </el-form-item>

        <el-form-item label="总课时">
          <el-input-number :min="0" v-model="courseInfo.lessonNum" controls-position="right" placeholder="请填写课程的总课时数"/>
        </el-form-item>

        <!-- 课程简介 TODO -->
<!-- 课程简介-->
<el-form-item label="课程简介">
    <tinymce :height="300" v-model="courseInfo.description"/>
</el-form-item>
        <!-- 课程封面 TODO -->

        <!-- 课程封面-->
        <el-form-item label="课程封面">

          <el-upload
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
            :action="BASE_API+'/ossservice/upfile'"
            class="avatar-uploader">
            <img :src="courseInfo.cover">
          </el-upload>

        </el-form-item>

        <el-form-item label="课程价格">
          <el-input-number :min="0" v-model="courseInfo.price" controls-position="right" placeholder="免费课程请设置为0元"/> 元
        </el-form-item>

      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">保存并下一步</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
 
 import course from '@/api/course.js'
 import teacher from '@/api/teacher.js'
 import subject from '@/api/subject.js'
import Tinymce from '@/components/Tinymce'


export default {
 components: { Tinymce },

  data() {
    return {
      saveBtnDisabled: false,// 保存按钮是否禁用
      courseInfo:{
        title: '',
        subjectId: '',
        subjectParentId:'',
        teacherId: '',
        lessonNum: 0,
        description: '',
        price: 0,
        cover:"/vue-admin-template-master/static/01.jpg"
      },
      teacherList: [], // 讲师列表
      BASE_API:process.env.VUE_APP_BASE_API,
      subjectNestedList: [],//一级分类列表
      subSubjectList: []//二级分类列表
    }
  },

  created() {
    console.log('info created');

     if (this.$route.params && this.$route.params.id) {
        const id = this.$route.params.id
        //根据id获取课程基本信息
        this.getCourseInfo(id)
        this.getALlTeacher();
    } else{
    this.getALlTeacher();
    this.getOneSubject();
    }
  },

  methods: {

//上传封面成功
    handleAvatarSuccess(res,file){
        this.courseInfo.cover=res.data.url;
    },
    subjectOneChanged(value){
        //value：一级分类id值
        for(let i=0;i<this.subjectNestedList.length;i++){
            if(this.subjectNestedList[i].id===value){
              this.subSubjectList=this.subjectNestedList[i].children
                this.courseInfo.subjectId=''
            }
        }
          
    },
    //查询所有的一级分类
    getOneSubject(){
      subject.getSubjectData().then(response=>{
        this.subjectNestedList=response.data.list;
      })
    },    
      getALlTeacher(){
          teacher.getList().then(response=>{
              this.teacherList=response.data.items;
          })

      },
      save(){
          course.saveCourseInfo(this.courseInfo).then(response=>{
          this.$message({
            type:'success',
            message:'添加课程信息成功'
          });
       this.$router.push({ path: '/edu/course/chapter/'+response.data.id})
        })
      },
      update(){
          course.updateCourseInfo(this.courseInfo).then(response=>{
            this.$message({
              type:'success',
              message:'修改课程信息成功'
            });
            this.$router.push({path:'/edu/course/chapter'+this.courseInfo.id})
          })
      },
      saveOrUpdate(){
             if (this.$route.params && this.$route.params.id) {
                this.update();
             }else{
                  this.save();
             }
      },
    getCourseInfo(id){
      course.getCourseFormInfoById(id).then(response=>{
        //在courseInfo课程基本信息，包含一级分类id和二级分类id
        this.courseInfo=response.data.courseForm;
        subject.getSubjectData().then(response=>{
          //获取所有一级分类
          this.subjectNestedList=response.data.list;
          for(var i=0;i<this.subjectNestedList.length;i++){
            //获取每个一级分类
            var oneSubjectList=this.subjectNestedList[i];
         //比较当前courseInfo里面一级分类id和所有的一级分类id
         if(this.courseInfo.subjectParentId===oneSubjectList.id){
           this.subSubjectList=oneSubjectList.children;
         }
          }
        })
      })
    }
  }
}
</script>