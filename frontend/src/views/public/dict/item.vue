<template>
  <div class="table">
    <Row>
      <Col :span="6">
        <Breadcrumb style="padding: 5px;">
          <BreadcrumbItem v-for="(item, index) in crumbs" :key="index" :to="item.path">{{item.name}}</BreadcrumbItem>
        </Breadcrumb>
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
      <Col :span="24" style="text-align: right;">
        <Page :total="total" :current="params.pageNum" show-total @on-change="handlePaging"/>
      </Col>
    </Row>
    <Modal v-model="dialog.show" :title="dialog.title" @on-cancel="handleReset" @on-ok="handleSubmit">
      <Form ref="itemForm" :model="itemForm" :rules="ruleValidate" :label-width="60">
        <FormItem label="编码" prop="code">
          <Input v-model="itemForm.code" :disabled="dialog.edit" placeholder="请输入字典项的编码"></Input>
        </FormItem>
        <FormItem label="值" prop="value">
          <Input v-model="itemForm.value" placeholder="请输入字典项的值"></Input>
        </FormItem>
        <FormItem label="开启">
          <i-switch v-model="isEnabled">
            <span slot="open">开</span>
            <span slot="close">关</span>
          </i-switch>
        </FormItem>
        <FormItem label="描述">
          <Input v-model="itemForm.note" type="textarea" :rows="4" placeholder="请输入描述信息"></Input>
        </FormItem>
      </Form>
    </Modal>
  </div>
</template>

<script>
    import util from '@/libs/util';
    export default {
      name: "item",

      data() {
        return {
          loading: true,
          selections: [],
          crumbs: [{
            path: "/dict/list",
            name: "字典列表"
          }],
          columns: [{
            type: "selection",
            width: 60,
            align: "center"
          }, {
            type: "index",
            width: 60,
            align: "center"
          }, {
            title: "编码",
            key: "code",
            render: (h, params) => {
              return h('a', {
                on: {
                  click: () => {
                    this.handleSubItem(params.row.code, params.row.value);
                  }
                }
              }, params.row.code)
            }
          }, {
            title: "值",
            key: "value"
          }, {
            title: "描述",
            key: "note"
          }, {
            title: "操作",
            align: "center",
            width: 180,
            render: (h, params) => {
              return h('div', [
                h('Button', {
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
              ])
            }
          }],
          data: [],
          total: 0,
          params: {
            pageSize: 10,
            pageNum: 1
          },
          dialog: {
            show: false,
            title: "",
            edit: false
          },
          itemForm: {
            code: "",
            value: "",
            dictCode: "",
            inUse: "1",
            note: "",
            sequence: 0
          },
          ruleValidate: {
            code: [
              {required: true, message: "编码不能为空", trigger: "blur"},
              {
                validator: (rule, value, callback) => {
                  util.ajax.post("/viz/dict/" + this.itemForm.dictCode + "/items/" + this.itemForm.code)
                    .then(function(resp) {
                      if(resp.data.code == 200) {
                        if(resp.data.data == true) {
                          callback();
                        } else {
                          callback(new Error(value + "已经存在"));
                        }
                      } else {
                        callback(new Error("校验出错"));
                      }
                    });
                },
                trigger: "blur"
              }
            ],
            value: [
              {required: true, message: "值不能为空", trigger: "blur"}
            ]
          }
        }
      },
      created() {
        let self = this;
        this.crumbs.push({
          path: "",
          name: self.$route.query.name
        });
        this.itemForm.dictCode = this.$route.query.code;
        this.loadData();
      },
      computed: {
        isEnabled: {
          get: function() {
            return this.itemForm.inUse == '1';
          },
          set: function(newVal) {
            this.itemForm.inUse = newVal ? '1' : '0';
          }
        }
      },
      methods: {
        loadData: function() {
          let self = this;
          self.loading = true;
          util.ajax.get("/viz/dict/" + self.itemForm.dictCode + "/items", {
            params: self.params
          }).then(function(resp) {
              if(resp.data.code == 200) {
                self.data = resp.data.data;
                self.total = resp.data.total;
              } else {
                self.$Message.error("加载数据出错");
              }
            }).finally(function() {
              self.loading = false;
          })
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
            content: "确认删除?"
          });
        },
        handleAdd: function() {
          let self = this;
          self.dialog.show = true;
          self.dialog.title = "新增";
          self.dialog.edit = false;
        },
        handleEdit: function(item) {
          let self = this;
          self.dialog.show = true;
          self.dialog.title = "编辑";
          self.dialog.edit = true;

          Object.keys(self.itemForm).forEach((key) => {
            self.itemForm[key] = item[key]
          });
        },
        handleReset: function() {
          this.$refs['itemForm'].resetFields();
        },
        handlePaging: function(pageNum) {
          let self = this;
          self.params.pageNum = pageNum;
          self.loadData();
        },
        handleSubItem: function(parentCode, parentValue) {
          let self = this;
          this.params.parentCode = parentCode;
          this.crumbs[self.crumbs.length - 1].path = this.$router.currentRoute.path;
          this.crumbs.push({
            path: "",
            name: parentValue
          });
          this.loadData();
        },
        handleSubmit: function() {
          let self = this;
          if(self.dialog.edit) {
            util.ajax.put("/viz/dict/" + self.itemForm.dictCode + "/items/" + self.itemForm.code, self.itemForm, {
              headers: {
                "Content-Type": "application/json"
              }
            }).then(function(resp) {
              if(resp.data.code == 200) {
                self.$Message.success("编辑成功");
                self.loadData();
              } else {
                self.$Message.error("保存失败");
              }
            }).finally(function() {
              self.$refs['itemForm'].resetFields();
            });
          } else {
            util.ajax.post("/viz/dict/" + self.itemForm.dictCode + "/items", self.itemForm, {
              headers: {
                "Content-Type": "application/json"
              }
            }).then(function(resp) {
              if(resp.data.code == 200) {
                self.$Message.success("保存成功");
                self.loadData();
              } else {
                self.$Message.error("保存失败");
              }
            }).finally(function() {
              self.$refs['itemForm'].resetFields();
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
                util.ajax.delete("/viz/dict/" + self.itemForm.dictCode + "/items", {
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
