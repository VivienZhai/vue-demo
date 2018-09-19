<template>
  <div class="table">
    <Row>
      <Col :span="6">
        <Input v-model="params.name" search enter-button placeholder="请输入搜索内容" style="width: 300px" @on-search="handleSearch"/>
      </Col>
      <Col :span="6" :offset="12" style="text-align: right">
        <Button type="primary" @click="handleAdd">新增</Button>
        <Button type="error" @click="handleBatchRemove">删除</Button>
      </Col>
    </Row>
    <Row>
      <Col :span="24">
        <Table border ref="tableList" :columns="columns" :data="data" :loading="loading" @on-select="handleSelect"></Table>
      </Col>
    </Row>
    <Row>
      <Col :span="24" style="text-align: right">
        <Page :total="total" :current="params.pageNum" show-total @on-change="handlePaging"/>
      </Col>
    </Row>
    <Modal v-model="dialog.show" :title="dialog.title" @on-cancel="handleReset" @on-ok="handleSubmit" >
      <Form ref="form" :model="dictForm" :rules="ruleValidate" :label-width="60">
        <FormItem label="编码" prop="code">
          <Input v-model="dictForm.code" :disabled="dialog.edit"></Input>
        </FormItem>
        <FormItem label="名称" prop="name">
          <Input v-model="dictForm.name"></Input>
        </FormItem>
        <FormItem label="开启">
          <i-switch v-model="isEnabled">
            <span slot="open">开</span>
            <span slot="close">关</span>
          </i-switch>
        </FormItem>
        <FormItem label="描述">
          <Input v-model="dictForm.note" type="textarea" :rows="4" placeholder="请输入描述信息"></Input>
        </FormItem>
      </Form>
    </Modal>
  </div>
</template>

<script>
    import util from '@/libs/util';
    export default {
        name: "list",

        data() {
          return {
            loading: true,
            selections: [],
            columns: [{
              type: "selection",
              width: 60,
              align: "center"
            }, {
              title: "字典编号",
              key: "code",
              render: (h, params) => {
                return h('a', {
                  on: {
                    click: () => {
                      this.handleViewItems(params.row);
                    }
                  }
                }, params.row.code)
              }
            }, {
              title: "字典名称",
              key: "name"
            }, {
              title: "字典描述",
              key: "note"
            }, {
              title: "最近修改人",
              key: "setMan"
            }, {
              title: "最近修改时间",
              key: "setTime"
            }, {
              title: "操作",
              align: "center",
              width: 180,
              render: (h, params) => {
                return h('div', [
                  h("Button", {
                    props: {
                      type: "text",
                      size: "small"
                    },
                    on: {
                      click: () => {
                        this.handleEdit(params.row);
                      }
                    }
                  }, "编辑"),
                  h("Button", {
                    props: {
                      type: "text",
                      size: "small"
                    },
                    on: {
                      click: () => {
                        this.handleRemove(params.row.code);
                      }
                    }
                  }, "删除")
                ]);
              }
            }],
            data: [],
            total: 0,
            params: {
              pageSize: 10,
              pageNum: 1,
              name: ""
            },
            dialog: {
              show: false,
              title: "",
              edit: false
            },
            dictForm: {
              code: "",
              name: "",
              inUse: "1",
              note: ""
            },
            ruleValidate: {
              code: [{
                required: true,
                message: "编码不能为空",
                trigger: "blur"
              }, {
                validator: (rule, value, callback) => {
                  util.ajax.post('/viz/dict/' + value)
                    .then(function(resp) {
                      if(resp.data.code == 200) {
                        if(resp.data.data == true) {
                          callback();
                        } else {
                          callback(new Error(value + '已经存在'));
                        }
                      } else {
                        callback(new Error('校验出错'));
                      }
                    });
                },
                trigger: 'blur'
              }],
              name: [{
                required: true,
                message: "名称不能为空",
                trigger: "blur"
              }]
            }
          }
        },
      created() {
        this.loadData();
      },
      computed: {
        isEnabled: {
          get: function() {
            this.dictForm.inUse == '0';
          },
          set: function(newValue) {
            this.dictForm.inUse = newValue ? '1' : '0';
          }
        }
      },
      methods: {
        loadData: function() {
          let self = this;
          self.loading = true;
          util.ajax.get("/viz/dict", {
            params: self.params
          }).then(function(resp) {
            self.data = resp.data.data;
            self.total = resp.data.total;
          }).finally(function() {
            self.loading = false;
          });
        },
        handlePaging: function(pageNum) {
          let self = this;
          self.params.pageNum = pageNum;
          self.loadData();
        },
        handleSearch: function() {
          let self = this;
          self.params.pageNum = 1;
          self.loadData();
        },
        handleRemove: function(code) {
          let self = this;
          self.$Modal.confirm({
            title: "提示信息",
            content: '确定删除?',
            onOk: () => {
              util.ajax.delete("/viz/dict/" + code)
                .then(function(resp) {
                  if(resp.data.code == 200) {
                    self.$Message.success('删除成功');
                    self.params.pageNum = 1;
                    self.loadData();
                  } else {
                    self.$Message.error("删除失败");
                  }
                })
            }
          });
        },
        handleAdd: function() {
          let self = this;
          self.dialog.show = true;
          self.dialog.edit = false;
          self.dictForm = {
            code: "",
            name: "",
            inUse: "",
            note: ""
          };
          self.dialog.title = "新增";
        },
        handleEdit: function(data) {
          let self = this;
          self.dialog.show = true;
          Object.keys(self.dictForm).forEach((key) => {
            self.dictForm[key] = data[key];
          });
          self.dialog.edit = true;
          self.dialog.title = "编辑";
        },
        handleViewItems: function(dict) {
          this.$router.push({path: '/dict/items', query: {code: dict.code, name: dict.name}})
        },
        handleReset: function() {
          this.$refs["form"].resetFields();
        },
        handleSubmit: function() {
          let self = this;
          if(self.dialog.edit) {
            util.ajax.put("/viz/dict/" + self.dictForm.code, self.dictForm, {
              headers: {
                'Content-Type': 'application/json'
              }
            }).then(function(resp) {
              if(resp.data.code == 200) {
                self.$Message.success("编辑成功");
                self.loadData();
              } else {
                self.$Message.error("保存失败");
              }
            });
          } else {
            util.ajax.post("/viz/dict", self.dictForm, {
              headers: {
                'Content-Type': 'application/json'
              }
            }).then(function(resp) {
                if(resp.data.code == 200) {
                  self.$Message.success("保存成功");
                  self.loadData();
                } else {
                  self.$Message.error("保存失败");
                }
              });
          }
        },
        handleSelect: function(selection) {
          let self = this;
          self.selections.length = 0;
          selection.forEach((item) => {
            self.selections.push(item.code);
          });
        },
        handleBatchRemove: function() {
          let self = this;
          if(self.selections.length == 0) {
            self.$Message.warning("请至少选择一条记录");
          } else {
            self.$Modal.confirm({
              title: "提示信息",
              content: "确认删除?",
              onOk: ()=> {
                util.ajax.delete("/viz/dict", {
                  params: {
                    keys: self.selections.join(",")
                  }
                }).then(function(resp) {
                  if(resp.data.code == 200) {
                    if(resp.data.data == true) {
                      self.$Message.success("删除成功");
                      self.loadData();
                    } else {
                      self.$Message.error("删除失败");
                    }
                  } else {
                    self.$Message.error("删除失败");
                  }
                });
              }
            })
          }
        }
      }
    }
</script>

<style lang="less" scoped>
.table {
  .ivu-row {
    margin-top: 7px;
  }
}
</style>
