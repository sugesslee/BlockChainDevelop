package com.nwnu.blockchain.web.controller;

import com.nwnu.blockchain.common.exception.TrustSDKException;
import com.nwnu.blockchain.core.bean.BaseData;
import com.nwnu.blockchain.core.bean.ResultGenerator;
import com.nwnu.blockchain.service.PairKeyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * PairKeyController
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 8:09 PM
 * @since 1.0.0
 */
@RestController
@RequestMapping("/pairKey")
public class PairKeyController {
	private final PairKeyService pairKeyService;

	public PairKeyController(PairKeyService pairKeyService) {
		this.pairKeyService = pairKeyService;
	}

	/**
	 * 生成公钥私钥
	 */
	@GetMapping("/random")
	public BaseData generate() throws TrustSDKException {
		return ResultGenerator.genSuccessResult(pairKeyService.generate());
	}
}
