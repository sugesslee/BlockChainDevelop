<template>
  <div class="index">
    <div class="header">
      <Particle></Particle>
      <transition name="move">
        <Header v-show="down"></Header>
      </transition>
      <div class="navbar">
        <router-link :to="{path: '/'}">
          <div class="logo">
          </div>
        </router-link>
        <div class="nav">
          <ul>
            <!-- <router-link :to="{path: '/'}"> -->
              <!-- <li><span class="active">公示</span></li> -->
            <!-- </router-link> -->
            <!-- <router-link :to="{path: '/asset'}"> -->
              <!-- <li>项目</li> -->
            <!-- </router-link> -->
            <!--<li>节点</li>-->
          </ul>
        </div>
      </div>
      <div class="banner">
        <div class="search-wrapper">
          <input type="text" name="search" v-model="search" placeholder="交易ID/地址">
          <button class="search-btn" v-on:click="searchResult()"></button>
        </div>
        <div class="circle">
          <!-- <img src="static/img/name3.png"> -->
          <p>区块链查询优化技术研究及应用</p>
        </div>
      </div>
    </div>
    <div class="stat">
      <ul>
        <li>
          <div class="left">
            <img src="static/img/item1.png">
          </div>
          <div class="right">
            <span class="name">当前区块高度</span>
            <span class="count"><count-to :startVal='0' :endVal=nodeInfo.height :duration=duration></count-to></span>
          </div>
        </li>
        <li>
          <div class="left">
            <img src="static/img/item2.png">
          </div>
          <div class="right">
            <span class="name">24小时交易数</span>
            <span class="count"><count-to :startVal='0' :endVal=nodeInfo._24Trs :duration=duration></count-to></span>
          </div>
        </li>
        <li>
          <div class="left">
            <img src="static/img/item3.png">
          </div>
          <div class="right">
            <span class="name">注册用户数</span>
            <span class="count"><count-to :startVal='0' :endVal=nodeInfo.memCount :duration=duration></count-to></span>
          </div>
        </li>
        <li>
          <div class="left">
            <img src="static/img/item4.png">
          </div>
          <div class="right">
            <span class="name">当前节点数</span>
            <span class="count"><count-to :startVal='0' :style="{'font-family': 'myFirstFont'}"
                                          :endVal=nodeInfo.nodeCount :duration=duration></count-to></span>
          </div>
        </li>
      </ul>
    </div>
    <transitionTablePanel :cellValue="transitions"></transitionTablePanel>
    <div class="more">
      <router-link :to="{path: '/transition'}">
        <span>查看全部交易</span>
      </router-link>
    </div>
    <blockTablePanel :cellValue="blocks"></blockTablePanel>
    <div class="more">
      <router-link :to="{path: '/block'}">
        <span>查看全部区块</span>
      </router-link>
    </div>
    <Footer></Footer>
  </div>
</template>
<script>
  import blockTablePanel from '../components/block-table-panel'
  import transitionTablePanel from '../components/transition-table-panel'
  import Header from '../components/header'
  import Footer from '../components/footer'
  import countTo from 'vue-count-to'
  import Particle from '../components/particle'
  import {fetchNodeInfo, fetchBlocks, fetchTransactions, fetchSearch} from '@/api/api'

  export default {
    data() {
      return {
        duration: 1000,
        blocks: [],
        transitions: [],
        nodeInfo: {},
        down: false,
        search: ''
      }
    },
    components: {
      blockTablePanel,
      transitionTablePanel,
      Particle,
      countTo,
      Header,
      Footer
    },
    created() {
      this.fetchNodeInfo();
      this.fetchBlocks();
      this.fetchTransactions();
    },
    mounted() {
      window.addEventListener('scroll', this.handleScroll)
    },
    methods: {
      fetchNodeInfo() {
        fetchNodeInfo().then(response => {
          this.nodeInfo = response.data.nodeInfo
        })
      },
      fetchBlocks() {
        fetchBlocks().then(response => {
          this.blocks = response.data.blocks
        })
      },
      fetchTransactions() {
        fetchTransactions().then(response => {
          this.transitions = response.data.transactions
        })
      },
      handleScroll() {
        let scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop
        this.down = scrollTop !== 0;
      },
      searchResult() {
        let id = this.search;
        if (!id) {
          return false;
        }
        fetchSearch({id: id.trim()}).then(response => {
          let type = response.data.type;
          if (type === 'address') {
            this.$router.push({name: 'AddressDetail', params: {id: id}})
          }
          if (type === 'transaction') {
            this.$router.push({name: 'TransitionDetail', params: {id: id}})
          }
          if (type === 'block') {
            this.$router.push({name: 'BlockDetail', params: {id: id}})
          }
        })
      }
    },
    destroyed() {
      window.removeEventListener('scroll', this.handleScroll)
    }
  }

