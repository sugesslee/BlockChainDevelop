<template>
  <div class="asset">
    <div class="container">
      <div class="head">组织</div>
      <div class="company">
        <div class="left" :style="{'backgroundImage':'url(static/img/company1.png)'}">
          <!--<img src="static/img/company1.png">-->
        </div>
        <div class="right">
          <img src="/static/img/name1.png">
          <p>
            查询优化技术研究及应用。
          </p>
          <router-link :to="{path: '/m/project'}">
            <button class="btn">查看全部项目</button>
          </router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'asset',
  data() {
    return {

    }
  }
}
</script>

<style lang="stylus" scoped>
  .asset
    .container
      margin: .2rem 0 0 0
      .head
        height: 46px
        font-size: 20px
        font-family: PingFangSC-Regular
        color: rgba(102, 102, 102, 1)
        line-height: 46px
        padding: 0 0 0 .46rem
        border-bottom: 1px solid rgba(0, 0, 0, 0.05)
        background: url("/static/img/type8.png") no-repeat .2rem center
      .company
        margin-top: 40px
        padding: 0 .2rem
        border-bottom: 2px solid rgba(0,0,0,.05)
        .left
          width: 100%
          height:270px
          background-position: center
          background-size: cover
        .right
          padding: .2rem 0
          h2
            font-size: 24px
            color: rgba(255, 221, 0, 1)
          p
            font-size: 18px
            font-family: PingFangSC-Regular
            color: rgba(102, 102, 102, 1)
            line-height: 28px
            margin: .1rem 0
            display: -webkit-box;
            -webkit-box-orient: vertical;
            -webkit-line-clamp: 4;
            overflow: hidden;
          .btn
            width: 133px
            height: 40px
            background: rgba(27, 118, 255, 1)
            border-radius: 100px
            border: none
            outline: none
            font-size: 16px
            font-family: PingFangSC-Regular
            color: rgba(255, 255, 255, 1)
            cursor: pointer
</style>
