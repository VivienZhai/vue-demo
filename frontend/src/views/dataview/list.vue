<template>
  <Table border :columns="columns" :data="items"></Table>
</template>

<script>
    import util from '@/libs/util'
    export default {
      name: "list",
      data() {
        return {
          columns: [{
            title: "编号",
            key: 'id'
          }, {
            title: '名称',
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
        loadData: function() {
          let self = this;
          util.ajax.get("/viz/service/manage/datasource/views")
            .then(function(resp) {
              self.items = resp.data.data;
            });
        },
        remove: function(id) {

        },
        edit: function(id) {

        }
      }
    }
</script>

<style scoped>

</style>