</script>

<style lang="stylus" scoped>
  @import "../assets/stylus/minixs.styl"
  .index
    .header
      width: 100%
      height: 660px
      background-gradual(left, rgba(24, 41, 78, 1), rgba(22, 35, 66, 1))
      .move-enter-active, .move-leave-active
        transition: opacity .5s
      .move-enter, .move-leave-to
        opacity: 0
      .navbar
        position: relative
        width: 1200px
        height: 60px
        margin: 0 auto
        background-gradual(left, rgba(24, 41, 78, 1), rgba(22, 35, 66, 1))
        .logo
          position: absolute
          left: 0
          width: 200px
          height: 60px
          background: url("../../static/img/logo@2x.png") no-repeat center center
        .nav
          margin-left: 250px
          ul
            li
              width: 80px
              height: 60px
              font-size: 14px
              font-family: PingFangSC-Regular, Lantinghei SC
              color: rgba(255, 255, 255, 1)
              line-height: 60px
              margin-left: 20px
              display: inline-block
              .active
                display: inline-block
                line-height: 35px
                border-bottom: 2px solid rgba(255, 255, 255, 1)
      .banner
        width: 1200px
        margin: 0 auto
        padding-top: 60px
        height: 600px
        box-sizing: border-box
        background-gradual(left, rgba(24, 41, 78, 1), rgba(22, 35, 66, 1))
        .search-wrapper
          position: relative
          margin: 0 auto
          width: 800px
          input
            width: 710px
            height: 60px
            background: rgba(255, 255, 255, 0.1)
            box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.5)
            border-radius: 4px
            font-size: 20px
            font-family: PingFangSC-Regular, Lantinghei SC
            color: rgba(255, 255, 255, 1)
            line-height: 60px
            padding-left: 24px
            box-sizing: border-box
            outline: none
          .search-btn
            position: absolute
            right: 0
            top: 0
            width: 90px
            height: 60px
            background: rgba(11, 29, 70, 1) url("../../static/img/search.png") no-repeat center center
            border-radius: 0px 4px 4px 0px
            border: none
            outline: none
            cursor: pointer
      .circle
        width: 467px
        height: 467px
        background: url("../../static/img/circle.png") no-repeat center center
        margin: 0 auto
        img
          position: relative
          left: -30px
          margin-top: 116px
        p
          font-size: 26px
          font-family: PingFangSC-Regular, Lantinghei SC
          color: rgba(255, 255, 255, 1)
          margin-top: 66px
          text-align: center
    .stat
      width: 1200px
      margin: 0 auto
      padding: 30px 0
      border-bottom: 1px solid rgba(0, 0, 0, 0.05)
      ul
        display: flex
        li
          flex: 1
          display: flex
          .left
            flex: 0 0 82px
          .right
            flex: 1
            .name
              display: block
              height: 28px
              font-size: 20px
              font-family: PingFangSC-Regular, Lantinghei SC
              color: rgba(153, 153, 153, 1)
              line-height: 28px
              padding-left: 23px
            .count
              display: block
              height: 33px
              font-size: 26px
              color: rgba(51, 51, 51, 1)
              line-height: 33px
              padding-left: 23px
              margin-top: 14px
              span
                font-family: dinpro
    .more
      height: 64px
      line-height: 64px
      text-align: center
      font-size: 14px
      font-family: PingFangSC-Regular, Lantinghei SC
      color: rgba(216, 216, 216, 1)
      span
        padding: 25px
        background: url("../../static/img/more.png") no-repeat center right
        cursor: pointer
</style>
