package com.pulsar.netty.server;

import java.nio.charset.Charset;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		// 基于换行符号
		ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
		// 解码转String，注意调整自己的编码格式GBK、UTF-8
		ch.pipeline().addLast(new StringDecoder(Charset.forName("GBK")));
		// 编码转String，注意调整自己的编码格式GBK、UTF-8
		ch.pipeline().addLast(new StringEncoder(Charset.forName("GBK")));
		// 在管道中添加我们自己的接收数据实现方法
		ch.pipeline().addLast(new StringHandler());

	}

}
