package com.pulsar.netty.client;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.pulsar.netty.domain.MsgInfo;
import com.pulsar.netty.util.MsgUtil;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class MyClientHandler extends ChannelInboundHandlerAdapter {
	private int count = 1;

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		SocketChannel channel = (SocketChannel) ctx.channel();
		String str = "通知服务端链接建立成功" + " " + new Date() + " " + channel.localAddress().getHostString();
		ctx.writeAndFlush(MsgUtil.buildMsg(channel.id().toString(), str));
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("断开链接" + ctx.channel().localAddress().toString());
        ctx.close(); 
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println((MsgInfo) msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
		System.out.println("异常信息：\r\n" + cause.getMessage());
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt instanceof IdleStateEvent) {
			IdleState state = ((IdleStateEvent) evt).state();
			if (state == IdleState.WRITER_IDLE) {
				if (count <= 3) {
					ctx.writeAndFlush(MsgUtil.buildMsg(ctx.channel().id().toString(), 2));
					count++;
				}
			}
		} else {
			super.userEventTriggered(ctx, evt);
		}
	}

}
