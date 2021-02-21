<template>
  <div class="address-detail">
    <div class="detail-wrapper">
      <div class="head">项目详情</div>
      <div class="container">
        <h1>{{projectDetails.title}}</h1>
        <div class="time">{{projectDetails.beginTime}}</div>
        <ul>
          <li>
            <span class="left">项目地址</span>
            <span class="right">{{projectDetails.blockChainAddress}}</span>
          </li>
          <li>
            <span class="left">目标金额</span>
            <span class="right">{{projectDetails.fundGoal}}</span>
          </li>
          <li>
            <span class="left">已筹金额</span>
            <span class="right">{{projectDetails.obtainFund}}</span>
          </li>
          <li>
            <span class="left">发起人(接收者)</span>
            <span class="right">{{projectDetails.originatorName}}</span>
          </li>
          <li>
            <span class="left">发起人ID</span>
            <span class="right mark">
              <router-link :to="{path:'/m/address/detail/'+projectDetails.originatorId}">
                {{projectDetails.originatorId}}
              </router-link>
            </span>
          </li>
          <li>
            <span class="left">项目描述</span>
            <span class="descript">{{projectDetails.comment}}
            </span>
          </li>
        </ul>
      </div>
      <!--<div class="head echart-head">项目进度</div>-->
      <!--<div id="myChart" :style="{width: '1080px', height: '380px'}"></div>-->
    </div>

    <transition-table-panel :cellValue="transitions" :defaultHead="4"></transition-table-panel>
    <div class="num-page">
      <el-pagination
        background
        small
        layout="prev, pager, next"
        :total="trsCount"
        :page-size="10"
        @current-change="handleCurrentChange"
      >
      </el-pagination>
    </div>
  </div>
</template>
<script>
  import transitionTablePanel from '../../components/m-transition-table-panel'
  import {Pagination} from 'element-ui'
  import {fetchTransactions, fetchProjectDetails} from '@/api/api'

  export default {
    data() {
      return {
        transitions: [],
        projectId: this.$route.params.id,
        projectDetails: {},
        trsCount: 0,
        url: 'https://testnet.sgchou.com/foundation/detail/'
      }
    },
    created() {
      this.fetchTransactions({currency: 'CARITAS.' + this.projectId});
      this.fetchProjectDetails();
      this.url = this.url + this.projectId
    },
    components: {
      transitionTablePanel,
      elPagination: Pagination
    },
    methods: {
      fetchTransactions(query) {
        fetchTransactions(query).then(response => {
          this.transitions = response.data.transactions;
          this.trsCount = response.data.count;
        })
      },
      fetchProjectDetails() {
        fetchProjectDetails({projectId: this.projectId}).then(response => {
          this.projectDetails = response.data.data;
        })
      },
      handleCurrentChange(val) {
        let query = {
          page: val,
          currency: 'CARITAS.' + this.projectId
        };
        fetchTransactions(query)
      }
    }
  }
</script>

<style lang="stylus" scoped>
  .address-detail
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
        background: url("/static/img/type10.png") no-repeat .2rem center
      .echart-head
        margin-top: 70px
        background: url("/static/img/type11.png") no-repeat .2rem center
      .container
        position: relative
        h1
          height: 40px
          font-size: 24px
          font-family: PingFangSC-Medium
          color: rgba(51, 51, 51, 1)
          line-height: 40px
          padding-left: .2rem
          margin-top: .2rem
        .time
          height: 40px
          font-size: 14px
          font-family: PingFangSC-Regular
          color: rgba(153, 153, 153, 1)
          line-height: 40px
          background: url("/static/img/name2.png") no-repeat .2rem center
          padding-left: 1rem
        ul
          li
            display: flex
            justify-content: space-between
            line-height: 40px
            padding: 0 .2rem
            .left
              font-size: 14px
              width: 1.12rem
              font-family: PingFangSC-Regular
              color: rgba(153, 153, 153, 1)
            .right
              font-size: 14px
              font-family: dinpro
              color: rgba(102, 102, 102, 1)
              overflow: hidden;
              text-overflow:ellipsis;
              white-space: nowrap;
              &.mark
                color: rgba(50, 145, 255, 1)
            .descript
              background: rgba(250, 250, 250, 1)
              display: -webkit-box;
              -webkit-box-orient: vertical;
              -webkit-line-clamp: 5;
              overflow: hidden;
              width: 2.6rem
    .num-page
      text-align: center
      margin: 40px 0
</style>
