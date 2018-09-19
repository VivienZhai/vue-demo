<template>
    <Modal v-model="boxShow" :title="boxTitle"  @on-cancel="onCancel" :mask-closable="false" transfer >
        <div v-if="boxShow">
            <LamboPagingTable ref="helpBoxTable" :dataUrl="tableUrl" :columns="tableColumnsClone" @on-row-click="onRowClick" @on-selection-change="onSelectionChange" :searchParams="searchParams" size="small">
                <div slot="search">
                    <Input v-model="searchText" placeholder="搜索" style="width: 200px" />
                    <Button type="primary" icon="ios-search" @click="doSearch">查询</Button>
                </div>
            </LamboPagingTable>
        </div>
        <div slot="footer">
            <Button type="primary" ghost @click="onClear">清空</Button>
            <Button type="primary" @click="onOk">确定</Button>
        </div>
    </Modal>
</template>

<script>
    import LamboPagingTable from "@/components/common/paging-table/index"
    export default {
        name: "helpbox",
        components:{
          LamboPagingTable
        },
        props:{
            value:Boolean,
            url:String,
            columns:Array,
            title:{
                type:String,
                default:"帮助框"
            },
            muliSelect:{
                type:Boolean,
                default:false
            }

        },
        data(){
            return {
                boxShow:this.value,
                searchText:"",
                selection:[],
                searchParams:{},
                tableColumnsClone:[]
            }
        },
        computed:{
            tableUrl:function(){
                return this.url
            },
            boxTitle:function(){
                return this.title
            }
        },
        methods:{
            init:function(){
                let self = this;
                self.selectStore = [];
                let selectColumn = {
                        width: 80,
                        align: 'center',
                        title:"选择"
                    };
                if(this.muliSelect){
                    selectColumn.type = "selection"
                }else{
                    //当前iView版本单选要自己实现
                    selectColumn.render = function(h,params){
                        let index = params.index;
                        return h("Radio",{
                            props:{
                                "value":self.selection[index] === undefined ? false : self.selection[index]
                            },
                            on:{
                                "on-change":function(){
                                    self.selectOnChange(params.row,index);
                                }
                            }
                        })
                    }
                }
                this.tableColumnsClone = JSON.parse(JSON.stringify(this.columns));
                this.tableColumnsClone.unshift(selectColumn);
            },
            onOk:function(){
                let result = null;
                if(this.selectStore.length > 0){
                    if(this.muliSelect){
                        result = this.selectStore
                    }else{
                        result = this.selectStore[0]
                    }
                    this.$emit("onOk",result);
                    this.$emit("input",false);
                    this.clearSelectData();
                    this.boxShow = false;
                }else{
                    this.$Message.error("请选择一条记录");
                }
            },
            onClear:function(){
                this.clearSelectData();
                this.$emit("onClear");
                this.$emit("input",false);
                this.boxShow = false;

            },
            onCancel:function(){
                this.clearSelectData();
                this.$emit("input",false);
                this.boxShow = false;
            },
            doSearch:function(){
                this.clearSelectData();
                this.searchParams = {
                    "search":this.searchText
                }
            },
            onRowClick:function(row,index){
                this.selectOnChange(row,index);
            },
            //单选
            selectOnChange:function(row,index){
                this.selection = Array(index).fill(false);
                this.selection.splice(index, 1, true);
                this.selectStore=[row];
            },
            //多选
            onSelectionChange:function(rows){
                this.selectStore = rows;
            },
            clearSelectData:function(){
                this.selection = [];//清空已选项
                this.selectStore = [];//清空已选数据
            }
        },
        watch:{
            value:function(){
                if(this.value){
                    this.boxShow = true;
                }
                this.searchText="";
                this.searchParams={};
            },
            muliSelect:function(){
                this.init();
            },
            columns:function () {
                this.init()
            }
        },
        created(){
            this.init();
        }
    }
</script>

<style scoped>

</style>
