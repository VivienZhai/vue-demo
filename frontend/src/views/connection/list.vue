<template>
  <div>
    <Row style="margin-bottom: 10px;">
      <Col span="12">
        <Input style="width:300px" search enter-button placeholder="请输入名称" />
      </Col>
      <Col span="12" style="text-align: right">
        <Button type="primary" to="connections/add">新增</Button>
        <Button type="error">删除</Button>
      </Col>
    </Row>
    <Table border :columns="columns" :data="items"></Table>
  </div>

</template>

<script>
    import util from '@/libs/util';
    export default {
      name: "list",
      data() {
          return {
            columns: [{
              type: "selection",
              width: 60,
              align: "center"
            },{
              title: '名称',
              key: "name",
              render: (h, params)=> {
                return h("div", [h('a',{
                  on: {
                    click:() => {
                      this.show(params.row.id);
                    }
                  }
                }, params.row.name)])
              }
            },{
              title: '所属用户',
              key: "ownerId"
            }, {
              title: "创建时间",
              key: "createdAt"
            }, {
              title: "更新时间",
              key: "updatedAt"
            }, {
              title: "操作",
              key: 'action',
              width: 150,
              align: "center",
              render: (h,params) => {
                return h('div', [
                  h('Button', {
                    props: {
                      type: "primary",
                      size: "small"
                    },
                    style: {
                      marginRight: '5px'
                    },
                    on: {
                      click: () => {
                        this.edit(params.row.id);
                      }
                    }
                  }, '编辑'),
                  h('Button', {
                    props: {
                      type: "error",
                      size: "small"
                    },
                    on: {
                      click: () => {
                        this.remove(params.index)
                      }
                    }
                  }, 'Delete')
                ]);
              }
            }],
            items: [{
              id: "test",
              name: "测试",
              ownerId: "superadmin",
              createdAt: "2018-08-22 20:56:46",
              updatedAt: "2018-08-22 20:57:02"
            }]
          }
        },
      methods: {
        show(index) {
          this.$Modal.info({
            title: 'Connection info',
            content: ""
          })
        },
        edit(id) {
          this.$router.push({path: '/manage/connections/edit/' + id})
        },
        remove(index) {
          this.items.splice(index, 1)
        },
        loadData() {
          let self = this;
          util.ajax.get("viz/service/manage/datasource/connections")
            .then(function(resp) {
              self.items = resp.data.data;
            })
        }
      },
      created() {
        this.loadData();
      }
    }
</script>

<style scoped>

</style>
