package com.nwnu.blockchain.core.model;


import com.nwnu.blockchain.utils.CommonUtil;

import javax.persistence.*;

/**
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 8:35 AM
 * @since 1.0.0
 */
@Entity
@Table(name = "sync")
public class SyncEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/**
	 * 已同步的区块hash
	 */
	private String hash;
	/**
	 * 创建时间
	 */
	private Long createTime = CommonUtil.getNow();

	@Override
	public String toString() {
		return "AsyncEntity{" +
				"id=" + id +
				", hash='" + hash + '\'' +
				", createTime=" + createTime +
				'}';
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
}
