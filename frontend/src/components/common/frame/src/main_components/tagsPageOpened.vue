<style lang="less">
    @import "../../styles/css/frame.less";
</style>

<template>
    <div ref="scrollCon" @mousewheel="handlescroll" class="tags-outer-scroll-con">
        <div class="close-all-tag-con">
            <Dropdown transfer @on-click="handleTagsOption">
                <Button size="small" type="primary">
                    标签选项
                    <Icon type="arrow-down-b"></Icon>
                </Button>
                <DropdownMenu slot="list">
                    <DropdownItem name="clearAll">关闭所有</DropdownItem>
                    <DropdownItem name="clearOthers">关闭其他</DropdownItem>
                </DropdownMenu>
            </Dropdown>
        </div>
        <div ref="scrollBody" class="tags-inner-scroll-body" :style="{left: tagBodyLeft + 'px'}">
            <transition-group name="taglist-moving-animation">
                <Tag
                        type="dot"
                        v-for="(item, index) in pageOpenedList"
                        ref="tagsPageOpened"
                        :key="item.name"
                        :name="item.name"
                        @on-close="closePage"
                        @click.native="linkTo(item)"
                        :closable="item.name==='home_index'?false:true"
                        :color="item.children?((item.children[0].name===currentPageName || hasOpenChild=='1' && item.children[0].name == oldCurrentPageName)?'blue':'default'):((item.name===currentPageName || hasOpenChild=='1' && item.name == oldCurrentPageName)?'blue':'default')"
                >{{ item.title }}</Tag>
            </transition-group>
        </div>
    </div>
</template>

<script>
    import util from '@/libs/util';
    export default {
        name: 'tagsPageOpened',
        data () {
            return {
                tagBodyLeft: 0,
                currentScrollBodyWidth: 0,
                refsTag: [],
            };
        },
        props: {
            pageOpenedList: Array,
            currentPageName: [Number, String],
            hasOpenChild:[Number,String],
            oldCurrentPageName:[Number, String],
        },
        computed: {

        },
        methods: {
            closePage (event, name) {
                this.pageOpenedList.map((item, index) => {
                    if (item.name === name) {
                        this.pageOpenedList.splice(index, 1);
                    }
                });
                localStorage.pageOpenedList = JSON.stringify(this.pageOpenedList);
                if(this.pageOpenedList.length>0){
                    this.$router.push({
                        name : this.pageOpenedList[0].name
                    });
                }
            },
            linkTo (item) {
                let routerObj = {};
                routerObj.name = item.name;
                if (item.argu) {
                    routerObj.params = item.argu;
                }
                if (item.query) {
                    routerObj.query = item.query;
                }
                util.openNewPage(item.name);
                this.$router.push(routerObj);
            },
            handlescroll (e) {
                let left = 0;
                if (e.wheelDelta > 0) {
                    left = Math.min(0, this.tagBodyLeft + e.wheelDelta);
                } else {
                    if (this.$refs.scrollCon.offsetWidth - 100 < this.$refs.scrollBody.offsetWidth) {
                        if (this.tagBodyLeft < -(this.$refs.scrollBody.offsetWidth - this.$refs.scrollCon.offsetWidth + 100)) {
                            left = this.tagBodyLeft;
                        } else {
                            left = Math.max(this.tagBodyLeft + e.wheelDelta, this.$refs.scrollCon.offsetWidth - this.$refs.scrollBody.offsetWidth - 100);
                        }
                    } else {
                        this.tagBodyLeft = 0;
                    }
                }
                this.tagBodyLeft = left;
            },
            handleTagsOption (type) {
                if (type === 'clearAll') {
                    this.pageOpenedList.splice(1);
                    this.$router.push({
                        name: this.pageOpenedList[0].name
                    });
                    localStorage.currentPageName = this.pageOpenedList[0].name;
                    localStorage.pageOpenedList = JSON.stringify(this.pageOpenedList);
                } else {
                    let currentName = this.currentPageName;
                    let currentIndex = 0;
                    this.pageOpenedList.forEach((item, index) => {
                        if (item.name === currentName) {
                            currentIndex = index;
                        }
                    });
                    if (currentIndex === 0) {
                        this.pageOpenedList.splice(1);
                    } else {
                        this.pageOpenedList.splice(currentIndex + 1);
                        this.pageOpenedList.splice(0, currentIndex);
                    }
                    localStorage.pageOpenedList = JSON.stringify(this.pageOpenedList);
                }
                this.tagBodyLeft = 0;
            },
            moveToView (tag) {
                if (tag.offsetLeft < -this.tagBodyLeft) {
                    // 标签在可视区域左侧
                    this.tagBodyLeft = -tag.offsetLeft + 10;
                } else if (tag.offsetLeft + 10 > -this.tagBodyLeft && tag.offsetLeft + tag.offsetWidth < -this.tagBodyLeft + this.$refs.scrollCon.offsetWidth - 100) {
                    // 标签在可视区域
                } else {
                    // 标签在可视区域右侧
                    this.tagBodyLeft = -(tag.offsetLeft - (this.$refs.scrollCon.offsetWidth - 100 - tag.offsetWidth) + 20);
                }
            }
        },
        watch: {
            '$route' (to) {
                var self = this;
                localStorage.currentPageName = to.name;
                this.$nextTick(() => {
                    self.refsTag.forEach((item, index) => {
                        if (to.name === item.name) {
                            let tag = self.refsTag[index].$el;
                            self.moveToView(tag);
                        }
                    });
                });
            }
        }
    };
</script>
