<template>
    <span @click="toggleExpanded(index)">
        <span v-for="space in row._level" class="ms-tree-space" :key="space"></span>
        <span class="tree-ctrl" v-if="iconShow(index,row)">
            <Icon v-if="!row._expand" type="plus-round" ></Icon>
            <Icon v-else type="minus-round" ></Icon>
        </span>
        {{row[column.key]}}
    </span>

</template>

<script>
    export default {
        name: "tree-ctrl",
        props:{
            params:Object
        },
        computed:{
            column(){
                return this.params.column;
            },
            row(){
                return this.params.row;
            },
            index(){
                return this.params.index;
            }
        },
        methods:{
            // 图标显示
            iconShow(index, record) {
                return (record.children)
            },
            toggleExpanded(index){
                this.$emit("on-toggle-expand",index)
            }
        }
    }
</script>

<style lang="less" scoped>
    .ms-tree-space {
        position: relative;
        top: 1px;
        display: inline-block;
        font-style: normal;
        font-weight: 400;
        line-height: 1;
        width: 18px;
        height: 14px;
        &::before {
            content: ""
        }
    }

    .tree-ctrl {
        position: relative;
        cursor: pointer;
        color: #2196F3;
        margin-left: -18px;
        padding:2.5px;
    }
</style>
