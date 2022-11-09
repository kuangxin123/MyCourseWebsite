

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
