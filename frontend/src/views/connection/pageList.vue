<template>
  <FlexGrid :dataUrl="ajaxUrl" :columns="columns" :searchParams="params">
    <Form :label-width="120" :model="searchForm" slot="search">
      <Row>
        <Col span="12">
          <Input v-model="searchForm.fuzzyName" style="width:300px" search enter-button placeholder="请输入名称" />
        </Col>
        <Col span="12" style="text-align: right;">
          <Button type="primary">新增</Button>
          <Button type="error">删除</Button>
        </Col>
      </Row>
    </Form>
  </FlexGrid>
</template>

<script>
  import FlexGrid from "@/components/common/paging-table/index";
  import util from '@/libs/util';

  export default {
    name: "pageList",
    components: {
      FlexGrid
    },
    data() {
      return {
        ajaxUrl: '/viz/service/manage/datasource/connections',
        params: {},
        columns: [
          {
            type: "selection",
            width: 60,
            align: "center"
          }, {
            title: "名称",
            key: "name"
          }
        ],
        searchForm: {
          fuzzyName: ""
        }
      }
    },
    methods: {
      goSearch() {

      }
    }
  }
</script>

<style scoped>

</style>
