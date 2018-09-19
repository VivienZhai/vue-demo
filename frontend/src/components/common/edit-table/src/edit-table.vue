<template>
    <div class="lambo-edit-table">
        <Table :columns="tableColumns"
               :data="tableDatas"
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
    </div>
</template>

<script>
    import EditorPlugin from "./editorPlugin.js";
    import {deepCopy} from '../../../../libs/assist';

    export default {
        name: 'canEditTable',
        props: {
            columns: {
                type:Array,
                default:[]
            },
            value: {
                type:Array,
                default:[]
            },
            size: {
                validator(value) {
                    return ['small', 'large', 'default'].indexOf(value) !== -1
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
            loading: {
                type: Boolean,
                default: false
            },
            noDataText: {
                type: String
            },
            noFilteredDataText: {
                type: String
            },
            disabledHover: {
                type: Boolean
            }
        },
        data() {
            return {
                tableColumns: this.columns,
                tableDatas: this.value,
                tableWidth: this.width,
                tableHeight: this.height,
                tableLoading: this.loading,

                //样式相关
                tableStripe: this.stripe,
                tableBorder: this.border,
                tableShowHeader: this.showHeader,
                tableSize: this.size,
            };
        },
        computed:{
            slots (){
                return this.$slots;
            }
        },
        created() {
            this.init();
        },
        methods: {
            init: function () {
                let self = this;
                let tableColumnsClone = deepCopy(this.columns);
                tableColumnsClone.forEach(column => {
                    if ("editor" in column) {
                        column.render = (h, param) => {
                            return h(EditorPlugin, {
                                props: {
                                    row:param.row,
                                    column:param.column,
                                    index:param.index
                                },
                                on:{
                                    "cell-on-change":function(param){
                                        self.cellOnChange(param);
                                    }
                                }
                            })
                        }
                    }
                })
                this.tableColumns = tableColumnsClone;
            },
            cellOnChange:function(param){
                let callbackEvent = param.callbackEvent, rowIndex = param.rowIndex, columnKey = param.columnKey, value = param.value;
                if(callbackEvent){
                    this.$emit(callbackEvent,value,rowIndex,columnKey);
                }else{
                    for(let i=0,len=this.tableDatas.length;i<len;i++){
                        if(i === rowIndex){
                            let item = this.tableDatas[i];
                            item[columnKey] = value;
                            this.$emit("input",this.tableDatas);
                        }
                    }
                }

            },
            getTableData:function(){
                return this.tableDatas;
            },
            onSortChange(sortParams) {
                this.$emit("on-current-change", sortParams);
            },
            onCurrentChange(currentRow,oldCurrentRow) {
                this.$emit("on-current-change", currentRow,oldCurrentRow);
            },
            onSelect(selection,row) {
                this.$emit("on-select", selection,row);
            },
            onSelectCancel(selection,row) {
                this.$emit("on-select-cancel", selection,row);
            },
            onSelectAll(selection) {
                this.$emit("on-select-all", selection);
            },
            onSelectionChange(selection) {
                this.$emit("on-selection-change", selection);
            },
            onFilterChange(column,key,order) {
                this.$emit("on-filter-change", column,key,order);
            },
            onRowClick(row,index) {
                this.$emit("on-row-click", row,index);
            },
            onRowDblclick(row,index) {
                this.$emit("on-row-dblclick", row,index);
            },
            onExpand(row,status) {
                this.$emit("on-expand", row,status);
            }
        },
        watch:{
            columns: {
                handler () {
                    this.init();
                },
                deep: true
            }
        }
    };
</script>

