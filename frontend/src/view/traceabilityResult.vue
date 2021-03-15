<template>
  <div class="result-detail">
    <Header></Header>

    <div class="block">
        <el-timeline>
            <el-timeline-item
            v-for="(item, index) in resultList"
            :key="index"
            :timestamp=item.timestame
            placement="top">
                <el-card>
                    <el-tag type="info" style="float:left;padding-left:10px">{{item.header}}</el-tag>
                    <el-tag type="success" style="height:auto;padding-left:10px"><pre>{{item.trac}}</pre></el-tag>
                    <el-tag type="success" style="height:auto;padding-left:10px"><pre>{{item.info}}</pre></el-tag>
                </el-card>
            </el-timeline-item>
        </el-timeline>
    </div>
    <Footer></Footer>
  </div>
</template>
<script>
  import Header from '../components/header'
  import Footer from '../components/footer'
  import {fetchTraceability} from '@/api/api'

  export default {
    data() {
      return {
        resultList: []
      }
    },
    components: {
      Header,
      Footer
    },
    // computed: {
    //     reverseResultList() {
    //         return this.resultList.reverse();
    //     }
    // },
    beforeCreate() {
      fetchTraceability({id: this.$route.params.id}).then(response => {
        this.resultList = response.data.traceability
      });
    }
  }
</script>

<style lang="stylus" scoped>
.result-detail
    .block
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
        background: url("/static/img/type4.png") no-repeat 60px center
pre {
  white-space: pre-wrap;
}
</style>
