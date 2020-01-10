package com.pulsar.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
	public static void main(String[] args) {
		new NettyClient().connect("127.0.0.1", 7397);
	}

	private void connect(String inetHost, int inetPort) {
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(workerGroup);
			b.channel(NioSocketChannel.class);
			b.option(ChannelOption.AUTO_READ, true);
			b.handler(new MyChannelInitializer());
			ChannelFuture f = b.connect(inetHost, inetPort).sync();
			f.channel().writeAndFlush(
					"你好，SpringBoot启动的netty服务端，“我的结尾是一个换行符，用于传输半包粘包处理”\r\n");
			f.channel().writeAndFlush(
					"你好，SpringBoot启动的netty服务端，“我的结尾是一个换行符，用于传输半包粘包处理”\r\n");
			f.channel().writeAndFlush(
					"你好，SpringBoot启动的netty服务端，“我的结尾是一个换行符，用于传输半包粘包处理”\r\n");
			f.channel().writeAndFlush(
					"你好，SpringBoot启动的netty服务端，“我的结尾是一个换行符，用于传输半包粘包处理”\r\n");
			f.channel().writeAndFlush(
					"你好，SpringBoot启动的netty服务端，“我的结尾是一个换行符，用于传输半包粘包处理”\r\n");
			f.channel().closeFuture().syncUninterruptibly();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			workerGroup.shutdownGracefully();
		}
	}
}
