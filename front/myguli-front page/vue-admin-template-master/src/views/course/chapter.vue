<template>

  <div class="app-container">

    <h2 style="text-align: center;">发布新课程</h2>

    <el-steps :active="2" process-status="wait" align-center style="margin-bottom: 40px;">
      <el-step title="填写课程基本信息"/>
      <el-step title="创建课程大纲"/>
      <el-step title="提交审核"/>
    </el-steps>
    
        <el-button type="text" @click="insertDialog()">添加章节</el-button>
        <!-- 章节 -->
        <ul class="chanpterList">
            <li
                v-for="chapter in chapterList"
                :key="chapter.id">
                <p>
                    {{ chapter.title }}

                    <span class="acts">
                   <el-button style="" type="text" @click="insertVideoDialog(chapter.id)">添加小节</el-button>
                        <el-button style="" type="text" @click="editChapter(chapter.id)">编辑</el-button>
                        <el-button type="text" @click="delete1(chapter.id)">删除</el-button>
                    </span>
                </p>

                <!-- 视频 -->
                <ul class="chanpterList videoList">
                    <li
                        v-for="video in chapter.children"
                        :key="video.id">
                        <p>{{ video.title }}
                            <span class="acts">
                                <el-button type="text">编辑</el-button>
                                <el-button type="text" @click="removeVideo(video.id)">删除</el-button>
                            </span>
                        </p>  
                    </li>
                </ul>
            </li>
        </ul>
        <div>
            <el-button @click="previous">上一步</el-button>
            <el-button :disabled="saveBtnDisabled" type="primary" @click="next">下一步</el-button>
        </div>

                  <!-- 添加和修改章节表单 -->
          <el-dialog :visible.sync="dialogChapterFormVisible" title="添加章节">
              <el-form :model="chapter" label-width="120px">
                  <el-form-item label="章节标题">
                      <el-input v-model="chapter.title"/>
                  </el-form-item>
                  <el-form-item label="章节排序">
                      <el-input-number v-model="chapter.sort" :min="0" controls-position="right"/>
                  </el-form-item>
              </el-form>
              <div slot="footer" class="dialog-footer">
                  <el-button @click="dialogChapterFormVisible = false">取 消</el-button>
                  <el-button type="primary" @click="saveOrUpdate">确 定</el-button>
              </div>
          </el-dialog>

          <!-- 添加和修改课时表单 -->
          <el-dialog :visible.sync="dialogVideoFormVisible" title="添加课时">
            <el-form :model="video" label-width="120px">
              <el-form-item label="课时标题">
                <el-input v-model="video.title"/>
              </el-form-item>
              <el-form-item label="课时排序">
                <el-input-number v-model="video.sort" :min="0" controls-position="right"/>
              </el-form-item>
              <el-form-item label="是否免费">
                <el-radio-group v-model="video.free">
                  <el-radio :label="true">免费</el-radio>
                  <el-radio :label="false">默认</el-radio>
                </el-radio-group>
              </el-form-item>
     <el-form-item label="上传视频">
    <el-upload
           :on-success="handleVodUploadSuccess"
           :on-remove="handleVodRemove"
           :before-remove="beforeVodRemove"
           :on-exceed="handleUploadExceed"
           :file-list="fileList"
           :action="BASE_API+'/servicevod/video/uploadFile'"
           :limit="1"
           class="upload-demo">
    <el-button size="small" type="primary">上传视频</el-button>
    <el-tooltip placement="right-end">
        <div slot="content">最大支持1G，<br>
            支持3GP、ASF、AVI、DAT、DV、FLV、F4V、<br>
            GIF、M2T、M4V、MJ2、MJPEG、MKV、MOV、MP4、<br>
            MPE、MPG、MPEG、MTS、OGG、QT、RM、RMVB、<br>
            SWF、TS、VOB、WMV、WEBM 等视频格式上传</div>
        <i class="el-icon-question"/>
    </el-tooltip>
    </el-upload>
</el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogVideoFormVisible = false">取 消</el-button>
              <el-button :disabled="saveVideoBtnDisabled" type="primary" @click="saveOrUpdateVideo">确 定</el-button>
            </div>
          </el-dialog>
  </div>
  
</template>
<style scoped>
.chanpterList{
    position: relative;
    list-style: none;
    margin: 0;
    padding: 0;
}
.chanpterList li{
  position: relative;
}
.chanpterList p{
  float: left;
  font-size: 20px;
  margin: 10px 0;
  padding: 10px;
  height: 70px;
  line-height: 50px;
  width: 100%;
  border: 1px solid #DDD;
}
.chanpterList .acts {
    float: right;
    font-size: 14px;
}

