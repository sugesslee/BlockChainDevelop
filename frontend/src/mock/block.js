import Mock from 'mockjs';
import block from './json/blocks'
import blockInfo from './json/blockInfo'

const List = [];
const count = 10;

for (let i = 0; i < count; i++) {
  List.push(Mock.mock({
    height: Mock.Random.integer(5, 1000),
    time: Mock.Random.date('yyyy-MM-dd HH:mm:ss'),
    blockAddr: Mock.Random.string(34),
    product: Mock.Random.string(34),
    num: Mock.Random.integer(60, 100)
  }));
}

export default {
  getBlocks: () => {
    return block;
  },
  getBlockInfo: () => {
    return blockInfo;
  }
};
