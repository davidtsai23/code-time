package netty.tcp

import io.netty.channel.{ChannelHandlerContext, ChannelInboundHandlerAdapter}


class NettyTcpClientHandler extends  ChannelInboundHandlerAdapter{

  override def channelActive(ctx: ChannelHandlerContext): Unit = {
      //与服务端建立好连接可以在此进行消息发送

      ctx.writeAndFlush("Hello Server,I'm client!")
  }

  override def channelRead(ctx: ChannelHandlerContext, msg: Any): Unit = {
      //读取服务端发来的消息
      println("收到服务端的消息："+msg)
  }
}
