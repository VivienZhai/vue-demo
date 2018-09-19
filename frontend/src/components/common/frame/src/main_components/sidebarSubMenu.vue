<style lang="less">
    @import '../../styles/css/menu.less';
</style>

<template>
    <div>
        <template v-for="item in menuList">
            <MenuItem v-if="!item.children" :name="item.name" :key="item.id">
                <Icon :type="item.icon" :size="iconSize"></Icon>
                <span class="layout-text">{{ item.title }}</span>
            </MenuItem>

            <Submenu v-else :name="item.name" :key="item.id">
                <template slot="title">
                    <Icon :type="item.icon" :size="iconSize"></Icon>
                    <span class="layout-text">{{item.title}}</span>
                </template>
                <sidebarSubMenu :menuList = "item.children"></sidebarSubMenu>
            </Submenu>
        </template>
    </div>
</template>

<script>
import sidebarSubMenu from './sidebarSubMenu.vue';
export default {
    data () {
        return {

        };
    },
    name: 'sidebarSubMenu',
    props: {
        slotTopClass: String,
        menuList: Array,
        iconSize: Number
    },
    components: {
        sidebarSubMenu
    },
    computed: {

    },
    methods: {

    },
    watch: {

    },
    updated () {

    },
    mounted () {

    }
};
</script>
