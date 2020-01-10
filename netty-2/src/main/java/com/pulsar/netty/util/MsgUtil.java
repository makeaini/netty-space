package com.pulsar.netty.util;

import com.pulsar.netty.domain.MsgBody;

public class MsgUtil {
	public static MsgBody buildMsg(String channelId, String msgInfo) {
		MsgBody.Builder msg = MsgBody.newBuilder();
		msg.setChannelId(channelId);
		msg.setMsgInfo(msgInfo);
		return msg.build();
	}
}
