package com.nwnu.blockchain.web.controller;

import com.nwnu.blockchain.core.bean.BaseData;
import com.nwnu.blockchain.core.bean.ResultGenerator;
import com.nwnu.blockchain.p2p.core.requestbody.InstructionBody;
import com.nwnu.blockchain.p2p.core.service.InstructionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * InstructionController
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
@RequestMapping("/instruction")
public class InstructionController {
	private final InstructionService instructionService;

	public InstructionController(InstructionService instructionService) {
		this.instructionService = instructionService;
	}

	/**
	 * 构建一条指令，传入各必要参数
	 *
	 * @param instructionBody instructionBody
	 * @return 用私钥签名后的指令
	 */
	@PostMapping
	public BaseData build(@RequestBody InstructionBody instructionBody) throws Exception {
		if (!instructionService.checkKeyPair(instructionBody)) {
			return ResultGenerator.genFailResult("公私钥不是一对");
		}
		if (!instructionService.checkContent(instructionBody)) {
			return ResultGenerator.genFailResult("Delete和Update操作需要有id和json内容");
		}
		return ResultGenerator.genSuccessResult(instructionService.build(instructionBody));
	}
}
