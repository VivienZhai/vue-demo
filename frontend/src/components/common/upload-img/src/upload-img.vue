<template>
  <div>
    <!-- 初始化图片链接 -->
    <div class="defaultImg">
      <img :src="addFile" :style="{'width': autoCropWidth + 'px', 'height': autoCropHeight + 'px'}" @click="openUpload()"/>
      <input type="file" :disabled="showOnly" ref="uploadInput" style="position:absolute; clip:rect(0 0 0 0);" accept="image/png, image/jpeg, image/gif, image/jpg" @change="readImg($event, 1)">
      <Spin fix v-if="spinShow">
        <Icon type="ios-loading" size=18 class="demo-spin-icon-load"></Icon>
        <div>正在上传...</div>
      </Spin>
    </div>
    <!-- 剪裁图片对话框 -->
    <Modal v-model="visibleModel" title="图片剪裁" @on-ok="ok" @on-cancel="cancel" :width="modelWidth">
      <div class="cropper-content">
        <div class="cropper" :style="{'width': cropperWidth + 'px', 'height': cropperHeight + 'px'}">
          <vueCropper
            ref="cropper"
            :img="uploadImg"
            :outputSize="option.outputSize"
            :outputType="option.outputType"
            :info="true"
            :full="option.full"
            :canMove="option.canMove"
            :canMoveBox="option.canMoveBox"
            :original="option.original"
            :autoCrop="option.autoCrop"
            :autoCropWidth="option.autoCropWidth"
            :autoCropHeight="option.autoCropHeight"
            :fixedBox="option.fixedBox"
            @realTime="realTime"
          ></vueCropper>
        </div>
        <div class="show-preview" :style="{'width': previews.w + 'px', 'height': previews.h + 'px',  'overflow': 'hidden', 'margin': '5px'}">
          <div :style="previews.div" class="preview">
            <img :src="previews.url" :style="previews.img">
          </div>
        </div>
      </div>
      <div class="msg">使用滚轮缩放图片</div>
    </Modal>
  </div>
</template>

<script>
  import VueCropper from "vue-cropper";
  import axios from 'axios';
  import add from "../styles/images/add.png";
  import config from '@/config/config';

  export default {
    props:{
      showOnly: {//不可点击， 为true 只可以展示图片，不可以上传图片
        type: Boolean,
        required: false,
        default: false
      },
      defaultImg:{//默认图片
        type:String,
        required:false,
        default:"",
      },
      cropperWidth:{//幕布宽度
        type: Number,
        required: false,
        default:240
      },
      cropperHeight:{//幕布高度
        type: Number,
        required: false,
        default:240
      },
      outputSize:{//裁剪生成图片的质量
        type: Number,
        required: false,
        default:1
      },
      full:{//是否输出原图比例的截图
        type: Boolean,
        required: false,
        default:false
      },
      outputType:{//裁剪生成图片的格式
        type: String,
        required: false,
        default:"png"
      },
      canMove:{//上传图片是否可以移动
        type: Boolean,
        required: false,
        default:true
      },
      original:{//上传图片按照原始比例渲染
        type: Boolean,
        required: false,
        default:false
      },
      canMoveBox:{//截图框能否拖动
        type: Boolean,
        required: false,
        default:true
      },
      autoCrop:{//是否默认生成截图框
        type: Boolean,
        required: false,
        default:true
      },
      autoCropWidth:{//默认生成截图框宽度
        type: Number,
        required: false,
        default:180
      },
      autoCropHeight:{//默认生成截图框高度
        type: Number,
        required: false,
        default:180
      },
      fixedBox:{//固定截图框大小 不允许改变
        type: Boolean,
        required: false,
        default:true
      },

      hasZoomSize:{//是否生成其他尺寸的图片，以剪裁后的为基准
        type:Boolean,
        required:false,
        default:false
      },
      zoomSize:{//其他生成图片的尺寸，形式：100-100,50-50
        type:String,
        required:false,
        default:""
      },

      otherParam:{//其他需要传递的参数
        type:String,
        required:false,
        default:""
      }
    },
    data() {
      return {
        addFile:add,
        fileName :"",
        uploadImg : add,
        previews: {},
        downImg: '#',
        visibleModel:false,
        viewImg:'',
        spinShow:false
      };
    },
    computed:{
      option:function () {
        return {
          outputSize: this.outputSize,
          full: this.full,
          outputType: this.outputType,
          canMove: this.canMove,
          original: this.original,
          canMoveBox: this.canMoveBox,
          autoCrop: this.autoCrop,
          autoCropWidth: this.autoCropWidth,
          autoCropHeight: this.autoCropHeight,
          fixedBox: this.fixedBox
        }
      },
      modelWidth:function () {
        return (this.cropperWidth + this.autoCropWidth + 80)+ "px";
      }
    },
    components: {
      VueCropper
    },
    methods: {
      openUpload(){
        this.$refs.uploadInput.click();
      },
      readImg (e, num) {
        //读取图片
        var file = e.target.files[0];
        if (!/\.(gif|jpg|jpeg|png|bmp|GIF|JPG|PNG)$/.test(e.target.value)) {
          alert('图片类型必须是.gif,jpeg,jpg,png,bmp中的一种');
          return false;
        }else{
          this.fileName = e.target.value;
          if(this.fileName.indexOf("/")>0){
            this.fileName = this.fileName.substring(this.fileName.lastIndexOf("/")+1);
          }
          if(this.fileName.indexOf("\\")>0){
            this.fileName = this.fileName.substring(this.fileName.lastIndexOf("\\")+1);
          }
        }
        var reader = new FileReader();
        var self = this;
        reader.onload = (e) => {
          let data;
          if (typeof e.target.result === 'object') {
            // 把Array Buffer转化为blob 如果是base64不需要
            data = window.URL.createObjectURL(new Blob([e.target.result]));
          } else {
            data = e.target.result;
          }
          self.uploadImg = data;
          self.visibleModel = true;
        };
        // 转化为blob
        reader.readAsArrayBuffer(file);
      },
      // 实时预览函数
      realTime (data) {
        this.previews = data;
      },
      ok(){
        var self = this;
        self.spinShow = true;
        this.$refs.cropper.getCropBlob((data) => {
          var afile = new File([data], this.fileName);
          let formData = new FormData();
          formData.append('file', afile);
          if(this.hasZoomSize){
            formData.append('sizes', this.zoomSize);
          }
          axios.post(config.fileServerContext + "/file/put" , formData).then(response => {
            if(response.data.code == "1"){
              var result = response.data.data;
              if(result.length>0){
                self.addFile = config.fileServerContext + "/file/get/"+ result[0].originalFileId;
                self.viewImg = self.addFile;
                result[0].otherParam = self.otherParam;
                self.$emit("upload-result",result[0]);
              }
            }else{
              self.$emit("uploadResult",null);
            }
            self.spinShow = false;
          })
        });

      },
      cancel(){
        if(this.viewImg && this.viewImg !=""){
          this.addFile = this.viewImg;
        }else{
          if(this.defaultImg && this.defaultImg != ""){
            if(this.defaultImg.indexOf("http://") >=0 || this.defaultImg.indexOf("/") >=0 ){
              this.addFile = this.defaultImg;
            }else{
              this.addFile = config.fileServerContext + "/file/get/"+ this.defaultImg;
            }
          }else{
            this.addFile = add;
          }
        }
        //保证每次点击选择文件可触发onchang事件
        this.$refs.uploadInput.value = '';
      },
      initView(){
        if(this.defaultImg && this.defaultImg != ""){
          if(this.defaultImg.indexOf("http://") >=0 || this.defaultImg.indexOf("/") >=0 ){
            this.addFile = this.defaultImg;
          }else{
            this.addFile = config.fileServerContext + "/file/get/"+ this.defaultImg;
          }
        }
      }
    },
    mounted(){
      this.initView();
    },
    watch:{
      defaultImg:function(){
        this.initView();
      }
    }
  }
