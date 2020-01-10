package com.pulsar.netty.server.handler;

import java.nio.charset.Charset;
import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class MyServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf buf = (ByteBuf) msg;
		byte[] msgByte = new byte[buf.readableBytes()];
		buf.readBytes(msgByte);
		System.out.print(new Date() + "接收到消息：");
		System.out.println(new String(msgByte, Charset.forName("GBK")));
	}

}
