<template>
  <div class="transition">
    <div class="container">
      <transition-table-panel :cellValue="transitions"></transition-table-panel>
      <div class="num-page">
        <el-pagination
          background
          layout="prev, pager, next"
          small
          :total=trsCount
          :page-size="10"
          @current-change="handleCurrentChange"
        >
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
  import transitionTablePanel from '../../components/m-transition-table-panel'
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
    .num-page
      text-align: center
      margin: 40px 0
      .el-pagination
        .btn-prev
          padding: 0 5px !important
        .btn-next
          padding: 0 5px !important
</style>
