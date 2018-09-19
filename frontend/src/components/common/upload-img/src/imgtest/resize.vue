<template>
<div class="wrapper-content">
  <div class="wrapper">
    <vueCropper
      ref="cropper"
      :img="option.img"
      :outputSize="option.size"
      :outputType="option.outputType"
      :info="true"
      :full="option.full"
      :canMove="option.canMove"
      :canMoveBox="option.canMoveBox"
      :fixedBox="option.fixedBox"
      :original="option.original"
      @realTime="realTime"
    ></vueCropper>
  </div>
  <div class="test-button">
    <button @click="select()" class="btn">选择图片</button>
    <input type="file" ref="uploads" style="position:absolute; clip:rect(0 0 0 0);" accept="image/png, image/jpeg, image/gif, image/jpg" @change="uploadImg($event, 1)">
    <button @click="finish('blob')" class="btn">预览图片</button>
    <button @click="down('blob')" class="btn">下载图片</button>
    <button @click="confirmImg()" class="btn">上传图片</button>
  </div>
  <div class="show-preview" :style="{'width': previews.w + 'px', 'height': previews.h + 'px',  'overflow': 'hidden', 'margin': '5px'}">
    <div :style="previews.div">
      <img :src="previews.url" :style="previews.img">
    </div>
  </div>
</div>
</template>
<script>
  import vueCropper from 'vue-cropper';
  import axios from 'axios';

  export default {
    data: function () {
      return {
        crap: false,
        previews: {},
        option: {
          img: '',
          size: 1,
          full: false,
          outputType: 'png',
          canMove: true,
          fixedBox: false,
          original: false,
          canMoveBox: false
        },
        downImg: '#'
      }
    },
    methods: {
      select(){
        this.$refs.uploads.click();
      },
      uploadImg (e, num) {
        //上传图片
        // this.option.img
        var file = e.target.files[0]
        if (!/\.(gif|jpg|jpeg|png|bmp|GIF|JPG|PNG)$/.test(e.target.value)) {
          alert('图片类型必须是.gif,jpeg,jpg,png,bmp中的一种')
          return false
        }
        var reader = new FileReader()
        reader.onload = (e) => {
          let data
          if (typeof e.target.result === 'object') {
            // 把Array Buffer转化为blob 如果是base64不需要
            data = window.URL.createObjectURL(new Blob([e.target.result]))
          } else {
            data = e.target.result
          }
          if (num === 1) {
            this.option.img = data
          } else if (num === 2) {
            this.example2.img = data
          }
        }
        // 转化为blob
        reader.readAsArrayBuffer(file)
      },
      // 实时预览函数
      realTime (data) {
        this.previews = data
      },
      finish (type) {
        // 输出
        var test = window.open('about:blank')
        test.document.body.innerHTML = '图片生成中..'
        if (type === 'blob') {
          this.$refs.cropper.getCropBlob((data) => {
            test.location.href = window.URL.createObjectURL(data)
          })
        } else {
          this.$refs.cropper.getCropData((data) => {
            test.location.href = data
          })
        }
      },
      down (type) {
        // event.preventDefault()
        var aLink = document.createElement('a')
        aLink.download = 'demo'
        // 输出
        if (type === 'blob') {
          this.$refs.cropper.getCropBlob((data) => {
            console.log("data");
            aLink.href = window.URL.createObjectURL(data);
            aLink.click()
          })
        } else {
          this.$refs.cropper.getCropData((data) => {
            this.downImg = data
            aLink.href = data
            aLink.click()
          })
        }
      },
      confirmImg(){
        this.$refs.cropper.getCropBlob((data) => {
          console.log(data);
          var afile = new File([data], "1.png");
          let formData = new FormData();
          formData.append('file', afile);
          axios.post("/file-server/file/put" , formData).then(response => {
            console.log(response.data);
          })
        })
      }
    },
    components: {
      vueCropper
    },
  }
</script>


<style scoped lang="less">
  .wrapper-content{
    .wrapper{
      width: 300px;
      height: 400px;
    }
  }
</style>
