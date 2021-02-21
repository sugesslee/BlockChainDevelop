// import Mock from 'mockjs';
import trs from './json/trs'
import trsDetails from './json/trsDetails'

// const Random = Mock.Random;
//
// const List = [];
// const count = 10;
//
// for (let i = 0; i < count; i++) {
//   List.push(Mock.mock({
//     type: '资产发行',
//     time: Random.date('yyyy-MM-dd HH:mm:ss'),
//     id: Random.string(64),
//     send: Random.string(34),
//     receive: Random.cname(),
//     mount: Random.integer(60, 100)
//   }));
// }

export default {
  fetchTransactions: () => {
    return trs;
  },
  fetchTransaction: (query) => {
    return trsDetails;
  }
};
