<template>
  <div class="transition">
    <div class="container">
      <block-table-panel :cellValue="blocks"></block-table-panel>
      <div class="num-page">
        <el-pagination
          background
          small
          layout="prev, pager, next"
          :total="count"
          @current-change="handleCurrentChange"
        >
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
  import blockTablePanel from '../../components/m-block-table-panel'
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
    .num-page
      text-align: center
      margin: 40px 0
</style>
