package com.pulsar.netty.client;

import com.pulsar.netty.util.MsgUtil;

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
			f.channel().writeAndFlush(MsgUtil.buildMsg("546546", "lskjdflkjdf"));
			f.channel().closeFuture().syncUninterruptibly();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			workerGroup.shutdownGracefully();
		}
	}
}
