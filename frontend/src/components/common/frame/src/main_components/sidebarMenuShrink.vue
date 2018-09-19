<template>
    <div>
        <template v-for="(item, index) in menuList">
            <div style="text-align: center;">
                <Dropdown transfer placement="right-start" :key="item.id" @on-click="changeMenu">
                    <Button style="width: 70px;margin-left: -5px;padding:10px 0;" type="text">
                        <Icon :size="20" :color="iconColor" :type="item.icon"></Icon>
                    </Button>
                    <DropdownMenu style="width: 200px;" slot="list">
                        <template v-for="(child, i) in item.children">
                            <DropdownItem :name="child.name" :key="child.id"><Icon :type="child.icon"></Icon><span style="padding-left:10px;">{{ child.title }}</span></DropdownItem>
                        </template>
                        <!--<DropdownItem :name="item.name" :key="index" v-if="!item.children">
                            <Icon :type="item.icon"></Icon><span style="padding-left:10px;">{{ item.title }}</span>
                        </DropdownItem>
                        <sidebarSubMenuShrink v-else :menuList = "item.children" :iconColor="iconColor"></sidebarSubMenuShrink>-->
                    </DropdownMenu>
                </Dropdown>
            </div>
        </template>
    </div>
</template>

<script>
    import util from '@/libs/util';
    import sidebarSubMenuShrink from './sidebarSubMenuShrink.vue';
    export default {
        name: 'sidebarMenuShrink',
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
                currentPageName: 0,
                openedSubmenuArr: [],
                hasOpenChild:0,
                oldCurrentPageName:'',
            };
        },
        components: {
            sidebarSubMenuShrink
        },
        computed: {

        },
        methods: {
            changeMenu (active) {
                util.openNewPage(active);
                this.$router.push({
                    name: active
                });
            },
        },
        watch: {
            '$route' (to,from) {
                //改变当前页的名称
                this.oldCurrentPageName = localStorage.oldCurrentPageName = from.name;
                let currentPageName = this.currentPageName = to.name;
                //改变currentPath
                let menuList = JSON.parse(localStorage.tagsList) || [];
                let pathstr = "", currentPath = [];
                for(let menu of menuList){
                    if(menu.name === currentPageName){
                        pathstr = menu.path;
                        break;
                    }
                }
                if(pathstr == ""){
                    currentPath = JSON.parse(localStorage.currentPath);
                    currentPath.push({
                        title:currentPageName,
                        name:currentPageName
                    });
                    this.hasOpenChild = localStorage.hasOpenChild = 1;
                }else{
                    let pathNams = pathstr.split("#");
                    for(let name of pathNams){
                        if(name){
                            let path = {
                                title:name,
                                name:name
                            };
                            currentPath.push(path);
                        }
                    }
                    this.hasOpenChild = localStorage.hasOpenChild = 0;
                }
                this.$emit("currentPath",currentPath);
                localStorage.currentPath = JSON.stringify(currentPath);
                //改变pageTagsList
                this.$emit("hasOpenChild",this.hasOpenChild);
                this.$emit("oldCurrentPageName",this.oldCurrentPageName);
                this.$emit("pageOpenedList",JSON.parse(localStorage.pageOpenedList));
                this.$emit("currentPageName",this.currentPageName);
            }
        },
        create(){
            this.currentPageName = localStorage.currentPageName;
            this.openedSubmenuArr = JSON.parse(localStorage.openedSubmenuArr);
        }
    };
</script>
