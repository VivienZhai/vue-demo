<template>
  <div class="container">
    <div class="content">
      <ul>
        <li>网站首页</li>
      </ul>
    </div>
  </div>
</template>

<script>
	export default {}
</script>

<style lang="less" scoped>
	.container {
		background-color: rgb(230, 230, 230);
		height: 30px;
		.content {
			width: 1280px;
			margin: 0 auto;
			ul {
				float: right;
				li {
					float: left;
					margin: 6px 8px;
					color: rgb(170, 170, 170);
				}
			}
		}
	}
</style>
