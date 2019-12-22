package com.client.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.client.codec.FakeMessageCodec;
import com.client.handler.FakeMessageInboundHandler;
import com.client.handler.FakeMessageOutboundHandler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

@Component
class FakeMessageSendProgramInitializer extends ChannelInitializer<Channel> {
	
	@Autowired
	private FakeMessageCodec messageCodec;
	
	@Autowired
	private FakeMessageInboundHandler messageInboundHandler;
	
	@Autowired
	private FakeMessageOutboundHandler messageOutboundHandler;

	@Override
	protected void initChannel(Channel channel) {
		ChannelPipeline pipeline = channel.pipeline();
		try {
			pipeline.addLast(new LoggingHandler(LogLevel.INFO));
			pipeline.addLast(messageCodec);
			pipeline.addLast(messageOutboundHandler);
			pipeline.addLast(messageInboundHandler);
			
		}
		catch(NullPointerException e) {
			e.printStackTrace();
		}
	}

}
