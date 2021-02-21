<template>
  <div class="table-panel">
    <div class="head">
      <div class="left">
        <img src="/static/img/type2.png">
        <span>最新区块</span>
      </div>
      <!--<div class="right">-->
      <!--<span>实时更新</span>-->
      <!--<control-btn></control-btn>-->
      <!--</div>-->
    </div>
    <div class="panel">
      <table>
        <tr>
          <th align="center" style="width: 60px">区块高度</th>
          <th align="center" style="width: 180px">时间</th>
          <th align="center" style="width: 320px">区块ID</th>
          <th align="center" style="width: 220px">生产者</th>
          <th align="center" style="width: 80px">交易数</th>
        </tr>
        <tr v-for="(item,index) in cellValue" :key="index">
          <td align="center">
            <router-link :to="{path:'/m/block/detail/'+item.id}" style="width: 60px">
              {{item.height}}
            </router-link>
          </td>
          <td align="center" class="time">
            {{item.timestamp | timestampFilter}}
          </td>
          <td align="center">
            <router-link :to="{path:'/m/block/detail/'+item.id}" style="width: 320px">
              {{item.id}}
            </router-link>
          </td>
          <td align="center">
            <router-link :to="{path:'/m/address/detail/'+item.generatorId}" style="width: 220px">
              {{item.generatorId}}
            </router-link>
          </td>
          <td align="center" class="mount">{{item.numberOfTransactions}}</td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
  import controlBtn from './control-btn'
  import timeUtils from '../utils/timeUtils'

  export default {
    props: {
      cellValue: {
        type: Array,
        default: () => []
      }
    },
    filters: {
      timestampFilter: timeUtils
    },
    components: {
      controlBtn
    }
  }
</script>

<style lang="stylus" scoped>
  .table-panel
    margin: 20px 0 0 0
    .head
      height: 46px
      line-height: 46px
      display: flex
      padding: 0 .2rem
      border-bottom: 1px solid rgba(0, 0, 0, 0.05)
      .left
        flex: 1
        text-align: left
        img
          vertical-align: middle
        span
          vertical-align: middle
          font-size: 20px
          font-family: PingFangSC-Regular
          color: rgba(102, 102, 102, 1)
          line-height: 28px
      .right
        flex: 1
        text-align: right
        span
          font-size: 14px
          font-family: PingFangSC-Regular
          color: rgba(153, 153, 153, 1)
          vertical-align: middle
    .panel::-webkit-scrollbar
      display: none
    .panel
      overflow: auto
      overflow:-moz-scrollbars-none
      table
        width: 950px
        tr
          height: 50px
          line-height: 50px
          text-align: left
          border-bottom: 1px solid rgba(0, 0, 0, 0.05)
          th
            font-size: 14px;
            font-family: PingFangSC-Regular
            color: rgba(51, 51, 51, 1)
            &:first-child
              padding-left: .2rem
          td
            font-size: 14px
            font-family: PingFangSC-Regular
            color: rgba(50, 145, 255, 1)
            a
              display: block
              overflow: hidden
              text-overflow:ellipsis
              white-space: nowrap
              &:first-child
                padding-left: .2rem
            &.time, &.mount
              font-family: SFUIText-Regular
              color: rgba(102, 102, 102, 1)
            & a
              color: rgba(50, 145, 255, 1)

    .more
      height: 64px
      line-height: 64px
      text-align: center
      font-size: 14px
      font-family: PingFangSC-Regular
      color: rgba(216, 216, 216, 1)
      span
        padding: 25px
        background: url("../../static/img/more.png") no-repeat center right
        cursor: pointer
</style>
