package com.pulsar.netty.domain;

import java.io.Serializable;

public class MsgInfo implements Serializable{
	private static final long serialVersionUID = -4674729696246265914L;
	private String channelId;
	private Integer messageType = 1;
	private String msgContent;
	

	public MsgInfo(String channelId, Integer messageType) {
		super();
		this.channelId = channelId;
		this.messageType = messageType;
	}

	public Integer getMessageType() {
		return messageType;
	}

	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	public MsgInfo() {
	}

	public MsgInfo(String channelId, String msgContent) {
		this.channelId = channelId;
		this.msgContent = msgContent;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	@Override
	public String toString() {
		return "MsgInfo [channelId=" + channelId + ", msgContent=" + msgContent + "]";
	}
	
}
