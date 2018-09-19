<template>
    <div class="editing-status">
        <Select  v-model ="val" transfer @on-change="doSave">
            <Option v-for="item in enums" :value="item.value" :key="item.value" >{{ item.label }}</Option>
        </Select>
    </div>
</template>

<script>
    export default {
        name: "select-editor",
        props:{
            row:Object,
            column:Object,
            index:Number
        },
        data(){
            return {
                val: this.row[this.column.key],
                enums:this.column.editor.enums
            }
        },
        computed:{
            label:function(){
                for(let item of this.enums){
                    if(item.value === this.val){
                        return item.label;
                    }
                }
            }
        },
        methods:{
            doSave:function(){
                let newVal = this.val;
                let validate = this.column.editor.validate;
                if(validate){
                    let result = validate(newVal,oldVal,this.row,this.index);
                    if(!result.valid){
                        this.$Message.error(result.msg);
                        return ;
                    }
                }
              var param = {
                rowIndex:this.index,
                columnKey:this.column.key,
                value:newVal
              }
              this.$emit("cell-on-change",param);

            }
        }
    }
</script>

<style lang="less" scoped>
    .editing-status{
        height:35px;
        line-height:35px;
        position:relative;
    }
</style>

