package com.nwnu.blockchain.web.controller;

import com.nwnu.blockchain.core.bean.BaseData;
import com.nwnu.blockchain.core.bean.ResultGenerator;
import com.nwnu.blockchain.core.requestbody.TransactionBody;
import com.nwnu.blockchain.service.TransactionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TransactionController
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 8:08 PM
 * @since 1.0.0
 */
@RestController
@RequestMapping("/transaction")
public class TransactionController {
	private final TransactionService transactionService;

	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	/**
	 * 构建一条交易，传入各必要参数
	 *
	 * @param transactionBody transactionBody
	 * @return 用私钥签名后的交易
	 */
	@PostMapping
	public BaseData build(@RequestBody TransactionBody transactionBody) throws Exception {
		if (!transactionService.checkKeyPair(transactionBody)) {
			return ResultGenerator.genFailResult("公私钥不是一对");
		}
		if (!transactionService.checkContent(transactionBody)) {
			return ResultGenerator.genFailResult("操作需要有id和json内容");
		}
		return ResultGenerator.genSuccessResult(transactionService.build(transactionBody));
	}
}
