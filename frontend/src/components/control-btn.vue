<template>
  <div class="control-btn" :class="{on:status}">
    <div class="box" :class="{on:status}" @click="toggleStatus"></div>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        status: false
      }
    },
    methods: {
      toggleStatus() {
        this.status = !this.status
      }
    }
  }
</script>

<style lang="stylus" scoped>
  .control-btn
    display: inline-block
    width: 36px
    height: 18px
    border-radius: 2px
    background: rgba(216, 216, 216, 1)
    position: relative
    vertical-align: middle
    &.on
      background: rgba(40, 207, 74, 1)
    .box
      position: absolute
      top: -1px
      left: 0
      width: 18px
      height: 18px
      cursor: pointer
      background: rgba(255, 255, 255, 1)
      box-shadow: 0px 0px 4px 0px rgba(0, 0, 0, 0.05)
      border: 1px solid rgba(0, 0, 0, 0.05)
      &.on
        right: 0 !important
        left: auto
        border: 1px solid rgba(40, 207, 74, 1)

</style>
