<template>
  <div id="aCoursesList" class="bg-fa of">
    <!-- 课程详情 开始 -->
    <section class="container">

      <!-- 课程所属分类 开始 -->

      <!-- /课程所属分类 结束 -->

      <!-- 课程基本信息 开始 -->

      <!-- /课程基本信息 结束 -->

      <div class="mt20 c-infor-box">
        <article class="fl col-7">
          <section class="mr30">
            <div class="i-box">
              <div>
                <section id="c-i-tabTitle" class="c-infor-tabTitle c-tab-title">
                  <a name="c-i" class="current" title="课程详情">课程详情</a>
                </section>
              </div>
              <article class="ml10 mr10 pt20">

                <!-- 课程详情介绍 开始 -->

                <!-- /课程详情介绍 结束 -->

                <!-- 课程大纲 开始-->

                <!-- /课程大纲 结束 -->
              </article>
            </div>
          </section>
        </article>
        <aside class="fl col-3">
          <div class="i-box">
            <!-- 主讲讲师 开始-->

            <!-- /主讲讲师 结束 -->
          </div>
        </aside>
        <div class="clear"/>
      </div>
    </section>
    <!-- /课程详情 结束 -->
  </div>
</template>

<script>
import course from '@/api/course'
import comment from '@/api/commonedu'
import order from '@/api/order'

export default {
  data() {
    return {
      data:{},
      page:1,
      limit:4,
      total:10,
      comment:{
      },
      courseId:'',
      courseInfo:{},
      chapterVideoList:[],
      isbuyCourse:false,
      courseList:{},
      chapterList:[]
    }
  },
  created() {
    console.log("11111111111")
    this.courseId=this.$route.params.id
    console.log(this.courseId)
    this.getAllInfo()
    this.initComment()
  },
  methods:{
    initComment(){
       comment.getPageList(this.page, this.limit, this.courseId).then(response => {
           this.data = response.data.data
       })
    },
    getAllInfo(){
    course.getCourseAllInfo(this.courseId)
                      .then(response=>{                
                          this.courseList=response.data.data.courseList,
                          this.chapterList=response.data.data.chapterList          
                      })
                      },
    addComment(){
        this.comment.courseId = this.courseId
        this.comment.teacherId = this.courseList.teacherId
        comment.addComment(this.comment).then(response => {
            if(response.data.success){
                this.comment.content = ''
                this.initComment()
            }
        })
    },
    gotoPage(page){
          comment.getPageList(page, this.limit,this.courseId).then(response => {
              this.data = response.data.data
          })
      },
          //根据课程id，调用接口方法生成订单
    createOrder(){
        order.createOrder(this.courseId).then(response => {
            if(response.data.success){
                //订单创建成功，跳转到订单页面
                this.$router.push({ path: '/order/'+ response.data.data.orderId })
            }
        })
    }
  }
};
</script>
