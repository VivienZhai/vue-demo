<template>
  <div class="content-info">
    <div class="content-header" v-if="hasTitle || hasExtend">
      <div class="content-title" v-if="hasTitle">
        <slot name="content-title"></slot>
      </div>
      <div class="content-extend" v-if="hasExtend">
        <slot name="content-extend"></slot>
      </div>
    </div>
    <div class="content-search" v-if="hasSearch">
      <slot name="content-search"></slot>
    </div>
    <div class="content-body">
      <slot></slot>
    </div>
  </div>
</template>

<script>
    export default {
        name: "main-content",
        data(){
          return {

          }
        },
        props:{
          hasTitle:{
            type:Boolean,
            required: false,
            default:true
          },
          hasExtend:{
            type:Boolean,
            required: false,
            default:true
          },
          hasSearch:{
            type:Boolean,
            required: false,
            default:false
          }
        }
    }
</script>

<style scoped lang="less">
  .content-info{
    background: #F2F2F2;
    .content-header{
      background: #ffffff;
      overflow: hidden;
      padding: 15px;
      .content-title{
        color: #000000;
        font-weight: bold;
        float:left;
        font-size: 16px;
        line-height: 24px;
        height: 24px;
      }
      .content-extend{
        float: right;
        font-size: 14px;
        line-height: 20px;
      }
    }
    .content-search{
      background: #ffffff;
      margin-top: 10px;
      padding: 15px 15px 0px 15px;
    }
    .content-body{
      background: #ffffff;
      margin-top: 10px;
      padding: 0px 15px 15px 15px;
    }
  }
</style>
