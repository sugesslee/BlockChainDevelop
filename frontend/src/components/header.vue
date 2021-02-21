<template>
  <div id="header">
    <div class="navbar">
      <router-link :to="{path: '/'}">
        <div class="logo">
        </div>
      </router-link>
      <div class="nav">
        <ul>
          <!-- <router-link :to="{path: '/'}">
            <li><span :class="{active:navItem==='公示'}">公示</span></li>
          </router-link> -->
          <!-- <router-link :to="{path: '/asset'}">
            <li><span :class="{active:navItem==='项目'}">项目</span></li>
          </router-link> -->
        </ul>
      </div>
      <div class="search">
        <input type="text" v-model="search" placeholder="交易ID/地址">
        <div class="search-btn" v-on:click="searchResult()">搜索</div>
      </div>
    </div>
  </div>
</template>

<script>
  import {fetchSearch} from '@/api/api'

  export default {
    data() {
      return {
        search: ''
      }
    },
    props: {
      navItem: {
        type: String,
        default: '公示'
      }
    },
    methods: {
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
    }
  }
</script>

<style lang="stylus" scoped>
  #header
    position: fixed
    top: 0
    left: 0
    width: 100%
    height: 60px
    background: rgba(255, 255, 255, 1)
    box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.06)
    z-index: 9999
    .navbar
      position: relative
      width: 1200px
      height: 60px
      margin: 0 auto
      .logo
        position: absolute
        left: 0
        width: 200px
        height: 60px
        background: url("/static/img/logo2.png") no-repeat center center
      .nav
        margin-left: 250px
        ul
          li
            width: 80px
            height: 60px
            font-size: 14px
            font-family: PingFangSC-Regular
            color: rgba(102, 102, 102, 1)
            line-height: 60px
            margin-left: 20px
            display: inline-block
            .active
              display: inline-block
              line-height: 35px
              border-bottom: 2px solid rgba(50, 145, 255, 1)
      .search
        position: absolute
        top: 0
        right: 0
        height: 40px
        margin-top: 10px
        box-sizing: border-box
        border-bottom: 1px solid rgba(216, 216, 216, 1)
        input
          height: 30px
          width: 380px
          border: none
          outline: none
          font-size 16px
        .search-btn
          display: inline-block
          cursor: pointer
          width: 50px
          height: 30px
          line-height: 30px
          background: rgba(50, 145, 255, 1)
          box-shadow: 0px 2px 4px 0px rgba(0, 80, 173, 0.16)
          border-radius: 2px
          font-size: 12px;
          font-family: PingFangSC-Regular
          color: rgba(255, 255, 255, 1)
          text-align: center
</style>