.videoList{
  padding-left: 50px;
}
.videoList p{
  float: left;
  font-size: 14px;
  margin: 10px 0;
  padding: 10px;
  height: 50px;
  line-height: 30px;
  width: 100%;
  border: 1px dotted #DDD;
}

</style>

<script>
import chapter from '@/api/chapter'
import video from '@/api/video'
export default {
  data() {
    return {
      dialogChapterFormVisible:false,
      dialogVideoFormVisible:false,
      saveBtnDisabled: false,// 保存按钮是否禁用
      courseId:'',
      chapterList:[],
      chapter:{
        title:'',
        sort:0
      },
      fileList:[],
      video:{
            title: '',
            sort: 0,
            free: 0,
            videoSourceId: '',
            videoOriginalName:''

      },
        BASE_API:process.env.VUE_APP_BASE_API
    }
  },

  created() {
    console.log('chapter created')
    if(this.$route.params && this.$route.params.id){
      this.courseId=this.$route.params.id;
      this.getChapterDataByCourseId();
    }
  },

  methods: {
    handleVodRemove(file, fileList){
      video.deleteAliyunVideo(this.video.videoSourceId).then(response=>{
        this.$message({
        type: 'success',
        message: response.message
      })
      this.fileList=[];
      this.video.videoSourceId='';
      this.video.videoOriginalName=''
      })
    },

    beforeVodRemove(file,fileList){
       return this.$confirm(`确定移除 ${file.name}？`)
    },
handleVodUploadSuccess(response,file){
    this.video.videoSourceId = response.data.videoId
    this.video.videoOriginalName=file.name;
},
    handleUploadExceed(){
        this.$message.warning('想要重新上传视频，请先删除已上传的视频')
    },

//#########################小节操作##########################################################
  insertVideoDialog(id){

    this.dialogVideoFormVisible=true;
    this.video.courseId=this.courseId;
    this.video.chapterId=id;
    
  },
    saveVideo(){
        video.insertVideo(this.video).then(response=>{
                   this.dialogVideoFormVisible=false;
           this.$message({
            type: 'success',
            message: '保存成功!'
          })
    
            this.getChapterDataByCourseId();
        })
    },
    saveOrUpdateVideo(){
      if(!this.video.id){
        this.saveVideo();
        this.fileList=[]
      }
    },
    removeVideo(id){
        this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
            video.deleteVideo(id)
            .then(response=>{
                this.$message({
                 type: 'success',
                message: '删除成功!'
          });
           this.getChapterDataByCourseId();
            })
        })
    },

    //#########################################章节操作####################################
    delete1(id){
           this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
            chapter.deleteChapter(id)
            .then(response=>{
                this.$message({
                 type: 'success',
                message: '删除成功!'
          });
           this.getChapterDataByCourseId();
            })
        })
    },
    editChapter(id){
     this.saveBtnDisabled = false
        this.dialogChapterFormVisible=true;
        chapter.selectChapter(id).then(response=>{
          this.chapter=response.data.eduChapter;
        })
      },
    insertDialog(){
        this.saveBtnDisabled = false
        this.dialogChapterFormVisible=true;
        this.chapter.title='';
        this.chapter.sort=0;
    },
    save(){
        this.saveBtnDisabled = false
          this.chapter.courseId=this.courseId;
         chapter.insertChapter(this.chapter)
              .then(response=>{
                        this.dialogChapterFormVisible=false;
                   this.$message({
                    type: 'success',
                    message: '保存成功!'
                  })
                      this.getChapterDataByCourseId();
              })
    },
    update(){
        this.saveBtnDisabled = false
        chapter.updateChapter(this.chapter).then(response=>{
                  this.dialogChapterFormVisible=false;
          this.$message({
          type: 'success',
          message: '修改成功!'
        })
            this.getChapterDataByCourseId();
        })
    },
    saveOrUpdate(){
        this.saveBtnDisabled = false
      if(!this.chapter.id){
        this.save();
      }else{
        this.update();
      }
    },
    getChapterDataByCourseId(){
      chapter.getChapterList(this.courseId).then(response=>{
          this.chapterList=response.data.list;
      })
    },
    previous() {
      console.log('previous')
      this.$router.push({ path: '/edu/course/info/'+this.courseId })
    },

    next() {
      console.log('next')
      this.$router.push({ path: '/edu/course/publish/'+this.courseId })
    }
  }
}
</script>