<template>
  <div>

    <!-- 阿里云视频播放器样式 -->
    <link rel="stylesheet" href="https://g.alicdn.com/de/prismplayer/2.8.1/skins/default/aliplayer-min.css" >
    <!-- 阿里云视频播放器脚本 -->
    <script charset="utf-8" type="text/javascript" src="https://g.alicdn.com/de/prismplayer/2.8.1/aliplayer-min.js" />

    <!-- 定义播放器dom -->
    <div id="J_prismPlayer" class="prism-player" />
  </div>
</template>
<script>
import vod from '@/api/vod'
export default {
    
  layout: 'video',//应用video布局
  asyncData({ params, error }) {
    return vod.getPlayAuth(params.vid).then(response => {
      // console.log(response.data.data)
      return {
        vid: params.id,
        playAuth: response.data.data.playAuth
      }
    })
  },
  created(){
    console.log(this.playAuth)
  },
  /**
 * 页面渲染完成时：此时js脚本已加载，Aliplayer已定义，可以使用
 * 如果在created生命周期函数中使用，Aliplayer is not defined错误
 */
mounted() {
    
    new Aliplayer({
        id: 'J_prismPlayer',
        vid: this.vid, // 视频id
        playauth: "eyJTZWN1cml0eVRva2VuIjoiQ0FJU2h3TjFxNkZ0NUIyeWZTaklyNWVDTTk3T3JPaG16NG5hV2tIV3NrVXNlN3Q0aXZidWxqejJJSDFFZm5sb0FPa2FzUHd6bFd0UjZmd2Vsck1xRThBZkdoS1pNNUV1dnNnTW9WM3dKcExGc3QySjZyOEpqc1VvMXFRS29FYXBzdlhKYXNEVkVmbDJFNVhFTWlJUi8wMGU2TC8rY2lyWXBUWEhWYlNDbFo5Z2FQa09Rd0M4ZGtBb0xkeEtKd3hrMnQxNFVtWFdPYVNDUHdMU2htUEJMVXhtdldnR2wyUnp1NHV5M3ZPZDVoZlpwMXI4eE80YXhlTDBQb1AyVjgxbExacGxlc3FwM0k0U2M3YmFnaFpVNGdscjhxbHg3c3BCNVN5Vmt0eVdHVWhKL3phTElvaXQ3TnBqZmlCMGVvUUFQb3BGcC9YNmp2QWF3UExVbTliWXhncGhCOFIrWGo3RFpZYXV4N0d6ZW9XVE84MCthS3p3TmxuVXo5bUxMZU9WaVE0L1ptOEJQdzQ0RUxoSWFGMElVRXh6Rm1xQ2QvWDRvZ3lRTzE3eUdwTG9pdjltamNCSHFIeno1c2VQS2xTMVJMR1U3RDBWSUpkVWJUbHphazVNalRTNEsvTllLMUFkS0FvNFhlcVBNYXgzYlFGRHI1M3ZzVGJiWHpaYjBtcHR1UG56ZDFRSUNGZk1sRWVVR29BQlo4b2RubUJnMDQxOXY0KzJHT3BTdFJNdmNiMFRNMWRCaDJWYUxERWNpWFV0Y2NZR0FHR3cvL2dVaFEvNmd1cVB5VmpkQ2JGRlpDMW9YcyszWTJBbHYraEloT2dVZitlTVFyL250RFlxYUZoYWtuSll6Z2Y4VDVKZnQ0QWU2MmhIWERyY2RFOFJpRTYyY0ErcjRpckx0TWxwS2tJQzIwWkM0aEIvSmVaSGhsYz0iLCJBdXRoSW5mbyI6IntcIkNJXCI6XCJNcnJ0OVFldkdhYnFYZk5PUHllczVpaGd1d3dnakVsdHo5N1RsY2pFTWREQUVCckxFTHV2eTdEdE1QZlJhQkQyXCIsXCJDYWxsZXJcIjpcIjVyTW9Gcy9OVXZGeXoxNXNlWnlUSnJVYW5LUDZ4K2lXdE1YcDc0VXdJMWs9XCIsXCJFeHBpcmVUaW1lXCI6XCIyMDIyLTExLTAzVDE0OjQ0OjUwWlwiLFwiTWVkaWFJZFwiOlwiZjY0NDQzOTk2YzM0NDYzMjgwNjc3ZTU1NjNkMmI2ZjhcIixcIlNpZ25hdHVyZVwiOlwicEtkRW14YjN2cE8rNVFDSkxkSHlkUUtFZXNrPVwifSIsIlZpZGVvTWV0YSI6eyJTdGF0dXMiOiJOb3JtYWwiLCJWaWRlb0lkIjoiZjY0NDQzOTk2YzM0NDYzMjgwNjc3ZTU1NjNkMmI2ZjgiLCJUaXRsZSI6IjYgLSBXaGF0IElmIEkgV2FudCB0byBNb3ZlIEZhc3RlciIsIkNvdmVyVVJMIjoiaHR0cDovL291dGluLWE2YjdlZThmNTE1NzExZWQ4MjFmMDAxNjNlMDBiMTc0Lm9zcy1jbi1zaGFuZ2hhaS5hbGl5dW5jcy5jb20vZjY0NDQzOTk2YzM0NDYzMjgwNjc3ZTU1NjNkMmI2Zjgvc25hcHNob3RzLzM2ZDhlYWZkNjAwNzQ2ZTliNmRiZTFjMTVhZGI5M2JmLTAwMDAxLmpwZz9FeHBpcmVzPTE2Njc0OTAxOTAmT1NTQWNjZXNzS2V5SWQ9TFRBSTNEa3h0c2JVeU5ZViZTaWduYXR1cmU9RXE2ekV5VTBKeXhlZWYyZ2FXTFlzSlZHaEZvJTNEIiwiRHVyYXRpb24iOjE2LjI3Njd9LCJBY2Nlc3NLZXlJZCI6IlNUUy5OVDd4ZHpBN0d4SzFYZ2dWRXl3NFRlM0V0IiwiQWNjZXNzS2V5U2VjcmV0IjoiQnh3amdzazZZa0FTeEp0VGhUYzR0N2g5YzdCRkhDSDhkTERWMnJ2QlUzbXkiLCJSZWdpb24iOiJjbi1zaGFuZ2hhaSIsIkN1c3RvbWVySWQiOjEwNzI3NjU2MjY4NDM4Njl9", // 播放凭证
        encryptType: '1', // 如果播放加密视频，则需设置encryptType=1，非加密视频无需设置此项
        width: '100%',
        height: '500px',
        // 以下可选设置
       // cover: 'http://guli.shop/photo/banner/1525939573202.jpg', // 封面
        qualitySort: 'asc', // 清晰度排序

        mediaType: 'video', // 返回音频还是视频
        autoplay: true, // 自动播放
        isLive: false, // 直播
        rePlay: false, // 循环播放
        preload: true,
        controlBarVisibility: 'hover', // 控制条的显示方式：鼠标悬停
        useH5Prism: true, // 播放器类型：html5
    }, function(player) {
        console.log('播放器创建成功')
    })
}
}
</script>