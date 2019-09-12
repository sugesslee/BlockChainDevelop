package com.nwnu.blockchain.gossip.core;

import com.nwnu.blockchain.gossip.model.SeedMember;
import com.nwnu.blockchain.gossip.net.MsgService;
import com.nwnu.blockchain.gossip.net.udp.UDPMsgService;

import java.util.ArrayList;
import java.util.List;

/**
 * GossipSettings
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/12     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/12 10:07 AM
 * @since 1.0.0
 */
public class GossipSettings {
	//Time between gossip ping in ms. Default is 1 second
	private int gossipInterval = 1000;

	//Network delay in ms. Default is 200ms
	private int networkDelay = 200;

	//Which message sync implementation. Default is UDPMsgService.class
	private MsgService msgService = new UDPMsgService();

	//Delete the deadth node when the sync message is not received more than [deleteThreshold] times
	private int deleteThreshold = 3;

	private List<SeedMember> seedMembers;

	public int getGossipInterval() {
		return gossipInterval;
	}

	public void setGossipInterval(int gossipInterval) {
		this.gossipInterval = gossipInterval;
	}

	public int getNetworkDelay() {
		return networkDelay;
	}

	public void setNetworkDelay(int networkDelay) {
		this.networkDelay = networkDelay;
	}

	public List<SeedMember> getSeedMembers() {
		return seedMembers;
	}

	public void setSeedMembers(List<SeedMember> seedMembers) {
		List<SeedMember> _seedMembers = new ArrayList<>();
		if(seedMembers != null && !seedMembers.isEmpty()){
			for(SeedMember seed : seedMembers){
				if(!seed.eigenvalue().equalsIgnoreCase(GossipManager.getInstance().getSelf().eigenvalue())){
					if(!_seedMembers.contains(seed)){
						_seedMembers.add(seed);
					}
				}
			}
		}
		this.seedMembers = seedMembers;
	}

	public MsgService getMsgService() {
		return msgService;
	}

	public void setMsgService(MsgService msgService) {
		this.msgService = msgService;
	}

	public int getDeleteThreshold() {
		return deleteThreshold;
	}

	public void setDeleteThreshold(int deleteThreshold) {
		this.deleteThreshold = deleteThreshold;
	}
}
