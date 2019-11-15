package com.nwnu.blockchain.transaction;


import com.google.common.collect.Lists;
import com.nwnu.blockchain.util.ByteUtil;
import lombok.Data;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;

/**
 * MerkleTree
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
@Data
public class MerkleTree {

	/**
	 * root node
	 */
	private Node root;

	/**
	 * leaf node hash
	 */
	private byte[][] leafHashes;

	public MerkleTree(byte[][] leafHashes) {
		constructTree(leafHashes);
	}

	/**
	 * construct merkle tree
	 *
	 * @param leafHashes leaf hash
	 */
	private void constructTree(byte[][] leafHashes) {
		if (leafHashes == null || leafHashes.length < 1) {
			throw new RuntimeException("ERROR: Fail to construct merkle tree!");
		}
		this.leafHashes = leafHashes;
		List<Node> parents = bottomLevel(leafHashes);

		while (parents.size() > 1) {
			parents = internalLevel(parents);
		}
		root = parents.get(0);
	}

	/**
	 * 构建层级节点
	 *
	 * @param children node list
	 * @return node list
	 */
	private List<Node> internalLevel(List<Node> children) {
		List<Node> parents = Lists.newArrayListWithCapacity(children.size() / 2);

		for (int i = 0; i < children.size() - 1; i += 2) {
			Node child1 = children.get(i);
			Node child2 = children.get(i + 1);

			Node parent = constructInternalNode(child1, child2);

			parents.add(parent);
		}

		// 内部结点奇数个，只对left node计算
		if (children.size() % 2 != 0) {
			Node child = children.get(children.size() - 1);
			Node parent = constructInternalNode(child, null);

			parents.add(parent);
		}

		return parents;
	}


	/**
	 * bottom node construct
	 *
	 * @param hashes hash
	 * @return node list
	 */
	private List<Node> bottomLevel(byte[][] hashes) {
		List<Node> parents = Lists.newArrayListWithCapacity(hashes.length / 2);

		for (int i = 0; i < hashes.length - 1; i += 2) {
			Node leaf1 = constructLeafNode(hashes[i]);
			Node leaf2 = constructLeafNode(hashes[i + 1]);

			Node parent = constructInternalNode(leaf1, leaf2);
			parents.add(parent);
		}

		if (hashes.length % 2 != 0) {
			Node leaf = constructLeafNode(hashes[hashes.length - 1]);
			// 奇数个节点复制最后一个节点
			Node parent = constructInternalNode(leaf, leaf);

			parents.add(parent);
		}

		return parents;
	}

	/**
	 * construct leaf node
	 *
	 * @param hash hash
	 * @return node
	 */
	private static Node constructLeafNode(byte[] hash) {
		Node leaf = new Node();
		leaf.hash = hash;

		return leaf;
	}

	/**
	 * 构建内部节点
	 *
	 * @param leftChild  left children
	 * @param rightChild right children
	 * @return node
	 */
	private Node constructInternalNode(Node leftChild, Node rightChild) {
		Node parent = new Node();

		if (rightChild == null) {
			parent.hash = leftChild.hash;
		} else {
			parent.hash = internalHash(leftChild.hash, rightChild.hash);
		}

		parent.left = leftChild;
		parent.right = rightChild;

		return parent;
	}


	private byte[] internalHash(byte[] leftChildHash, byte[] rightChildHash) {
		byte[] mergedBytes = ByteUtil.merge(leftChildHash, rightChildHash);

		return DigestUtils.sha256(mergedBytes);
	}

	/**
	 * merkle node
	 */
	@Data
	public static class Node {
		private byte[] hash;
		private Node left;
		private Node right;
	}
}
