package com.nwnu.blockchain.service;

import com.nwnu.blockchain.common.PairKey;
import com.nwnu.blockchain.common.exception.TrustSDKException;
import com.nwnu.blockchain.repository.utils.TrustSDKUtil;
import org.springframework.stereotype.Service;

/**
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 2:39 PM
 * @since 1.0.0
 */
@Service
public class PairKeyService {

	/**
	 * 生成公私钥对
	 *
	 * @return PairKey
	 * @throws TrustSDKException TrustSDKException
	 */
	public PairKey generate() throws TrustSDKException {
		return TrustSDKUtil.generatePairKey(true);
	}
}
