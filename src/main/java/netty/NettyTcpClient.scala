package netty

import io.netty.bootstrap.Bootstrap
import io.netty.channel.ChannelInitializer
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.{NioServerSocketChannel, NioSocketChannel}
import io.netty.handler.codec.string.{StringDecoder, StringEncoder}

class NettyTcpClient {

    def connect(host:String,port:Int): Unit ={
        val worker = new NioEventLoopGroup()
        try{
            val bootStrap = new Bootstrap()
            bootStrap
              .group(worker)
              .channel(classOf[NioSocketChannel])
              .handler(new ChannelInitializer[SocketChannel] {
                override def initChannel(c: SocketChannel): Unit = {
                    c.pipeline().addLast(new StringDecoder())
                    c.pipeline().addLast(new StringEncoder())
                    c.pipeline().addLast(new NettyTcpClientHandler)
                }
              })
            //开始与服务端建立连接
            val future = bootStrap.connect(host,port).sync()
            //等待服务端关闭
            future.channel().closeFuture().sync()
        } finally {
            worker.shutdownGracefully()
        }
    }
}

object NettyTcpClient{
  def main(args: Array[String]): Unit = {
      new NettyTcpClient().connect("127.0.0.1",8900)
  }
}
