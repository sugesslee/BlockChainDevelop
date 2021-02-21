<template>
  <div class="table-panel">
    <div class="head" v-show="defaultHead===1">
      <div class="left">
        <img src="static/img/type1.png">
        <span>最新交易</span>
      </div>
      <!--<div class="right">-->
      <!--<span>实时更新</span>-->
      <!--<control-btn></control-btn>-->
      <!--</div>-->
    </div>
    <div class="head2" v-show="defaultHead===2">该区块内交易</div>
    <div class="head3" v-show="defaultHead===3">用户交易记录</div>
    <div class="head4" v-show="defaultHead===4">项目交易记录</div>
    <div class="panel">
      <table>
        <tr>
          <th align="center" style="width:90px">交易类型</th>
          <th align="center" style="width:150px">时间</th>
          <th align="center" style="width:270px">交易ID</th>
          <th align="center" style="width:190px">发送者</th>
          <th align="center" style="width:190px">接收者</th>
          <th align="center" style="width:90px">金额</th>
        </tr>
        <tr v-for="(item,index) in cellValue" :key="index">

          <td align="center">
            <span class="type" :class="getClassByTrsType(item.type)">{{item.type|getNameByTrsType}}</span>
          </td>
          <td align="center" class="time">{{item.timestamp | timestampFilter}}</td>

          <td align="center" class="over-flow-hide">
            <router-link :to="{name:'TransitionDetail',params: { id: item.id }}">
              {{item.id}}
            </router-link>
          </td>

          <td align="center" class="over-flow-hide" v-if="item.senderId">
            <router-link :to="{name:'AddressDetail',params: { id: item.senderId }}">
              {{defaultSender(item)}}
            </router-link>
          </td>
          <td align="center" class="over-flow-hide" v-else>
            ----
          </td>

          <td align="center" class="over-flow-hide" v-if="item.recipientId">
            <router-link :to="{name:'AddressDetail',params: { id: item.recipientId }}">
              {{defaultRecipient(item)}}
            </router-link>
          </td>
          <td align="center" class="over-flow-hide" v-else>
            ----
          </td>

          <td align="center" class="mount">{{item.asset.amount | defaultValue}}</td>

        </tr>
      </table>

    </div>
  </div>
</template>

<script>
  import controlBtn from './control-btn'
  import timeUtils from '../utils/timeUtils'
  import {defaultValue, getNameByTrsType, getClassByTrsType} from '../utils/commonUtils'

  export default {
    props: {
      cellValue: {
        type: Array,
        default: () => []
      },
      defaultHead: {
        type: Number,
        default: 1
      }
    },
    filters: {
      timestampFilter: timeUtils,
      defaultValue: defaultValue,
      getNameByTrsType: getNameByTrsType
    },
    methods: {
      goDetail() {
        this.$router.push({path: '/transition/detail'})
      },
      defaultSender: function defaultUser(trs) {
        return trs.senderName ? trs.senderName : trs.senderId;
      },
      defaultRecipient: function defaultRecipient(trs) {
        return trs.recipientName ? trs.recipientName : trs.recipientId;
      },
      getClassByTrsType: getClassByTrsType
    },
    components: {
      controlBtn
    }
  }
</script>

<style lang="stylus" scoped>
  .table-panel
    width: 1200px
    margin: 60px auto 0 auto
    .head
      height: 64px
      line-height: 64px
      display: flex
      padding: 0 60px
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
    .head2
      height: 64px
      font-size: 20px
      font-family: PingFangSC-Regular
      color: rgba(102, 102, 102, 1)
      line-height: 64px
      padding: 0 60px 0 90px
      border-bottom: 1px solid rgba(0, 0, 0, 0.05)
      background: url("../../static/img/type5.png") no-repeat 60px center
    .head3
      height: 64px
      font-size: 20px
      font-family: PingFangSC-Regular
      color: rgba(102, 102, 102, 1)
      line-height: 64px
      padding: 0 60px 0 90px
      border-bottom: 1px solid rgba(0, 0, 0, 0.05)
      background: url("../../static/img/type7.png") no-repeat 60px center
    .head4
      height: 64px
      font-size: 20px
      font-family: PingFangSC-Regular
      color: rgba(102, 102, 102, 1)
      line-height: 64px
      padding: 0 60px 0 90px
      border-bottom: 1px solid rgba(0, 0, 0, 0.05)
      background: url("../../static/img/type12.png") no-repeat 60px center
    .panel
      table
        width: 100%
        tr
          height: 64px
          line-height: 64px
          text-align: left
          border-bottom: 1px solid rgba(0, 0, 0, 0.05)
          th
            font-size: 14px;
            font-family: PingFangSC-Regular
            color: rgba(51, 51, 51, 1)
            padding: 0 24px 0 30px
            &:first-child
              padding-left: 80px
          td
            font-size: 14px
            font-family: PingFangSC-Regular
            overflow: hidden;
            text-overflow:ellipsis;
            white-space: nowrap;
            padding: 0 24px 0 30px
            & a
              color: rgba(50, 145, 255, 1)
            &:first-child
              padding-left: 80px
            &.time, &.mount
              font-family: dinpro
              color: rgba(102, 102, 102, 1)
            &.over-flow-hide
              max-width: 110px;
              overflow: hidden;
              text-overflow: ellipsis;
              white-space: nowrap;
            & > .type
              padding: 5px
              background: rgba(254, 49, 128, 1)
              box-shadow: 0px 2px 4px 0px rgba(137, 12, 0, 0.11)
              border-radius: 2px
              color: rgba(255, 255, 255, 1)
              &.color_delegate
                background: #1B76FF
              &.color_vote
                background: #389bcf
              &.color_uia_issuer
                background: #FFBE00
              &.color_uia_asset
                background: #FF5F57
              &.color_uia_issue
                background: #FF4976
              &.color_uia_transfer
                background: #cf60c9

</style>
