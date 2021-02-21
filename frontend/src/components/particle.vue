<template>
  <div class="particle-wrapper">
    <vue-particles
      color="#ff0"
      :particleOpacity="0.7"
      :particlesNumber="80"
      shapeType="circle"
      :particleSize="4"
      linesColor="#dedede"
      :linesWidth="1"
      :lineLinked="true"
      :lineOpacity="0.4"
      :linesDistance="150"
      :moveSpeed="3"
      :hoverEffect="true"
      hoverMode="grab"
      :clickEffect="true"
      clickMode="push"
    >
    </vue-particles>
  </div>
</template>

<script>

  export default {}
</script>

<style lang="stylus" scoped>
  .particle-wrapper
    position: absolute
    top: 0
    left: 0
    height: 600px
    overflow: hidden
    min-width: 1200px
    width:100%
</style>
