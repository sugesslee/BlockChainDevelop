import Mock from 'mockjs';
import nodeInfoApi from './nodeInfo';
import blocksApi from './block';
import trsApi from './trs';
import project from './project'

// Mock.setup({
//   timeout: '350-600'
// });

Mock.mock(/\/blockChain\/nodeInfo/, 'get', nodeInfoApi.getNodeInfo);
Mock.mock(/\/blockChain\/blocks/, 'get', blocksApi.getBlocks);
Mock.mock(/\/blockChain\/blockInfo/, 'get', blocksApi.getBlockInfo());
Mock.mock(/\/blockChain\/transactions\//, 'get', trsApi.fetchTransactions);
Mock.mock(/\/blockChain\/transactionsDetails/, 'get', trsApi.fetchTransaction);
Mock.mock(/\/blockChain\/projectList/, 'get', project.fetchProjectList);
Mock.mock(/\/blockChain\/projectDetails/, 'get', project.fetchProjectDetails);
Mock.mock(/\/blockChain\/addressDetails/, 'get', project.fetchAddressDetails());
Mock.mock(/\/blockChain\/search/, 'get', project.fetchSearch());

export default Mock;
