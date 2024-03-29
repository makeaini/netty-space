package com.pulsar.netty.server;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.pulsar.netty.util.MsgUtil;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class MySeverHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out
				.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " 接收到消息类型：" + msg.getClass());
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " 接收到消息内容："
				+  JSON.toJSONString(msg));
	}

	/**
	 * 当客户端主动链接服务端的链接后，这个通道就是活跃的了。也就是客户端与服务端建立了通信通道并且可以传输数据
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		SocketChannel channel = (SocketChannel) ctx.channel();
		System.out.println("链接报告开始");
		System.out.println("链接报告信息：有一客户端链接到本服务端");
		System.out.println("链接报告IP:" + channel.localAddress().getHostString());
		System.out.println("链接报告Port:" + channel.localAddress().getPort());
		System.out.println("链接报告完毕");
		String str = "通知客户端链接建立成功" + " " + new Date() + " " + channel.localAddress().getHostString();
		ctx.writeAndFlush(MsgUtil.buildMsg(channel.id().toString(), str));
	}

	/**
	 * 当客户端主动断开服务端的链接后，这个通道就是不活跃的。也就是说客户端与服务端的关闭了通信通道并且不可以传输数据
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("客户端断开链接" + ctx.channel().localAddress().toString());
	}

	/**
	 * 抓住异常，当发生异常的时候，可以做一些相应的处理，比如打印日志、关闭链接
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
		System.out.println("异常信息：\r\n" + cause.getMessage());
	}
	
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt instanceof IdleStateEvent) {
			IdleState state = ((IdleStateEvent) evt).state();
			if (state == IdleState.READER_IDLE) {
			     System.out.println("已经5秒没有接收到客户端的信息了");
				 ctx.channel().close();
				//ctx.writeAndFlush(MsgUtil.buildMsg(ctx.channel().id().toString(), 2));
			}
		} else {
			super.userEventTriggered(ctx, evt);
		}
	}


}
