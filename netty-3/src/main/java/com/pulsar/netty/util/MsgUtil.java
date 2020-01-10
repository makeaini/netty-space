package com.pulsar.netty.util;

import com.pulsar.netty.domain.MsgInfo;

public class MsgUtil {
	public static MsgInfo buildMsg(String channelId, String msgInfo) {
		MsgInfo msg = new MsgInfo(channelId, msgInfo);
		return msg;
	}
	public static MsgInfo buildMsg(String channelId,Integer messageType) {
		MsgInfo msg = new MsgInfo(channelId, messageType);
		return msg;
	}
}
