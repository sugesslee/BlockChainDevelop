package com.nwnu.blockchain.protobuf;

import static org.junit.Assert.*;

import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.Test;

/**
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/11     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/11 4:48 PM
 * @since 1.0.0
 */
public class GpsDataProtoTest {
	@Test
	public void testProtobuf() {
		System.out.println("===== 构建一个GPS模型开始 =====");
		gps_data.Builder gps_builder = gps_data.newBuilder();
		gps_builder.setAltitude(1);
		gps_builder.setDataTime("2017-12-17 16:21:44");
		gps_builder.setGpsStatus(1);
		gps_builder.setLat(39.123);
		gps_builder.setLon(120.112);
		gps_builder.setDirection(30.2F);
		gps_builder.setId(100L);

		gps_data gps_data = gps_builder.build();
		System.out.println(gps_data.toString());
		System.out.println("===== 构建GPS模型结束 =====");

		System.out.println("===== gps Byte 开始=====");
		for(byte b : gps_data.toByteArray()){
			System.out.print(b);
		}
		System.out.println("\n" + "bytes长度" + gps_data.toByteString().size());
		System.out.println("===== gps Byte 结束 =====");

		System.out.println("===== 使用gps 反序列化生成对象开始 =====");
		gps_data gd = null;
		try {
			gd = gps_data.parseFrom(gps_data.toByteArray());
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
		System.out.print(gd.toString());
		System.out.println("===== 使用gps 反序列化生成对象结束 =====");
	}
}