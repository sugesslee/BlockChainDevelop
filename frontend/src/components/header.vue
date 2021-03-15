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
        <el-select v-model="select_value" placeholder="请选择查询方式" :popper-append-to-body="false">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value" :popper-append-to-body="false">
            </el-option>
          </el-select>
        <input type="text" v-model="search" placeholder="请根据查询方式输入查询条件">
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
        search: '',
        select_value: '',
        options: [
          {
          value: 'trac',
          label: '中药材溯源'
        },
        {
          value: 'num',
          label: '数值型属性查询'
        },
        {
          value: 'range',
          label: '连续型属性查询'
        },
        {
          value: 'key',
          label: '关键词属性查询'
        }]
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
        if (this.select_value === 'trac') {
          this.$router.push({name: 'TraceabilityResult', params: {id: id}})
        } else {
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
  }
</script>

<style lang="stylus" scoped>
//修改的是el-input的样式
  //一种方法是设置最里层el-input__inner的背景色 外层的两级父元素设置为透明色
  //另一种方法是从el-select到el-input__inenr的背景色都设置为需要的颜色
  /deep/ .el-select,
  /deep/ .el-input,
  /deep/ .el-input__inner{
    background: rgba(255, 255, 255, 0.1)
    color: rgba(102, 102, 102, 1);
    border:0px;
    border-radius:0px;
    text-align: center;
    height: 30px;
    margin: 0 0;
    float:left;
    width: 200px;
    border-radius: 4px 0px 0px 4px;
    border: none;
    outline: none;
    cursor: pointer;
    font-size: 16px;
  }

  //el-input聚焦的时候 外层的border会有一个样式
  /deep/ .el-select .el-input.is-focus .el-input__inner{
    border:0px;
  }

  //修改的是el-input上下的小图标的颜色
  /deep/ .el-select .el-input .el-select__caret{
    color: rgba(102, 102, 102, 1);
  }

  //修改总体选项的样式 最外层
  /deep/ .el-select-dropdown{
    background-color: #303133;
    margin: 0px;
    border:0px;
    font-size: 16px;
    width: 200px;
    border-radius: 2px;
  }
  /deep/ .el-select-dropdown ..el-popper::after{
    left:0px;
  }
  /deep/ .el-select-dropdown__empty{
    font-size: 14px;
    height: 20px;
  }

  //修改单个的选项的样式
  /deep/ .el-select-dropdown__item{
    background-color: transparent;
    // color:#fff;
    font-size: 16px;
  }

  //item选项的hover样式
  /deep/ .el-select-dropdown__item.hover,
  /deep/ .el-select-dropdown__item:hover{
    color: rgba(102, 102, 102, 1)
    font-size: 16px;
  }
  /deep/ .el-popper{
    left: 0px;
  }
  //修改的是下拉框选项内容上方的尖角
  /deep/ .el-popper .popper__arrow, .el-popper .popper__arrow::after{
    display: none;
  }
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
        // background: url("/static/img/logob/logo2.png") no-repeat center center
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
