<template>
  <div class="transition-detail">
    <div class="detail-wrapper">
      <div class="head">交易详情</div>
      <ul>
        <li>
          <span class="left">交易ID</span>
          <span class="right">{{ this.$route.params.id }}</span>
        </li>
        <li>
          <span class="left">交易类型</span>
          <span class="right">
            <span class="type" :class="getClassByTrsType(transition.type)">{{transition.type|getNameByTrsType}}</span>
          </span>
        </li>
        <li>
          <span class="left">交易时间</span>
          <span class="right">{{transition.timestamp | timestampFilter}}</span>
        </li>
        <li>
          <span class="left">发送方</span>
          <router-link class="right mark" :to="{path:'/m/address/detail/'+transition.senderId}"
                       v-if="transition.senderId">
            {{this.defaultSender(transition)}}
          </router-link>
          <span class="right" v-else>
             ----
          </span>
        </li>

        <li>
          <span class="left">接收方</span>
          <router-link class="right mark" :to="{path:'/m/address/detail/'+transition.recipientId}"
                       v-if="transition.recipientId">
            {{this.defaultRecipient(transition)}}
          </router-link>
          <span class="right" v-else>
             ----
          </span>
        </li>
        <li>
          <span class="left">交易量</span>
          <span class="right">{{transition.asset.amount|defaultValue}}</span>
        </li>
        <li>
          <span class="left">确认数</span>
          <span class="right">{{transition.confirmations}}</span>
        </li>
        <li>
          <span class="left">所属区块</span>
          <router-link class="right mark" :to="{path:'/m/block/detail/'+transition.blockId}">
            {{transition.blockId}}
          </router-link>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
  import {fetchTransaction} from '@/api/api'
  import timeUtils from '../../utils/timeUtils'
  import {defaultValue, getNameByTrsType, getClassByTrsType} from '../../utils/commonUtils'

  export default {
    data() {
      return {
        transition: {}
      }
    },
    filters: {
      timestampFilter: timeUtils,
      defaultValue: defaultValue,
      getNameByTrsType: getNameByTrsType
    },
    beforeCreate() {
      fetchTransaction({id: this.$route.params.id}).then(response => {
        this.transition = response.data.transaction
      });
    },
    methods: {
      getClassByTrsType: getClassByTrsType,
      defaultSender: function defaultUser(trs) {
        return trs.senderName ? trs.senderName : trs.senderId;
      },
      defaultRecipient: function defaultRecipient(trs) {
        return trs.recipientName ? trs.recipientName : trs.recipientId;
      }
    }
  }
</script>

<style lang="stylus" scoped>
  .transition-detail
    .detail-wrapper
      margin: .2rem 0 0 0
      .head
        height: 46px
        font-size: 20px
        font-family: PingFangSC-Regular
        color: rgba(102, 102, 102, 1)
        line-height: 46px
        padding: 0 0 0 .46rem
        border-bottom: 1px solid rgba(0, 0, 0, 0.05)
        background: url("/static/img/type4.png") no-repeat .2rem center
      ul
        li
          display: flex
          justify-content: space-between
          align-items: center
          height: .5rem
          line-height: .5rem
          padding: 0 .2rem
          border-bottom: 1px solid rgba(0, 0, 0, 0.05)
          .left
            text-align: left
            font-size: 14px
            font-family: PingFangSC-Regular
            color: rgba(102, 102, 102, 1)
            width: 1.6rem
          .right
            text-align: right
            font-size: 14px
            font-family: SFUIText-Regular
            color: rgba(102, 102, 102, 1)
            overflow: hidden;
            text-overflow:ellipsis;
            white-space: nowrap;
            &.mark
              color: rgba(50, 145, 255, 1)
            span
              padding: 5px
              box-shadow: 0px 2px 4px 0px rgba(137, 12, 0, 0.11)
              border-radius: 2px
              color: rgba(255, 255, 255, 1)
              background: rgba(254, 49, 128, 1)
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
