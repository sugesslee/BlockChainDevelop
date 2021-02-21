export function defaultValue(value) {
  if (!value) {
    return '----';
  } else {
    return value
  }
}

const trsType = {
  0: {
    name: '普通转账',
    class: 'blue'
  },
  1: {
    name: '设置二级密码',
    class: 'red'
  },
  2: {
    name: '注册受托人',
    class: 'color_delegate'
  },
  3: {
    name: '共识投票',
    class: 'color_vote'
  },
  4: {
    name: '普通转账',
    class: 'red'
  },
  5: {
    name: '设置二级密码',
    class: 'red'
  },
  6: {
    name: '注册受托人',
    class: 'red'
  },
  7: {
    name: '注册受托人',
    class: 'red'
  },
  8: {
    name: '普通转账',
    class: 'red'
  },
  9: {
    name: '爱心组织',
    class: 'color_uia_issuer'
  },
  10: {
    name: '项目发起',
    class: 'color_uia_asset'
  },
  11: {
    name: '项目发起',
    class: 'color_uia_asset'
  },
  12: {
    name: '设置二级密码',
    class: 'color_uia_asset'
  },
  13: {
    name: '爱心流向',
    class: 'color_uia_transfer'
  },
  14: {
    name: '爱心流向',
    class: 'color_uia_transfer'
  }
};

//返回交易类型
export function transactionType() {
  return trsType;
}

/**
 * 根据交易类型获取名字
 * @param type 交易类型
 */
export function getNameByTrsType(type) {
  return trsType[type].name;
}

/**
 * 根据交易类型获取class，控制显示颜色
 * @param type 交易类型
 */
export function getClassByTrsType(type) {
  return trsType[type].class;
}

