<template>
    <div class="export-data">
        <Row>
            <Form :label-width="60">
                <FormItem label="导出格式">
                    <Select v-model="fileFormat" style="width:315px">
                        <Option value="xlsx" key="xlsx">xlsx</Option>
                        <Option value="csv" key="csv">csv</Option>
                    </Select>
                </FormItem>
                <FormItem label="文件名">
                    <Input v-model="fileName" icon="document" placeholder="自定义文件名" />
                </FormItem>
                <FormItem>
                    <Button type="primary" @click="exportCurrentPage">导出当前页</Button>
                    <Button type="primary" ghost @click="exportAllData" style="margin-left: 8px">导出全部</Button>
                </FormItem>
            </Form>
        </Row>
    </div>
</template>

<script>
    import config from '@/config/config';
    export default {
        props:{
            tableColumns:{
                type: Array,
                default() {
                    return [];
                }
            },
            dataUrl:{
                type: String,
                default() {
                    return "";
                }
            },
            searchParams:{
                type: Object,
                default() {
                    return {};
                }
            },
            sortParams:{
                type: Object,
                default() {
                    return {};
                }
            },
            paginationParams:{
                type:Object,
                default() {
                    return {};
                }
            },
            exportName:String
        },
        data () {
            return {
                fileFormat: "xlsx",
                fileName: "表格数据"
            }
        },
        methods: {
            exportCurrentPage () {
                this.exportExcel("current");
            },
            exportAllData () {
                this.exportExcel("all");
            },
            exportExcel (exportType) {
                if(!exportType){
                    exportType = "current";
                }
                let self = this;
                let exportParams = {
                    tableColumns:self.tableColumns,
                    searchParams:self.searchParams,
                    sortParams:self.sortParams,
                    paginationParams:self.paginationParams,
                    fileFormat:self.fileFormat,
                    fileName:self.fileName,
                    exportType:exportType
                };
                var tempForm = document.createElement("form");
                tempForm.id = "formForExportExcel";
                tempForm.name = "formForExportExcel";
                tempForm.method = 'post';
                tempForm.action = "/" + config.appContext + self.dataUrl;
                tempForm.target = "_blank";
                document.body.appendChild(tempForm);
                var input = document.createElement("input");
                input.setAttribute("name","exportParams");
                input.setAttribute("type","hidden");
                input.setAttribute("value",JSON.stringify(exportParams));
                tempForm.appendChild(input);
                if(exportType == "current"){
                    for(var key in self.paginationParams){
                        var value = self.paginationParams[key];
                        var input = document.createElement("input");
                        input.setAttribute("name",key);
                        input.setAttribute("type","hidden");
                        input.setAttribute("value",value);
                        tempForm.appendChild(input);
                    }
                }
                for(var key in self.searchParams){
                    var value = self.searchParams[key];
                    var input = document.createElement("input");
                    input.setAttribute("name",key);
                    input.setAttribute("type","hidden");
                    input.setAttribute("value",value);
                    tempForm.appendChild(input);
                }
                for(var key in self.sortParams){
                    var value = self.sortParams[key];
                    var input = document.createElement("input");
                    input.setAttribute("name",key);
                    input.setAttribute("type","hidden");
                    input.setAttribute("value",value);
                    tempForm.appendChild(input);
                }
                tempForm.submit();
            }
        },
        watch:{
            exportName:function () {
                this.fileName = this.exportName;
            }
        }
    };
</script>
<style>

</style>
