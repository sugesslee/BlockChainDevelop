package com.nwnu.blockchain.util;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.ArrayUtils;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * byte util
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/10/08     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/10/8 7:31 PM
 * @since 1.0.0
 */
public class ByteUtil {
	public static final byte[] EMPTY_ARRAY = new byte[0];

	private static final byte[] EMPTY_BYTES = new byte[32];

	public static final String ZERO_HASH = Hex.encodeHexString(EMPTY_BYTES);

	/**
	 * 将多个字节数组合并成一个字节数组
	 *
	 * @param bytes bytes
	 * @return bytes
	 */
	public static byte[] merge(byte[]... bytes) {
		Stream<Byte> stream = Stream.of();
		for (byte[] b : bytes) {
			stream = Stream.concat(stream, Arrays.stream(ArrayUtils.toObject(b)));
		}
		return ArrayUtils.toPrimitive(stream.toArray(Byte[]::new));
	}

	/**
	 * long 类型转 byte[]
	 *
	 * @param val val
	 * @return byte
	 */
	public static byte[] toBytes(long val) {
		return ByteBuffer.allocate(Long.BYTES).putLong(val).array();
	}

}
