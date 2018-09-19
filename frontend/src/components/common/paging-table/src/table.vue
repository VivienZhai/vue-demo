<template>
  <div class="lambo-grid-table">
    <exportData ref="exportPart" style="display: none"
                :tableColumns="tableColumns"
                :dataUrl="dataUrl"
                :searchParams="searchParams"
                :sortParams="sortParams"
                :paginationParams="paginationParams"
                :exportName="exportName">
    </exportData>
    <Row>
      <Col :span="showTableOption?19:24">
      <slot name="search">

      </slot>
      </Col>
      <Col :span="showTableOption?5:0">
      <div v-if="showTableOption">
        <Dropdown class="table-option-dropdown" trigger="custom" :visible="optionDropdownVisible" placement="bottom-end"
                  style="float:right">
          <ButtonGroup>
            <Button type="primary" ghost icon="md-refresh" title="刷新表格数据" @click="tableRefresh"></Button>
            <Button type="primary" ghost icon="ios-keypad" title="表格选项" @click="tableSettingToggle">
              <Icon type="ios-arrow-down"></Icon>
            </Button>
          </ButtonGroup>
          <DropdownMenu slot="list" style="width:400px;padding:5px 10px;">
            <Tabs type="card">
              <TabPane label="导出">
                <exportData
                  :tableColumns="tableColumns"
                  :dataUrl="dataUrl"
                  :searchParams="searchParams"
                  :sortParams="sortParams"
                  :paginationParams="paginationParams">
                </exportData>
              </TabPane>
              <TabPane label="列筛选">
                <div style="border-bottom: 1px solid #e9e9e9;padding-bottom:6px;margin-bottom:6px;">
                  <Checkbox :value="checkAll" @on-change="handleCheckAll">全选</Checkbox>
                </div>
                <CheckboxGroup v-model="checkAllGroup" @on-change="checkAllGroupChange">
                  <Checkbox v-for="(item, index) in columns" :key="item.key" :label="item.title"></Checkbox>
                  <br/><br/>
                  <Button type="primary" long @click="columnSelect">确定</Button>
                </CheckboxGroup>

              </TabPane>
              <TabPane label="样式">
                显示边框
                <i-switch v-model="tableBorder" style="margin-right: 5px"></i-switch>
                显示斑马纹
                <i-switch v-model="tableStripe" style="margin-right: 5px"></i-switch>
                显示表头
                <i-switch v-model="tableShowHeader" style="margin-right: 5px"></i-switch>
                <br><br> 表格尺寸
                <Radio-group v-model="tableSize" type="button">
                  <Radio label="large">大</Radio>
                  <Radio label="default">中(默认)</Radio>
                  <Radio label="small">小</Radio>
                </Radio-group>
                <br><br>
                <Button type="default" long @click="setTableStyleGlobal">应用为全局样式</Button>
              </TabPane>
            </Tabs>
            <Button type="error" icon="md-close" size="small" title="关闭" style="position:absolute;top:10px;right:10px;"
                    @click="closeTableSettingDropdown"></Button>
          </DropdownMenu>
        </Dropdown>
      </div>
      </Col>
    </Row>
    <Row style="margin-top:15px;">
      <Col span="24">
      <Table
        ref="tableRef"
        :columns="tableColumns"
        :data="tableData"
        :stripe="tableStripe"
        :border="tableBorder"
        :showHeader="tableShowHeader"
        :width="tableWidth"
        :height="tableHeight"
        :loading="tableLoading"
        :disabledHover="disabledHover"
        :highlightRow="highlightRow"
        :rowClassName="rowClassName"
        :size="tableSize"
        :noDataText="noDataText"
        :noFilteredDataText="noFilteredDataText"
        @on-sort-change="onSortChange"
        @on-current-change="onCurrentChange"
        @on-select="onSelect"
        @on-select-cancel="onSelectCancel"
        @on-select-all="onSelectAll"
        @on-selection-change="onSelectionChange"
        @on-filter-change="onFilterChange"
        @on-row-click="onRowClick"
        @on-row-dblclick="onRowDblclick"
        @on-expand="onExpand">
        <div slot="header" v-if="slots.header">
          <slot name="header"></slot>
        </div>
        <div slot="footer" v-if="slots.footer">
          <slot name="footer"></slot>
        </div>
        <div slot="loading" v-if="slots.loading">
          <slot name="loading"></slot>
        </div>
      </Table>
      </Col>
    </Row>
    <Row style="margin-top:15px;">
      <Col span="24" style="text-align:right">
      <Page
        :total="totalNumber"
        :current="currentPage"
        show-sizer
        show-total
        show-elevator
        @on-change="onPageChange"
        @on-page-size-change="onPageSizeChange">
      </Page>
      </Col>
    </Row>
  </div>
