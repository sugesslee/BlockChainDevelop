package com.nwnu.blockchain.tree;

import com.nwnu.blockchain.utils.SHAUtil;

/**
 * TreeNode
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/15     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/15 11:10 AM
 * @since 1.0.0
 */
public class TreeNode {
	// 二叉树的左孩子
	private TreeNode left;
	// 二叉树的右孩子
	private TreeNode right;
	// 二叉树中孩子节点的数据
	private String data;
	// 二叉树中孩子节点的数据对应的hash值，此处采用SHA256处理
	private String hash;
	// 节点名称
	private String name;


	// 构造函数1
	public TreeNode() {

	}

	// 构造函数2
	public TreeNode(String data) {
		this.data = data;
		this.hash = SHAUtil.sha256BasedHuTool(data);
		this.name = "节点：" + data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public TreeNode getRight() {
		return right;
	}

	public void setRight(TreeNode right) {
		this.right = right;
	}

	public TreeNode getLeft() {
		return left;
	}

	public void setLeft(TreeNode left) {
		this.left = left;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}
}
