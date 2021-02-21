<template>
  <div class="block-detail">
    <Header></Header>
    <div class="detail-wrapper">
      <div class="head">区块详情</div>
      <ul>
        <li>
          <span class="left">区块ID</span>
          <span class="right">{{blockId}}</span>
        </li>
        <li>
          <span class="left">出块高度</span>
          <span class="right">{{block.height}}</span>
        </li>
        <li>
          <span class="left">出块时间</span>
          <span class="right">{{block.timestamp | timestampFilter}}</span>
        </li>
        <li>
          <span class="left">生产者</span>
          <router-link class="right mark" :to="{name:'AddressDetail',params: { id: block.generatorId }}">
            {{block.generatorId}}
          </router-link>
        </li>
        <li>
          <span class="left">确认数</span>
          <span class="right">{{block.confirmations}}</span>
        </li>
        <li>
          <span class="left">交易次数</span>
          <span class="right">{{block.numberOfTransactions}}</span>
        </li>
      </ul>
    </div>
    <transition-table-panel :cellValue="transitions" :defaultHead="2"></transition-table-panel>
    <div class="num-page">
      <el-pagination
        background
        layout="prev, pager, next"
        :total=numberOfTransactions
        :page-size="10"
        :prev-text="'上一页'"
        :next-text="'下一页'"
        @current-change="handleCurrentChange"
      >
      </el-pagination>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
  import Header from '../components/header'
  import Footer from '../components/footer'
  import transitionTablePanel from '../components/transition-table-panel'
  import {fetchTransactions, fetchBlockInfo} from '@/api/api'
  import timeUtils from '../utils/timeUtils'

  export default {
    data() {
      return {
        transitions: [],
        numberOfTransactions: 0,
        block: {},
        blockId: this.$route.params.id
      }
    },
    filters: {
      timestampFilter: timeUtils
    },
    created() {
      fetchTransactions({blockId: this.blockId}).then(response => {
        this.transitions = response.data.transactions;
        this.trsCount = response.data.count;
      });
      fetchBlockInfo({id: this.blockId}).then(response => {
        this.block = response.data.block;
        this.numberOfTransactions = response.data.numberOfTransactions;
      })
    },
    components: {
      Header,
      Footer,
      transitionTablePanel
    },
    methods: {
      handleCurrentChange(val) {
        let query = {
          page: val,
          blockId: this.blockId
        };
        fetchTransactions(query).then(response => {
          this.transitions = response.data.transactions;
        });
      }
    }
  }
</script>

<style lang="stylus" scoped>
  .block-detail
    .detail-wrapper
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
        background: url("/static/img/type3.png") no-repeat 60px center
      ul
        li
          display: flex
          height: 64px
          line-height: 64px
          padding: 0 60px
          border-bottom: 1px solid rgba(0, 0, 0, 0.05)
          .left
            flex: 1
            text-align: left
            font-size: 14px
            font-family: PingFangSC-Regular
            color: rgba(102, 102, 102, 1)
          .right
            flex: 1
            text-align: right
            font-size: 14px
            font-family: SFUIText-Regular
            color: rgba(102, 102, 102, 1)
            &.mark
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
          background: url("/static/img/more.png") no-repeat center right
          cursor: pointer

  .num-page
    text-align: center
    margin-top: 40px
</style>
