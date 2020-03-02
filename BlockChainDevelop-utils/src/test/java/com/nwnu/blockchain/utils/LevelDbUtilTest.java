//package com.nwnu.blockchain.utils;
//
//import static org.fusesource.leveldbjni.JniDBFactory.factory;
//
//import org.iq80.leveldb.DB;
//import org.iq80.leveldb.Options;
//import org.junit.Test;
//
//import java.io.File;
//import java.io.IOException;
//
///**
// * <pre>
// *  Version         Date            Author          Description
// * ------------------------------------------------------------
// *  1.0.0           2019/09/16     red        -
// * </pre>
// *
// * @author red
// * @version 1.0.0 2019/9/16 11:25 AM
// * @since 1.0.0
// */
//public class LevelDbUtilTest {
//	@Test
//	public void test() throws IOException {
//		Options options = new Options();
//		options.createIfMissing(true);
//		DB db = factory.open(new File("/tmp/lvltest"), options);
//		try {
//			for (int i = 0; i < 1000000; i++) {
//				byte[] key = new String("key" + i).getBytes();
//				byte[] value = new String("value" + i).getBytes();
//				db.put(key, value);
//			}
//			for (int i = 0; i < 1000000; i++) {
//				byte[] key = new String("key" + i).getBytes();
//				byte[] value = db.get(key);
//				String targetValue = "value" + i;
//				if (!new String(value).equals(targetValue)) {
//					System.out.println("something wrong!");
//				}
//			}
//			for (int i = 0; i < 1000000; i++) {
//				byte[] key = new String("key" + i).getBytes();
//				db.delete(key);
//			}
//		} finally {
//			db.close();
//		}
//	}
//}
