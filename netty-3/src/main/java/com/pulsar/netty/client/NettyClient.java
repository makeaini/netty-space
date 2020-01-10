package com.pulsar.netty.client;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

import com.pulsar.netty.util.MsgUtil;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
	public static ChannelFuture f = null;

	public static void main(String[] args) throws InterruptedException {
		connect("127.0.0.1", 7397);
		for(;;);
	}

	public static void connect(String inetHost, int inetPort) {
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(workerGroup);
			b.channel(NioSocketChannel.class);
			b.option(ChannelOption.AUTO_READ, true);
			b.handler(new MyChannelInitializer());
			f = b.connect(inetHost, inetPort).sync();
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			workerGroup.shutdownGracefully();
		}
	}
}
