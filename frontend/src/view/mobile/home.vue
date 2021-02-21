<template>
    <div class="home">
      <ul class="stat">
        <li>
          <img src="/static/img/item1.png">
          <div>
            <span class="name">当前区块高度</span>
            <span class="count"><count-to :startVal='0' :endVal=nodeInfo.height :duration='1000'></count-to></span>
          </div>
        </li>
        <li>
          <img src="/static/img/item2.png">
          <div>
            <span class="name">24小时交易数</span>
            <span class="count"><count-to :startVal='0' :endVal=nodeInfo._24Trs :duration='1000'></count-to></span>
          </div>
        </li>
        <li>
          <img src="/static/img/item3.png">
          <div>
            <span class="name">注册用户数</span>
            <span class="count"><count-to :startVal='0' :endVal=nodeInfo.memCount :duration='1000'></count-to></span>
          </div>
        </li>
        <li>
          <img src="/static/img/item4.png">
          <div>
            <span class="name">当前节点数</span>
            <span class="count"><count-to :startVal='0' :endVal=nodeInfo.nodeCount :duration='1000'></count-to></span>
          </div>
        </li>
      </ul>
      <transitionTablePanel :cellValue="transitions"></transitionTablePanel>
      <div class="more">
        <router-link :to="{path: '/m/transition'}">
          <span>查看全部交易</span>
        </router-link>
      </div>
      <blockTablePanel :cellValue="blocks"></blockTablePanel>
      <div class="more">
        <router-link :to="{path: '/m/block'}">
          <span>查看全部区块</span>
        </router-link>
      </div>
    </div>
</template>

<script>
import countTo from 'vue-count-to';
import transitionTablePanel from '../../components/m-transition-table-panel'
import blockTablePanel from '../../components/m-block-table-panel'
import {fetchNodeInfo, fetchBlocks, fetchTransactions} from '@/api/api'
export default {
  name: 'home',
  data(){
    return {
      transitions: [], //最新交易
      nodeInfo: {}, //区块信息
      blocks: [] //最新区块
    }
  },
  beforeCreate() {
    fetchTransactions().then(response => {
      this.transitions = response.data.transactions
    });
    fetchNodeInfo().then(response => {
      this.nodeInfo = response.data.nodeInfo
    });
    fetchBlocks().then(response => {
      this.blocks = response.data.blocks
    });
  },
  components: {
    countTo,
    transitionTablePanel,
    blockTablePanel
  }
}
</script>

<style scoped lang="stylus">
.home
  .stat
    padding: .2rem .2rem 0 .2rem
    border-bottom: 1px solid rgba(0,0,0,.05)
    li
      display: flex
      align-items: center
      margin-bottom: .12rem
      float: left
      width: 1.67rem
      img
        width: .52rem
        margin-right: .04rem
      span
        display: block
        line-height: .3rem
        font-size: .16rem
      .name
        color: #999
      .count
        color: #333
  .stat:after
    content: ''
    display: block
    clear: both
.more
  height: 64px
  line-height: 64px
  text-align: center
  font-size: 14px
  font-family: PingFangSC-Regular, Lantinghei SC
  color: rgba(216, 216, 216, 1)
  span
    padding: 25px
    background: url("/static/img/more.png") no-repeat center right
    cursor: pointer
</style>
