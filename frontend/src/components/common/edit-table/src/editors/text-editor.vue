<template>
    <div class="editing-status">
        <Input ref="input" type="text" v-model="val" @on-blur="doSave" @on-enter="doSave"/>
    </div>
</template>

<script>
    export default {
        name: "text-editor",
        props:{
            row:Object,
            column:Object,
            index:Number
        },
        data(){
            return {
                val: this.row[this.column.key]
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
                        this.$ref.input.focus();
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

