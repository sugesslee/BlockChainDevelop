package com.nwnu.blockchain.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * MerkleTreesUtil
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/10/08     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/10/8 4:29 PM
 * @since 1.0.0
 */
public class MerkleTrees {
	// translation list
	private List<String> txList;
	// merkle root
	private String root;

	/**
	 * @param txList translation list
	 */
	public MerkleTrees(List<String> txList) {
		this.txList = txList;
		this.root = "";
	}

	/**
	 * execute merkle tree and set root
	 */
	public void merkle_tree() {

		List<String> tempTxList = new ArrayList<>(this.txList);

		List<String> newTxList = getNewTxList(tempTxList);

		while (newTxList.size() != 1) {
			newTxList = getNewTxList(newTxList);
		}

		this.root = newTxList.get(0);
	}

	/**
	 * return node hash list
	 *
	 * @param tempTxList translate list
	 * @return hash list
	 */
	private List<String> getNewTxList(List<String> tempTxList) {
		List<String> newTxList = new ArrayList<>();
		int index = 0;

		while (index < tempTxList.size()) {
			// left
			String left = tempTxList.get(index);
			index++;
			//right
			String right = "";
			if (index != tempTxList.size()) {
				right = tempTxList.get(index);
			}
			// sha2 hex value
			String sha2HexValue = getSHA2HexValue(left + right);
			newTxList.add(sha2HexValue);
			index++;
		}
		return newTxList;
	}

	/**
	 * hex String
	 *
	 * @param str str
	 * @return str
	 */
	private String getSHA2HexValue(String str) {
		byte[] cipher_byte;

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(str.getBytes());
			cipher_byte = md.digest();

			StringBuilder sb = new StringBuilder(2 * cipher_byte.length);

			for (byte b : cipher_byte) {
				sb.append(String.format("%02x", b & 0xff));
			}

			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * get root node
	 *
	 * @return root
	 */
	public String getRoot() {
		return this.root;
	}
}
