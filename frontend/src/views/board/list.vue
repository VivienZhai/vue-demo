<template>
    <Table border :columns="columns" :data="items"></Table>
</template>

<script>
    import util from '@/libs/util'

    export default {
      name: "list.vue",
      data() {
        return {
          columns: [{
            title: "编号",
            key: "id"
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
          util.ajax.get('/viz/service/manage/viz/dashboards')
            .then(function(resp) {
              self.items = resp.data;
            });
        }
      }
    }
</script>

<style scoped>

</style>
