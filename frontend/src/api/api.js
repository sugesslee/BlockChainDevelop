import request from '@/utils/request';

/**
 * 获取节点信息
 */
export function fetchNodeInfo() {
  return request({
    url: '/blockChain/nodeInfo',
    method: 'get'
  });
}

/**
 * 获取区块列表
 * @param query
 */
export function fetchBlocks(query) {
  return request({
    url: '/blockChain/blocks',
    method: 'get',
    params: query
  });
}

/**
 * 获取区块信息
 * @param query
 */
export function fetchBlockInfo(query) {
  return request({
    url: '/blockChain/blockInfo',
    method: 'get',
    params: query
  });
}

/**
 * 获取交易列表
 * @param query
 */
export function fetchTransactions(query) {
  return request({
    url: '/blockChain/transactions/',
    method: 'get',
    params: query
  });
}

/**
 * 获取交易详情
 * @param query
 */
export function fetchTransaction(query) {
  return request({
    url: '/blockChain/transactionsDetails',
    method: 'get',
    params: query
  });
}

/**
 * 获取项目列表
 * @param query
 */
export function fetchProjectList(query) {
  return request({
    url: '/blockChain/projectList',
    method: 'get',
    params: query
  })
}

/**
 * 获取项目详情
 * @param query
 */
export function fetchProjectDetails(query) {
  return request({
    url: '/blockChain/projectDetails',
    method: 'get',
    params: query
  })
}

/**
 * 获取项目详情
 * @param query
 */
export function fetchAddressDetails(query) {
  return request({
    url: '/blockChain/addressDetails',
    method: 'get',
    params: query
  })
}

/**
 * 查询功能
 * @param query
 */
export function fetchSearch(query) {
  return request({
    url: '/blockChain/search',
    method: 'get',
    params: query
  })
}