</script>

<style scoped lang="less">
  .cropper-content{
    display: flex;
    display: -webkit-flex;
    justify-content: flex-end;
    -webkit-justify-content: flex-end;
    .cropper{
      width: 350px;
      height: 300px;
    }
    .show-preview{
      flex: 1;
      -webkit-flex: 1;
      display: flex;
      display: -webkit-flex;
      justify-content: center;
      -webkit-justify-content: center;
      .preview{
        overflow: hidden;
        border:1px solid #cccccc;
        background: #cccccc;
        margin-left: 40px;
      }
    }
  }
  .msg{
    line-height: 2;
    color:#999999;
  }
  .footer-btn{
    margin-top: 30px;
    display: flex;
    display: -webkit-flex;
    justify-content: flex-end;
    -webkit-justify-content: flex-end;
    .scope-btn{
      width: 350px;
      display: flex;
      display: -webkit-flex;
      justify-content: space-between;
      -webkit-justify-content: space-between;
    }
    .upload-btn{
      flex: 1;
      -webkit-flex: 1;
      display: flex;
      display: -webkit-flex;
      justify-content: center;
      -webkit-justify-content: center;
    }
    .btn {
      outline: none;
      display: inline-block;
      line-height: 1;
      white-space: nowrap;
      cursor: pointer;
      -webkit-appearance: none;
      text-align: center;
      -webkit-box-sizing: border-box;
      box-sizing: border-box;
      outline: 0;
      margin: 0;
      -webkit-transition: .1s;
      transition: .1s;
      font-weight: 500;
      padding: 8px 15px;
      font-size: 12px;
      border-radius: 3px;
      color: #fff;
      background-color: #67c23a;
      border-color: #67c23a;
    }
  }
  .defaultImg{
    position:relative;

    .demo-spin-icon-load{
      animation: ani-demo-spin 1s linear infinite;
    }
    @keyframes ani-demo-spin {
      from { transform: rotate(0deg);}
      50%  { transform: rotate(180deg);}
      to   { transform: rotate(360deg);}
    }
    .demo-spin-col{
      height: 100px;
      position: relative;
      border: 1px solid #eee;
    }
  }
</style>
