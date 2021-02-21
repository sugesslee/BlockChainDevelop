<template>
  <div class="address-detail">
    <Header :navItem="'数字资产'"></Header>
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
            <span class="left">发起人（接收者）</span>
            <span class="right">{{projectDetails.originatorName}}</span>
          </li>
          <li>
            <span class="left">发起人ID</span>
            <router-link :to="{name:'AddressDetail',params: { id: projectDetails.originatorId }}">
              <span class="right mark">{{projectDetails.originatorId}}</span>
            </router-link>
          </li>
          <li>
            <span class="left">项目描述</span>
            <span class="right descript">{{projectDetails.comment}}
            </span>
          </li>
        </ul>
        <div class="pic">
          <qrcode-vue :value="url" :size="qrSize" level="H"></qrcode-vue>
          <div class="des">扫描二维码查看项目详情</div>
        </div>
      </div>
      <!--<div class="head echart-head">项目进度</div>-->
      <!--<div id="myChart" :style="{width: '1080px', height: '380px'}"></div>-->
    </div>

    <transition-table-panel :cellValue="transitions" :defaultHead="4"></transition-table-panel>
    <div class="num-page">
      <el-pagination
        background
        layout="prev, pager, next"
        :total="trsCount"
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
  import QrcodeVue from 'qrcode.vue'
  import transitionTablePanel from '../components/transition-table-panel'
  import {Pagination} from 'element-ui'
  import {fetchTransactions, fetchProjectDetails} from '@/api/api'

  export default {
    data() {
      return {
        transitions: [],
        projectId: this.$route.params.id,
        projectDetails: {},
        trsCount: 0,
        url: 'https://app.caritas.lirencp.com/foundation/detail/',
        qrSize: 220
      }
    },
    created() {
      this.fetchTransactions({currency: 'CARITAS.' + this.projectId});
      this.fetchProjectDetails();
      this.url = this.url + this.projectId
    },
    mounted() {
      this.drawLine();
    },
    components: {
      Header,
      Footer,
      transitionTablePanel,
      elPagination: Pagination,
      QrcodeVue
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
      },
      drawLine() {
        // 基于准备好的dom，初始化echarts实例
        let myChart = this.$echarts.init(document.getElementById('myChart'))
        // 绘制图表
        myChart.setOption({
          color: ['#3398DB'],
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              type: 'shadow'
            }
          },
          xAxis: [
            {
              type: 'category',
              data: ['02-01', '02-11', '02-21', '02-01', '02-11', '02-21', '02-01', '02-11', '02-21', '02-01', '02-11', '02-21', '02-01', '02-11', '02-21', '02-01', '02-11', '02-21', '03-01', '03-11', '03-21', '03-01', '03-11', '03-21', '03-01', '03-11', '03-21', '03-01', '03-11', '03-21'],
              axisTick: {
                alignWithLabel: true
              }
            }
          ],
          yAxis: [
            {
              name: '筹款金额 (元)',
              type: 'value'
            }
          ],
          series: [
            {
              name: '直接访问',
              type: 'bar',
              barWidth: '60%',
              data: [330, 220, 10, 52, 200, 330, 220, 52, 200, 330, 220, 10, 52, 200, 330, 220, 10, 52, 200, 334, 390, 330, 220, 10, 52, 200, 334, 390, 330, 220]
            }
          ]
        })
      }
    }
  }
</script>

<style lang="stylus" scoped>
  .address-detail
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
        background: url("../../static/img/type10.png") no-repeat 60px center
      .echart-head
        margin-top: 70px
        background: url("../../static/img/type11.png") no-repeat 60px center
      .container
        position: relative
        h1
          height: 40px
          font-size: 24px
          font-family: PingFangSC-Medium
          color: rgba(51, 51, 51, 1)
          line-height: 40px
          padding-left: 60px
          margin-top: 40px
        .time
          height: 40px
          font-size: 14px
          font-family: PingFangSC-Regular
          color: rgba(153, 153, 153, 1)
          line-height: 40px
          background: url("../../static/img/name2.png") no-repeat 60px center
          padding-left: 150px
        ul
          li
            display: flex
            line-height: 40px
            .left
              flex: 0 0 200px
              padding-left: 60px
              font-size: 14px
              font-family: PingFangSC-Regular
              color: rgba(153, 153, 153, 1)
            .right
              flex: 1
              font-size: 14px
              font-family: dinpro
              color: rgba(102, 102, 102, 1)
              padding: 0 20px
              &.mark
                color: rgba(50, 145, 255, 1)
              &.descript
                background: rgba(250, 250, 250, 1)
        .pic
          position: absolute
          top: 0
          right: 0
          width: 230px
          border: 1px solid rgba(0, 0, 0, 0.05)
          padding: 5px
          box-sizing: border-box
          .des
            height: 40px
            font-size: 16px
            font-family: PingFangSC-Regular
            color: rgba(102, 102, 102, 1)
            line-height: 40px
            text-align: center
    .num-page
      text-align: center
      margin-top: 40px
</style>
