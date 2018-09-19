<template>
    <div class="page">
      <TopBar></TopBar>
      <NavBar></NavBar>
      <div class="breadcrumb">
        <div class="content">
          <Breadcrumb separator=">">
            <BreadcrumbItem to="/">首页</BreadcrumbItem>
            <BreadcrumbItem
              v-for="(item,index) in currentPath"
              :key="item.name"
              :to="item.path"
            >
              {{item.title}}
            </BreadcrumbItem>
          </Breadcrumb>
        </div>
      </div>
      <div class="page-body">
        <div class="menu">
          <SidebarMenu :menuList="menuList"></SidebarMenu>
        </div>
        <div class="content">
          <slot></slot>
        </div>
      </div>

    </div>
</template>
<script>
    import TopBar from "./components/TopBar";
    import NavBar from "./components/NavBar";
    import SidebarMenu from "./components/sidebarMenu";
    export default {
        components:{
          TopBar,
          NavBar,
          SidebarMenu
        },
        props:{
            menuList : Array,
            logoImg:String,
            minLogoImg:String,
            avatarPath : String,
            userId: String,
            userName: String,
            dropItem:Array,
        },
        data () {
            return {
              tagsList:[],
              currentPath:[],
            };
        },
        computed: {
        },
        methods: {
          setTagsList(menuList){
            for(var i=0;i<menuList.length;i++){
              if(menuList[i].children){
                this.setTagsList(menuList[i].children);
              }else{
                this.tagsList.push(menuList[i]);
              }
            }
          },
          setCurrentPath(){
            this.currentPath = [];
            let menuList = JSON.parse(sessionStorage.tagsList) || [];
            let pathstr = "";
            for(let menu of menuList){
              if(menu.name === this.$route.name){
                pathstr = menu.path;
                break;
              }
            }
            if(pathstr != ""){
              let pathNames = pathstr.split("#");
              for(var i=0;i<pathNames.length;i++){
                var name = pathNames[i];
                if(name){
                  let path = {
                    title:name,
                    name:name
                  };
                  if(i == pathNames.length - 1){
                    path = {
                      title:name,
                      name:name,
                      path:this.$route.path
                    };
                  }
                  this.currentPath.push(path);
                }
              }
              sessionStorage.currentPath = JSON.stringify(this.currentPath);
            }else{
              this.currentPath = JSON.parse(sessionStorage.currentPath) || [];
              let path = {
                title:this.$route.name,
                name:this.$route.name,
                path:this.$route.path
              };
              this.currentPath.push(path);
            }
          }
        },
        watch: {
          menuList(data){
            if(data){
              sessionStorage.menuList = JSON.stringify(data);
              this.tagsList = [];
              this.setTagsList(data);
              sessionStorage.tagsList = JSON.stringify(this.tagsList);
              this.setCurrentPath();
            }
          },
          '$route' (to,from) {
            this.setCurrentPath();
          }
        },
        mounted () {
        }
    };
</script>
<style lang="less" scoped>
  .page{
    background: #f2f2f2;
    .breadcrumb{
      .content{
        width:1280px;
        height:50px;
        margin:0 auto;
        line-height:50px;
      }
    }
    .page-body{
      width:1280px;
      margin:5px auto;
      padding-bottom: 20px;
      overflow: hidden;
      .menu{
        float:left;
      }
      .content{
        width:1080px;
        float:left;
        margin-left: 10px;
      }
    }
  }
</style>
