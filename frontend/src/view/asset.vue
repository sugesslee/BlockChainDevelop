<template>
  <div class="asset">
    <Header :navItem="'项目'"></Header>
    <div class="container">
      <div class="head">组织</div>
      <div class="company">
        <div class="left" :style="{'backgroundImage':'url(static/img/company1.png)'}">
          <!--<img src="static/img/company1.png">-->
        </div>
        <div class="right">
          <img src="static/img/name1.png">
          <p>
            查询优化技术研究及应用。
          </p>
          <router-link :to="{path: '/project'}">
            <button class="btn">查看全部项目</button>
          </router-link>
        </div>
      </div>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
  import Header from '../components/header'
  import Footer from '../components/footer'

  export default {
    components: {
      Header,
      Footer
    }
  }
</script>

<style lang="stylus" scoped>
  .asset
    .container
      width: 1200px
      margin: 100px auto 0 auto
      .head
        height: 64px
        font-size: 20px
        font-family: PingFangSC-Regular
        color: rgba(102, 102, 102, 1)
        line-height: 64px
        padding: 0 60px 0 90px
        border-bottom: 1px solid rgba(0, 0, 0, 0.05)
        background: url("../../static/img/type8.png") no-repeat 60px center
      .company
        display: flex
        margin-top: 40px
        .left
          flex: 0 0 360px
          width: 360px
          height:270px
          background-position: center
          background-size: cover
        .right
          flex: 1
          padding-left: 40px
          position: relative
          h2
            font-size: 24px
            color: rgba(255, 221, 0, 1)
          p
            font-size: 18px
            font-family: PingFangSC-Regular
            color: rgba(102, 102, 102, 1)
            line-height: 28px
            margin-top: 30px
          .btn
            position: absolute
            bottom: 0
            left: 40px
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
