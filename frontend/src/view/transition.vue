<template>
  <div class="transition">
    <Header></Header>
    <div class="container">
      <transition-table-panel :cellValue="transitions"></transition-table-panel>
      <div class="num-page">
        <el-pagination
          background
          layout="prev, pager, next"
          :total=trsCount
          :page-size="10"
          :prev-text="'上一页'"
          :next-text="'下一页'"
          @current-change="handleCurrentChange"
        >
        </el-pagination>
      </div>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
  import Header from '../components/header'
  import Footer from '../components/footer'
  import transitionTablePanel from '../components/transition-table-panel'
  import {Pagination} from 'element-ui'
  import {fetchTransactions} from '@/api/api'

  export default {
    data() {
      return {
        transitions: [],
        trsCount: 0
      }
    },
    created() {
      this.fetchTransactions()
    },
    components: {
      Header,
      Footer,
      transitionTablePanel,
      elPagination: Pagination
    },
    methods: {
      fetchTransactions() {
        fetchTransactions().then(response => {
          this.transitions = response.data.transactions;
          this.trsCount = response.data.count;
        })
      },
      //分页
      handleCurrentChange(val) {
        let query = {
          page: val
        };
        fetchTransactions(query).then(response => {
          this.transitions = response.data.transactions;
          this.trsCount = response.data.count;
        });
      }
    }
  }
</script>

<style lang="stylus" scoped>
  .transition
    .container
      margin-top: 100px
    .num-page
      text-align: center
      margin-top: 40px
      .el-pagination
        .btn-prev
          padding: 0 5px !important
        .btn-next
          padding: 0 5px !important
</style>
