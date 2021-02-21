module.exports = {
  //此项是用来告诉eslint找当前配置文件不能往父级查找
  root: true,
  //此项是用来指定eslint解析器的，解析器必须符合规则，babel-eslint解析器是对babel解析器的包装使其与ESLint解析
  parserOptions: {
    parser: 'babel-eslint'
  },
  //此项指定环境的全局变量，下面的配置指定为浏览器环境
  env: {
    browser: true,
  },
  // https://github.com/feross/standard/blob/master/RULES.md#javascript-standard-style
  // 此项是用来配置标准的js风格，就是说写代码的时候要规范的写，如果你使用vs-code我觉得应该可以避免出错
  extends: [
    'plugin:vue/essential',
    'standard'
  ],
  // required to lint *.vue files
  plugins: [
    'vue',
    'vuefix'
  ],
  // 下面这些rules是用来设置从插件来的规范代码的规则，使用必须去掉前缀eslint-plugin-
  // 主要有如下的设置规则，可以设置字符串也可以设置数字，两者效果一致
  // "off" -> 0 关闭规则
  // "warn" -> 1 开启警告规则
  //"error" -> 2 开启错误规则
  rules: {
    // allow async-await
    'generator-star-spacing': 'off',
    "semi": 0,//语句强制分号结尾
    //空行最多不能超过100行
    "no-multiple-empty-lines": [0, {"max": 100}],
    //关闭禁止混用tab和空格
    "no-mixed-spaces-and-tabs": [0],
    "no-inline-comments": 0,//禁止行内备注
    "no-new-func": 1,//禁止使用new Function
    "no-new-object": 2,//禁止使用new Object()
    "no-new-require": 2,//禁止使用new require
    "camelcase": 2,//强制驼峰法命名
    "eqeqeq": 2,//必须使用全等
    "strict": 2,//使用严格模式
    "indent": 0,//缩进风格
    "space-after-keywords": 0,//关键字后面是否要空一格
    "space-before-blocks": 0,//不以新行开始的块{前面要不要有空格
    "space-before-function-paren": 0,//函数定义时括号前面要不要有空格
    "space-in-parens": 0,//小括号里面要不要有空格
    "space-infix-ops": 0,//中缀操作符周围要不要有空格
    "space-unary-ops": 0,//一元运算符的前/后要不要加空格
    "spaced-comment": 0,//注释风格要不要有空格什么的
    'no-debugger': process.env.NODE_ENV === 'production' ? 'error' : 'off'
  }
}
