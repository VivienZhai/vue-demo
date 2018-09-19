<template>
    <div class="read-status">
        <span>{{val}}</span>
        <div class="edit-btn">
            <Button type="text" icon="md-create" @click="doEdit"></Button>
        </div>
        <div v-if="editing">
            <lambo-help-box v-model="editing" :url="helpBoxUrl" :columns="helpBoxColumns" :title="helpBoxTitle" :muliSelect="muliSelect" @onOk="doSave" @onClear="onClear">
            </lambo-help-box>
        </div>
    </div>
</template>

<script>
    import LamboHelpBox from "../../../helpbox";
    export default {
        name: "helpbox-editor",
        components:{
            "lambo-help-box":LamboHelpBox
        },
        props:{
            row:Object,
            column:Object,
            index:Number
        },
        data(){
            return {
                editing:false
            }
        },
        computed:{
            val:function(){
                return this.row[this.column.key];
            },
            helpBoxUrl:function(){
                console.log("this.column.editor.url="+this.column.editor.url)
                return this.column.editor.url;
            },
            helpBoxColumns:function(){
                return this.column.editor.columns;
            },
            callbackEvent:function(){
                return this.column.editor.callbackEvent;
            },
            helpBoxTitle:function(){
                return this.column.editor.title;
            },
            muliSelect:function(){
                return this.column.editor.muliSelect;
            }
        },
        methods:{
            doEdit:function(){
                this.editing = true;
            },
            doSave:function(result){
                var param = {
                    rowIndex:this.index,
                    columnKey:this.column.key,
                    value:result,
                    callbackEvent:this.column.editor.callbackEvent
                }
                this.$emit("cell-on-change",param);
            },
            onClear:function(){
                var param = {
                    rowIndex:this.index,
                    columnKey:this.column.key,
                    value:null,
                    callbackEvent:this.column.editor.callbackEvent
                }
                this.$emit("cell-on-change",param);
            }
        }
    }
</script>

<style lang="less" scoped>
    .read-status{
        height:35px;
        line-height:35px;
        margin-right:20px;
        position:relative;
        .edit-btn{
            position:absolute;
            top:0px;
            right:-40px;
        }
    }
    .editing-status{
        height:35px;
        line-height:35px;
        margin-right:20px;
        position:relative;
        .save-btn{
            position:absolute;
            top:0px;
            right:-40px;
        }
    }



      .lambo-edit-table {
          .show-edit-btn {
              /*margin-left: -15px;*/
          }
      }

</style>

