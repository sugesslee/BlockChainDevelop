//package com.nwnu.blockchain.utils;
//
//import com.google.common.hash.BloomFilter;
//import com.google.common.hash.Funnels;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.HashSet;
//import java.util.Random;
//
///**
// * BloomFilterTest
// * <pre>
// *  Version         Date            Author          Description
// * ------------------------------------------------------------
// *  1.0.0           2019/09/17     red        -
// * </pre>
// *
// * @author red
// * @version 1.0.0 2019/9/17 7:07 PM
// * @since 1.0.0
// */
//public class BloomFilterTest {
//	private static int size = 1000000;
//
//	private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size, 0.0001);
//
//	@Test
//	public void test() {
//		for (int i = 0; i < size; i++) {
//			bloomFilter.put(i);
//		}
//
//		for (int i = 0; i < size; i++) {
//			if (!bloomFilter.mightContain(i)) {
//				System.out.println("有坏人逃脱了");
//			}
//		}
//
//		List<Integer> list = new ArrayList<>(1000);
//		for (int i = size + 10000; i < size + 20000; i++) {
//			if (bloomFilter.mightContain(i)) {
//				list.add(i);
//			}
//		}
//		System.out.println("有误伤的数量：" + list.size());
//	}
//
//
//	static int sizeOfNumberSet = Integer.MAX_VALUE >> 4;
//
//	static Random generator = new Random();
//
//	@Test
//	public void BloomFilter1Test() {
//
//		int error = 0;
//		HashSet<Integer> hashSet = new HashSet<Integer>();
//		BloomFilter<Integer> filter = BloomFilter.create(Funnels.integerFunnel(), sizeOfNumberSet);
//
//		for (int i = 0; i < sizeOfNumberSet; i++) {
//			int number = generator.nextInt();
//			if (filter.mightContain(number) != hashSet.contains(number)) {
//				error++;
//			}
//			filter.put(number);
//			hashSet.add(number);
//		}
//
//		System.out.println("Error count: " + error + ", error rate = " + String
//				.format("%f", (float) error / (float) sizeOfNumberSet));
//	}
//}
