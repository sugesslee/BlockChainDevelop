<template>
  <div class="index">
    <div class="m-header">
      <div class="flex">
        <router-link to="/m"><img src="" class="icon-logo" alt=""></router-link>
        <img src="/static/img/search.png" class="icon-search" alt="" @click="showSearch">
        <img src="/static/img/menu.png" class="icon-menu" alt="" @click="showMenu">
      </div>
      <ul :style="{height:menuHeight}">
        <!-- <li @click="menued">
          <router-link to="/m">公示</router-link>
        </li> -->
        <!-- <li @click="menued">
          <router-link to="/m/project">项目</router-link>
        </li> -->
      </ul>
      <div class="search flex" :style="{height:searchHeight}">
        <input type="text" v-model="search" placeholder="交易ID/地址" />
        <img src="/static/img/search.png" class="icon-menu" alt="" @click="searchResult">
      </div>
    </div>
    <router-view />
    <div class="m-footer">
      <span>© 查询优化技术研究及应用</span>
      <span>2018级硕士研究生</span>
    </div>
  </div>
</template>

<script>
import {fetchSearch} from '@/api/api'
export default {
  name: 'h5index',
  data(){
     return {
       menuHeight: 0, //导航栏控制显示和隐藏
       searchHeight: 0, //导航栏控制显示和隐藏
       search: ''
     }
  },
  methods: {
    showMenu() {
      this.searchHeight = 0;
      if (this.menuHeight === 0){
        this.menuHeight = '1rem';
      } else {
        this.menuHeight = 0;
      }
    },
    showSearch() {
      this.menuHeight = 0;
      if (this.searchHeight === 0){
        this.searchHeight = '0.5rem';
      } else {
        this.searchHeight = 0;
      }
    },
    menued(){
      this.menuHeight = 0;
    },
    searchResult() {
      let id = this.search;
      if (!id) {
        return false;
      }
      fetchSearch({id: id.trim()}).then(response => {
        this.searchHeight = 0;
        let type = response.data.type;
        if (type === 'address') {
          this.$router.push({path: '/m/address/detail/'+id})
        }
        if (type === 'transaction') {
          this.$router.push({path: '/m/transition/detail/'+id})
        }
        if (type === 'block') {
          this.$router.push({path: '/m/block/detail/'+id})
        }
      })
    }
  }
}
</script>

<style scoped lang="stylus">
@import "../../assets/stylus/minixs.styl"
.index
  max-width: 3.75rem
  margin: 0 auto
  .m-header
    padding: .2rem
    height: .28rem
    position: relative
    background-gradual(left, rgba(24, 41, 78, 1), rgba(22, 35, 66, 1))
    .icon-logo
      width: 1.8rem
    .icon-menu
      width: .3rem
    .icon-search
      width: .26rem
    ul
      position: absolute
      top: .68rem
      left: 0
      width: 100%
      overflow: hidden
      transition: .5s
      z-index: 20
      background-gradual(left, rgba(24, 41, 78, 1), rgba(22, 35, 66, 1))
      li
        line-height: .5rem
        a
          display: block
          color: #fff
          font-size: .16rem
          padding-left: .2rem
    .search
      position: absolute
      top: .68rem
      left: 0
      width: 100%
      overflow: hidden
      transition: .5s
      padding: 0 .2rem
      z-index: 20
      box-sizing: border-box
      line-height: .5rem
      background-gradual(left, rgba(24, 41, 78, 1), rgba(22, 35, 66, 1))
      input
        width: 90%
        height: .36rem
        border: none
        background: hsla(0,0%,100%,.1)
        text-indent: .12rem
        color: #fff
      img
        width: .22rem
      input:focus
        outline: none
.m-footer
  background-gradual(left, rgba(24, 41, 78, 1), rgba(22, 35, 66, 1))
  padding: .2rem
  span
    font-size: .12rem
    color: #fff
  span:nth-child(2)
    margin-left: .2rem
</style>
