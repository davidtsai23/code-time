package netty.tcp

import io.netty.channel.{ChannelHandlerContext, ChannelInboundHandlerAdapter}

class NettyTcpServerHandler extends ChannelInboundHandlerAdapter{


  override def channelActive(ctx: ChannelHandlerContext): Unit = {
      println("建立了客户端连接，通道处于工作状态")
  }

  override def channelRead(ctx: ChannelHandlerContext, msg: Any): Unit = {
      println("接收客户端的信息："+msg)
      ctx.writeAndFlush("success")
  }
}
