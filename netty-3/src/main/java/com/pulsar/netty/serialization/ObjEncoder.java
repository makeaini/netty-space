package com.pulsar.netty.serialization;

import com.pulsar.netty.domain.MsgInfo;
import com.pulsar.netty.util.SerializationUtil;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class ObjEncoder extends MessageToByteEncoder<MsgInfo> {
	@Override
	protected void encode(ChannelHandlerContext ctx, MsgInfo msg, ByteBuf out) throws Exception {
		byte[] data = SerializationUtil.serialize(msg);
		out.writeInt(data.length);
		out.writeBytes(data);
	}

}
