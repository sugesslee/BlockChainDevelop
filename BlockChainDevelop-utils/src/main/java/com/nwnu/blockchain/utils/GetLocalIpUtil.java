package com.nwnu.blockchain.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.UUID;

/**
 * get local ip
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 9:02 AM
 * @since 1.0.0
 */
public class GetLocalIpUtil {

	public static String getLocalIp() {
		InetAddress inetAddress = getLocalHostLANAddress();
		if (inetAddress != null) {
			return inetAddress.getHostAddress();
		}
		return null;
	}

	/**
	 * 获取本机ip地址
	 */
	private static InetAddress getLocalHostLANAddress() {
		try {
			InetAddress candidateAddress = null;
			// 遍历所有的网络接口
			for (Enumeration ifAces = NetworkInterface.getNetworkInterfaces(); ifAces.hasMoreElements(); ) {
				NetworkInterface ifAce = (NetworkInterface) ifAces.nextElement();
				// 在所有的接口下再遍历IP
				for (Enumeration inetAddrs = ifAce.getInetAddresses(); inetAddrs.hasMoreElements(); ) {
					InetAddress inetAddr = (InetAddress) inetAddrs.nextElement();
					// 排除loopback类型地址
					if (!inetAddr.isLoopbackAddress()) {
						if (inetAddr.isSiteLocalAddress()) {
							// 如果是site-local地址，就是它了
							return inetAddr;
						} else if (candidateAddress == null) {
							// site-local类型的地址未被发现，先记录候选地址
							candidateAddress = inetAddr;
						}
					}
				}
			}
			if (candidateAddress != null) {
				return candidateAddress;
			}
			// 如果没有发现 non-loopback地址.只能用最次选的方案
			return InetAddress.getLocalHost();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
