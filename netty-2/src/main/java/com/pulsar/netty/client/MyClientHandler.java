package com.pulsar.netty.client;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.googlecode.protobuf.format.JsonFormat;
import com.pulsar.netty.domain.MsgBody;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class MyClientHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("断开链接" + ctx.channel().localAddress().toString());
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// 接收msg消息{与上一章节相比，此处已经不需要自己进行解码}
		JsonFormat format = new JsonFormat();
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
				+ " 接收到消息：" + format.printToString((MsgBody) msg));
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
		System.out.println("异常信息：\r\n" + cause.getMessage());
	}

}
