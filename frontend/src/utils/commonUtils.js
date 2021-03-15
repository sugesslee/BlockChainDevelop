export function defaultValue(value) {
  if (!value) {
    return '----';
  } else {
    return value
  }
}

const trsType = {
  0: {
    name: '零售商交易',
    class: 'blue'
  },
  1: {
    name: '药材生产',
    class: 'red'
  },
  2: {
    name: '加工生产',
    class: 'color_delegate'
  },
  3: {
    name: '物流流通',
    class: 'color_vote'
  },
  4: {
    name: '药材生产',
    class: 'red'
  },
  5: {
    name: '药材生产',
    class: 'red'
  },
  6: {
    name: '药材生产',
    class: 'red'
  },
  7: {
    name: '药材生产',
    class: 'red'
  },
  8: {
    name: '药材生产',
    class: 'red'
  },
  9: {
    name: '收购信息',
    class: 'color_uia_issuer'
  },
  10: {
    name: '加工生产',
    class: 'color_uia_asset'
  },
  11: {
    name: '加工生产',
    class: 'color_uia_asset'
  },
  12: {
    name: '加工生产',
    class: 'color_uia_asset'
  },
  13: {
    name: '销售信息',
    class: 'color_uia_transfer'
  },
  14: {
    name: '销售信息',
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