</template>
<script>
  import util from '@/libs/util.js';
  import exportData from "./components/export-data.vue";

  export default {
    props: {
      //取数的url
      dataUrl: {
        type: String
      },
      //查询条件
      searchParams: {
        type: Object
      },
      data: {
        type: Array,
        default() {
          return [];
        }
      },
      columns: {
        type: Array,
        default() {
          return [];
        }
      },
      size: {
        type: String,
        validator(value) {
          return ['small', 'large', 'default'].indexOf(value) > -1;
        }
      },
      width: {
        type: [Number, String]
      },
      height: {
        type: [Number, String]
      },
      stripe: {
        type: Boolean,
        default: true
      },
      border: {
        type: Boolean,
        default: true
      },
      showHeader: {
        type: Boolean,
        default: true
      },
      highlightRow: {
        type: Boolean,
        default: false
      },
      rowClassName: {
        type: Function,
        default() {
          return '';
        }
      },
      context: {
        type: Object
      },
      noDataText: {
        type: String
      },
      noFilteredDataText: {
        type: String
      },
      disabledHover: {
        type: Boolean
      },
      loading: {
        type: Boolean,
        default: false
      },
      showTableOption: {
        type: Boolean,
        default: false
      },
      exportName: String,
      transformRequest: {
        type: Function,
        default: function (data) {
          return data;
        }
      },
      transformResponse: {
        type: Function,
        default: function (data) {
          return data;
        }
      }
    },
    data() {
      return {
        tableData: this.data,
        tableColumns: this.columns,
        tableWidth: this.width,
        tableHeight: this.height,
        tableLoading: this.loading,

        //样式相关
        tableStripe: "tableStripe" in localStorage ? localStorage.getItem("tableStripe") !== "false" : this.stripe,
        tableBorder: "tableBorder" in localStorage ? localStorage.getItem("tableBorder") !== "false" : this.border,
        tableShowHeader: "tableShowHeader" in localStorage ? localStorage.getItem("tableShowHeader") !== "false" : this.showHeader,
        tableSize: "tableSize" in localStorage ? localStorage.getItem("tableSize") : this.size,


        //分页相关参数
        totalNumber: 0,
        currentPage: 1,
        limitNumber: 10,
        //表格选项
        optionDropdownVisible: false,

        //列筛选
        checkAll: true,
        checkAllGroup: [],
        //导出格式
        dataExportFormat: "xlsx",
        //排序
        sortParams: {}
      }
    },
    computed: {
      columnNameArr() {
        var arr = [];
        for (var column of this.columns) {
          arr.push(column.title);
        }
        return arr;
      },
      paginationParams() {
        var self = this;
        return {
          offset: (self.currentPage - 1) * self.limitNumber,
          limit: self.limitNumber
        }
      },
      slots() {
        return this.$slots;
      }
    },
    watch: {
      searchParams: {
        handler: function (val, oldVal) {
          this.tableRefresh();
        },
        deep: true
      }
    },
    methods: {
      tableRefresh: function () {
        var self = this;
        self.tableLoading = true;
        util.ajax.get(self.dataUrl, {
          params: Object.assign({}, self.paginationParams, self.searchParams, self.sortParams),
          transformRequest: [self.transformRequest],
          transformResponse: [self.transformResponse],
          responseType: 'json'
        }).then(function (resp) {
          if (resp.data) {
            if (resp.data.code === 1 || resp.data.code === "000") {
              self.tableData = resp.data.data.rows;
              self.totalNumber = resp.data.data.total;
            } else if (resp.data.code === 0) {
              alert("获取数据异常，请稍后重试");
            } else {
              self.tableData = resp.data.rows;
              self.totalNumber = resp.data.total;
            }
          }
          self.tableLoading = false;
        });
      },
      getParams: function () {
        return Object.assign({}, this.paginationParams, this.searchParams, this.sortParams)
      },
      exportOper: function (type) {
        this.$refs.exportPart.exportExcel(type);
      },
      onPageChange: function (currentPageIndex) {
        this.currentPage = currentPageIndex;
        this.tableRefresh();
        this.$emit("on-page-change", currentPageIndex)
      },
      onPageSizeChange: function (currentLimitNumber) {
        this.limitNumber = currentLimitNumber;
        this.tableRefresh();
        this.$emit("on-page-size-change", currentLimitNumber)
      },
      handleCheckAll() {
        this.checkAll = !this.checkAll;
        if (this.checkAll) {
          this.checkAllGroup = this.columnNameArr;
        } else {
          this.checkAllGroup = [];
        }
      },
      checkAllGroupChange(data) {
        if (data.length === this.columnNameArr.length) {
          this.checkAll = true;
        } else if (data.length > 0) {
          this.checkAll = false;
        } else {
          this.checkAll = false;
        }
      },
      tableSettingToggle() {
        this.optionDropdownVisible = !this.optionDropdownVisible;
      },
      closeTableSettingDropdown() {
        this.optionDropdownVisible = false;
      },
      onSortChange(sortParams) {
        this.sortParams = {
          sort: sortParams.column.sortField || sortParams.key,
          order: sortParams.order == "normal" ? "" : sortParams.order
        };
        this.tableRefresh();
      },
      onCurrentChange(currentRow, oldCurrentRow) {
        this.$emit("on-current-change", currentRow, oldCurrentRow);
      },
      onSelect(selection, row) {
        this.$emit("on-select", selection, row);
      },
      onSelectCancel(selection, row) {
        this.$emit("on-select-cancel", selection, row);
      },
      onSelectAll(selection) {
        this.$emit("on-select-all", selection);
      },
      onSelectionChange(selection) {
        this.$emit("on-selection-change", selection);
      },
      onFilterChange(column, key, order) {
        this.$emit("on-filter-change", column, key, order);
      },
      onRowClick(row, index) {
        this.$emit("on-row-click", row, index);
      },
      onRowDblclick(row, index) {
        this.$emit("on-row-dblclick", row, index);
      },
      onExpand(row, status) {
        this.$emit("on-expand", row, status);
      },
      columnSelect: function () {
        let self = this;
        var columnSelected = self.checkAllGroup;
        var newColumns = [];
        columnSelected.forEach(colName => {
          for (let column of self.columns) {
            if (column.title === colName) {
              newColumns.push(column);
              break;
            }
          }
        });
        this.tableColumns = newColumns;
      },
      setTableStyleGlobal: function () {
        window.localStorage.setItem("tableBorder", this.tableBorder);
        window.localStorage.setItem("tableStripe", this.tableStripe);
        window.localStorage.setItem("tableShowHeader", this.tableShowHeader);
        window.localStorage.setItem("tableSize", this.tableSize);
        this.$Message.success('应用全局样式成功');
      },
      getTableInstance: function () {
        return this.$refs.tableRef;
      }
    },
    mounted() {
      this.tableRefresh();
      this.checkAllGroup = this.columnNameArr;
    },
    components: {
      exportData
    }
  }
</script>
<style lang="less">
  .lambo-grid-table {
    .ivu-select-dropdown {

    }
  }
</style>
