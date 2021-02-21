package com.nwnu.blockchain.utils;


import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/10/07     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/10/7 10:20 AM
 * @since 1.0.0
 */
@Slf4j
public class BitmapTest {
	public Map removeDuplicates(int[] array) {
		int index = 0;
		Map<Integer, Boolean> maps = new LinkedHashMap<Integer, Boolean>();
		for (int num : array) {
			if (!maps.containsValue(num)) {
				array[index] = num;
				index++;
				maps.put(num, true);
			}
		}

		return maps;
	}

	@Test
	public void bitMapTest() {
		Map result = removeDuplicates(new int[]{1, 2, 4, 5, 10, 2});
		Assert.assertEquals(5, result.size());
	}


	public static final int _1MB = 1024 * 1024;
	//每个byte记录8bit信息,也就是8个数是否存在于数组中
	public static byte[] flags = new byte[512 * _1MB];

	public static void setFlags(int num) {
		//使用每个数的低三位作为byte内的映射
		//例如: 255 = 0x11111111
		//低三位(也就是num & (0x07))为0x111 = 7, 则byte的第7位为1, 表示255已存在
		flags[num >> 3] |= 0x01 << (num & (0x07));
	}

	public static boolean getFlags(int num) {
		return (flags[num >> 3] >> (num & (0x07)) & 0x01) == 0x01;
	}

	@Test
	public void BitMap1Test() {
		//待判重数据
		int[] array = {255, 1024, 0, 65536, 255};

		int index = 0;
		for (int num : array) {
			if (!getFlags(num)) {
				//未出现的元素
				array[index] = num;
				index = index + 1;
				//设置标志位
				setFlags(num);
				System.out.println("set " + num);
			} else {
				Assert.assertEquals(255, num);
			}
		}
	}
}
