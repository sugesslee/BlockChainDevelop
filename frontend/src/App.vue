<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
  export default {
    name: 'App'
  }
</script>
<style lang="stylus">
  @import "assets/stylus/reset.styl"
  @import "assets/stylus/minixs.styl"
  .footer
    width: 100%
    height: 80px
    background-gradual(left, rgba(24, 41, 78, 1), rgba(22, 35, 66, 1))
    margin-top: 100px
    .content
      width: 1200px
      margin: 0 auto
      height: 80px
      line-height: 80px
      background-gradual(left, rgba(24, 41, 78, 1), rgba(22, 35, 66, 1))
      span
        font-size: 12px
        font-family: PingFangSC-Regular
        color: rgba(255, 255, 255, 1)
        padding-left: 30px
</style>
