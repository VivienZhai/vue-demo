<template>
    <div>
        <template v-for="(item, index) in menuList">
            <DropdownItem :name="item.name" :key="item.id" v-if="!item.children">
                <Icon :type="item.icon"></Icon><span style="padding-left:10px;">{{ item.title }}</span>
            </DropdownItem>
            <Dropdown placement="right-start" v-else :key="item.id">
                <DropdownItem :name="item.name" :key="item.id">
                    <Icon :type="item.icon"></Icon>
                    <span style="padding-left:10px;">{{ item.title }}</span>
                    <Icon type="ios-arrow-right"></Icon>
                </DropdownItem>
                <DropdownMenu style="width: 200px;" slot="list">
                    <sidebarSubMenuShrink  :menuList = "item.children" :iconColor="iconColor"></sidebarSubMenuShrink>
                </DropdownMenu>
            </Dropdown>
        </template>
    </div>
</template>

<script>
    import sidebarSubMenuShrink from './sidebarSubMenuShrink.vue';
    export default {
        name: 'sidebarSubMenuShrink',
        props: {
            menuList: {
                type: Array
            },
            iconColor: {
                type: String,
                default: 'white'
            }
        },
        data () {
            return {

            }
        },
        components: {
            sidebarSubMenuShrink
        },
        computed: {

        },
        methods: {

        },
        watch: {

        },
        create(){

        }
    };
</script>
