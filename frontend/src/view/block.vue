<template>
  <div class="transition">
    <Header></Header>
    <div class="container">
      <block-table-panel :cellValue="blocks"></block-table-panel>
      <div class="num-page">
        <el-pagination
          background
          layout="prev, pager, next"
          :total="count"
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
  import blockTablePanel from '../components/block-table-panel'
  import {Pagination} from 'element-ui'
  import {fetchBlocks} from '@/api/api'

  export default {
    data() {
      return {
        blocks: [],
        count: 0
      }
    },
    components: {
      Header,
      Footer,
      blockTablePanel,
      elPagination: Pagination
    },
    created() {
      this.fetchBlocks();
    },
    methods: {
      fetchBlocks() {
        fetchBlocks().then(response => {
          this.blocks = response.data.blocks;
          this.count = response.data.count
        })
      },
      //分页
      handleCurrentChange(val) {
        let query = {
          page: val
        };
        fetchBlocks(query).then(response => {
          this.blocks = response.data.blocks;
          this.count = response.data.count
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
</style>
