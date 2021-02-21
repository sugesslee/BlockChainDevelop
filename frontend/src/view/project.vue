<template>
  <div class="project">
    <Header :navItem="'数字资产'"></Header>
    <div class="container">
      <div class="head">全部项目</div>
      <div class="project-wrapper">
        <img src="static/img/name1.png">
        <ul>
          <li v-for="(item,index) in projectList" :class="{right:(index+1)%3}" :key="index">
            <router-link :to="{name:'ProjectDetail',params: { id: item.projectId }}">
              <div class="pic" :style="{'backgroundImage':'url('+getImages(item.images)+')'}">
                <!--<img v-bind:src="getImages(item.images)"/>-->
              </div>
              <h2>{{item.title}}</h2>
              <div class="info">
                <span class="target">目标金额</span>
                <span class="mount">{{item.fundGoal}}</span>
              </div>
            </router-link>
          </li>
          <div class="clearfix"></div>
        </ul>
        <div class="num"><span>项目数量</span>{{projectTotal}}</div>
      </div>
    </div>
    <div class="num-page">
      <el-pagination
        background
        layout="prev, pager, next"
        :total="projectTotal"
        :page-size="10"
        :prev-text="'上一页'"
        :next-text="'下一页'"
        @current-change="handleCurrentChange">
      </el-pagination>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
  import Header from '../components/header'
  import Footer from '../components/footer'
  import {Pagination} from 'element-ui'
  import {fetchProjectList} from '@/api/api'

  export default {
    data() {
      return {
        projectList: [],
        projectTotal: 0
      }
    },
    filters: {},
    methods: {
      getImages: function (array) {
        return array[0]
      },
      //分页
      handleCurrentChange(val) {
        let query = {
          page: val
        };
        fetchProjectList(query).then(res => {
          this.projectList = res.data.data.rows;
          this.projectTotal = res.data.data.total;
        })
      }
    },
    created() {
      //查询项目列表
      fetchProjectList().then(res => {
        this.projectList = res.data.data.rows;
        this.projectTotal = res.data.data.total;
      })
    },
    components: {
      Header,
      Footer,
      elPagination:
      Pagination
    }
  }
</script>

<style lang="stylus" scoped>
  .project
    .container
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
        background: url("../../static/img/type9.png") no-repeat 60px center
      .project-wrapper
        position: relative
        padding: 0 60px
        img
          margin: 30px 0
        ul
          li
            width: 320px
            border: 1px solid rgba(0, 0, 0, 0.05)
            box-sizing: border-box
            float: left
            &.right
              margin-right: 60px
            .pic
              width: 320px
              height: 240px
              background-position: center
              background-size: cover
              background-repeat: no-repeat
              img
                margin: 0
                display: block
                width: 100%
                height 100%
            h2
              font-size: 18px
              font-family: PingFangSC-Medium
              color: rgba(51, 51, 51, 1)
              line-height: 26px
              padding: 14px 20px
            .info
              display: flex
              padding: 14px 20px
              .target
                flex: 1
                font-size: 16px
                font-family: PingFangSC-Regular
                color: rgba(153, 153, 153, 1)
                text-align: left
              .mount
                flex: 1
                font-size: 16px
                font-family: dinpro
                color: rgba(254, 49, 128, 1)
                text-align: right
        .num
          position: absolute
          top: 30px
          right: 60px
          height: 38px
          line-height: 38px
          font-size: 14px
          font-family: PingFangSC-Regular
          color: rgba(254, 49, 128, 1)
          span
            font-size: 14px
            font-family: PingFangSC-Regular
            color: rgba(102, 102, 102, 1)
            margin-right: 10px
    .num-page
      text-align: center
      margin-top: 40px
</style>
