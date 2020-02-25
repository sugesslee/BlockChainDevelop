# 数据交易格式

## 货物交易记录
- 销售方用户ID Seller User ID
- 收购方用户ID Acquirer User ID
- 交易批次 Transaction lot
- 数量 Quantity
- 货物标记(二维码) Cargo marking (QR code)
- 交易时间 transaction time
```json
[{
      "hash" : "a424242df2cb398021ffdfac83f46daeffd720721d26b42379b4353b21c0db5c",
      "transactionId" : "45c7441a-e1b0-4a94-8cdd-a330730e0041",
      "json" : "{\"content\":\"1\"}",
      "oldJson" : "{\"content\":\"1\"}",
      "operation" : 1,
      "publicKey" : "A8WLqHTjcT/FQ2IWhIePNShUEcdCzu5dG+XrQU8OMu54",
      "sign" : "MEQCIETBh3h0rPh07hd3S95U3QlZvj4MLBv1qT/1m+lr9E15AiBnw5c9RH11HYUWUQt1LE3NeiQEFlHWLvdOtp4QT4lFPQ==",
      "table" : "message",
      "timeStamp" : 1574312696390
}]
```
```json
[{
      "json" : "{\"content\":\"1\"}"
}]
```
## 物流记录
- 物流用户ID Logistics user ID
- 货物批次 Cargo batch
- 起运时间、地点 Departure time and place
- 到达时间、地点 Arrival time, place
- 货物数量 Quantity


## 质检记录
- 货物批次 Cargo batch
- 检测时间 Detection time
- 检测机构ID Detection Agency ID
- 检测员 inspector
- 检测结论 Test conclusion