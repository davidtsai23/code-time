package com.jfs.netty.udp

import io.netty.channel.{ChannelHandlerContext, ChannelInboundHandlerAdapter, SimpleChannelInboundHandler}

class NettyUdpServerHandler extends ChannelInboundHandlerAdapter{

  override def channelRead(ctx: ChannelHandlerContext, msg: Any): Unit = {
      println(msg)
  }
}
