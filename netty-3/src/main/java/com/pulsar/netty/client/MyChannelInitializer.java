package com.pulsar.netty.client;

import com.pulsar.netty.serialization.ObjDecoder;
import com.pulsar.netty.serialization.ObjEncoder;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel channel) throws Exception {
		channel.pipeline().addLast(new IdleStateHandler(0, 4, 0));
		channel.pipeline().addLast(new ObjDecoder());
		channel.pipeline().addLast(new ObjEncoder());
		// 在管道中添加我们自己的接收数据实现方法
		channel.pipeline().addLast(new MyClientHandler());
	}

}
