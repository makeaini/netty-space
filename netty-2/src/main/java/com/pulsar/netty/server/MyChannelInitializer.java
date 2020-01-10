package com.pulsar.netty.server;

import com.pulsar.netty.domain.MsgBody;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		// protobuf 处理
		ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
		ch.pipeline().addLast(new ProtobufDecoder(MsgBody.getDefaultInstance()));
		ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
		ch.pipeline().addLast(new ProtobufEncoder());
		// 在管道中添加我们自己的接收数据实现方法
		ch.pipeline().addLast(new SeverHandler());

	}

}
