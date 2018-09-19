<template>
  <Table border ref="widgetList" :columns="columns" :data="items"></Table>
</template>

<script>
    import util from '@/libs/util'

    export default {
      name: "list",
      data() {
        return {
          columns: [{
            type: 'selection',
            width: 60,
            align: 'center'
          }, {
            title: "名称",
            key: "name"
          }, {
            title: "创建时间",
            key: "createdAt"
          }, {
            title: "更新时间",
            key: "updatedAt"
          }],
          items: []
        }
      },
      created() {
        this.loadData();
      },
      methods: {
        loadData() {
          let self = this;
          util.ajax.get("/viz/service/manage/widgets")
            .then(function(resp) {
              self.items = resp.data;
            });
        },
        handleClearRow() {

        }
      }
    }
</script>

<style scoped>

</style>
