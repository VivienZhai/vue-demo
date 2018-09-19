<template>
  <div class="model-header">
    <slot></slot>
  </div>
</template>

<script>
    export default {
        name: "model-title"
    }
</script>

<style scoped lang="less">
  .model-header{
    background: #F2F2F2;
    line-height: 24px;
    padding: 10px 15px;
    font-size: 14px;
    font-weight: bolder;
    overflow: hidden;
    margin: 15px;
    color: #989898;
  }
</style>
