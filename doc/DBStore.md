# 区块存储
```
src/main/java/com/nwnu/blockchain/block/DbBlockGenerator.java
```

## rocksDB
### 存储的数据
1. key_first_block：创世区块
2. key_last_block：当前区块链中最后一个区块
3. key_next_f1b6e8d03dcaaf1e9add5b56197ddf0c551c4614ecb455105dc5ec4cdd7b9a3d: f1...区块的下一个区块
4. f1b6e8d03dcaaf1e9add5b56197ddf0c551c4614ecb455105dc5ec4cdd7b9a3d：区块中的所有内容(交易)

## sqlite
### 存储的数据
1. sync：区块链
2. message：生成区块的通信消息