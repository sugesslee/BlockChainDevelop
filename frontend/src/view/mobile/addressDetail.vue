<template>
  <div class="address-detail">
    <div class="detail-wrapper">
      <div class="head">用户地址详情</div>
      <ul>
        <li>
          <span class="left">地址</span>
          <span class="right">{{address}}</span>
        </li>
        <li>
          <span class="left">用户名称</span>
          <span class="right">{{userName}}</span>
        </li>
        <li>
          <span class="left">捐款总次数</span>
          <span class="right">{{helpedCount}}</span>
        </li>
        <li>
          <span class="left">捐款总金额</span>
          <span class="right">{{helpedAmount}}</span>
        </li>
        <li>
          <span class="left">曙光值</span>
          <span class="right">{{helpedAmount*10}}</span>
        </li>
        <li>
          <span class="left">发起的项目</span>
          <span class="right mark">{{projectCount}}</span>
        </li>
        <div v-for="(item,index) in projects" :key="index">
          <li>
            <span class="left">项目{{index+1}}</span>
            <router-link :to="{path:'/m/project/detail/'+item.projectId}">
              <span class="right mark">{{item.title}}</span>
            </router-link>
          </li>
        </div>
      </ul>
    </div>
    <transition-table-panel :cellValue="transitions" :defaultHead="3"></transition-table-panel>
    <div class="num-page">
      <el-pagination
        background
        small
        layout="prev, pager, next"
        :total=trsCount
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
  import {fetchTransactions, fetchAddressDetails} from '@/api/api'

  export default {
    data() {
      return {
        address: this.$route.params.id,
        transitions: [],
        trsCount: 0,
        projects: [],
        projectCount: 0,
        userName: '爱心人士',
        helpedCount: 0, //捐款次数,
        loveLevel: 0,
        helpedAmount: 0
      }
    },
    created() {
      this.fetchTransactions();
      fetchAddressDetails({blockChainAddress: this.address}).then(response => {
        this.projects = response.data.data.projectList;
        this.projectCount = response.data.data.projectCount;
        this.userName = response.data.data.userName;
        this.helpedCount = response.data.data.helpedCount;
        this.loveLevel = response.data.data.loveLevel;
        this.helpedAmount = response.data.data.helpedAmount;
      });
      scrollTo(0, 0);
    },
    components: {
      transitionTablePanel,
      elPagination: Pagination
    },
    beforeRouteUpdate(to, from, next) {
      this.fetchTransactions({senderId: to.params.id});
      fetchAddressDetails({blockChainAddress: to.params.id}).then(response => {
        this.projects = response.data.data.projectList;
        this.projectCount = response.data.data.projectCount;
        this.userName = response.data.data.userName;
      });
      next()
    },
    methods: {
      fetchTransactions(param) {
        let query = param || {
          senderId: this.address,
          recipientId: this.recipientId
        };
        fetchTransactions(query).then(response => {
          this.transitions = response.data.transactions;
          this.trsCount = response.data.count;
        })
      },
      handleCurrentChange(val) {
        let query = {
          senderId: this.address,
          recipientId: this.recipientId,
          page: val
        };
        fetchTransactions(query).then(response => {
          this.transitions = response.data.transactions;
          this.trsCount = response.data.count;
        })
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
        background: url("/static/img/type6.png") no-repeat .2rem center
      ul
        li
          display: flex
          height: 64px
          line-height: 64px
          padding: 0 .2rem
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
            overflow: hidden;
            text-overflow:ellipsis;
            white-space: nowrap;
            &.mark
              color: rgba(50, 145, 255, 1)
            .out
              color: rgba(102, 102, 102, 1)
            .in
              color: rgba(41, 202, 58, 1)

    .num-page
      text-align: center
      margin: 40px 0
</style>
