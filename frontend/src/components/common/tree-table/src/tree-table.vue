<template>
    <div class="lambo-tree-table">
        <Table :data="tableData" :columns="tableColumns" :row-class-name="showRow" v-bind="$attrs"></Table>
    </div>
</template>

<script>
    import treeToArray from './components/eval'
    import TreeCtrl from './components/tree-ctrl'
    import {deepCopy} from '../../../libs/assist'

    export default {
        name: 'treeTable',
        props: {
            data: {
                type: [Array, Object],
                required: true
            },
            columns: {
                type: Array,
                default: () => [],
                required: true
            },
            evalFunc: Function,
            evalArgs: Array,
            expandAll: {
                type: Boolean,
                default: false
            }
        },
        data(){
            return {
                tableColumns:[],
                tableData:[]
            }
        },
        computed: {

        },
        methods: {
            formatColumns:function(){
                let self = this;
                let columns = this.columns;
                if(columns.length !== 0){
                    columns[0].render = (h,params) => {
                        return h(TreeCtrl,{
                            props:{
                                params
                            },
                            on:{
                                "on-toggle-expand":function(trIndex){
                                    self.toggleExpanded(trIndex);
                                }
                            }
                        })
                    }
                }
                this.tableColumns = this.columns;
            },
            formatData:function(){
                let tmp
                if (!Array.isArray(this.data)) {
                    tmp = [this.data]
                } else {
                    tmp = this.data;
                }
                const func = this.evalFunc || treeToArray;
                const args = this.evalArgs ? Array.concat([tmp, this.expandAll], this.evalArgs) : [tmp, this.expandAll];
                let formatData = func.apply(null, args);
                this.tableData = formatData;
            },
            showRow: function (row) {
                return (row._level === 1 || row._show === true) ? 'shown' : 'hidden'
            },
            // 切换下级是否展开
            toggleExpanded: function (trIndex) {
                let self = this, tableData = self.tableData;
                let record = tableData[trIndex];
                let level = record._level;
                let i = trIndex + 1;
                if(record._expand === true){
                    record._expand = false;
                    while(i < tableData.length){
                        if(tableData[i]._level >= level + 1){
                            tableData[i]._show = false;
                            tableData[i]._expand = false;
                            i++;
                        }else{
                            break;
                        }
                    }
                }else{
                    record._expand = true;
                    while(i < tableData.length){
                        if(tableData[i]._level === level + 1){
                            tableData[i]._show = true;
                            i++;
                        }else{
                            break;
                        }
                    }
                }

                self.tableData = JSON.parse(JSON.stringify(self.tableData));
            }

        },
        created(){
            this.formatColumns();
            this.formatData();
        },
        watch:{
            columns(){
                //this.formatColumns();
            }
        }
    }
</script>
<style lang="less">
    .lambo-tree-table{
        .hidden{
             display:none;
         }
        .shown{
            animation:treeTableShow;
            -webkit-animation:treeTableShow;
        }
        @keyframes treeTableShow {
            from {
                opacity: 0;
            }
            to {
                opacity: 1;
            }
        }

        @-webkit-keyframes treeTableShow {
            from {
                opacity: 0;
            }
            to {
                opacity: 1;
            }
        }
    }

</style>

<style lang="less" scoped>

</style>
