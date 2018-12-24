package netty.tcp

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.channel.{ChannelInitializer, ChannelOption}
import io.netty.handler.codec.string.{StringDecoder, StringEncoder}

class NettyTcpServer {

  def bind(host:String,port:Int): Unit = {
      //管理服务器接收客户端连接
      val bossGroup = new NioEventLoopGroup
      //管理socket通道的读写操作
      val workerGroup = new NioEventLoopGroup

      try {
        //netty启动服务工具
        val bootStrap = new ServerBootstrap
        //
        bootStrap.group(bossGroup, workerGroup)
          //创建socket通道
          .channel(classOf[NioServerSocketChannel])
          .option(ChannelOption.SO_BACKLOG, Integer.valueOf(1024)) //设置缓存大小
          .childOption[java.lang.Boolean](ChannelOption.SO_KEEPALIVE, true) //维持链接活跃清除死连接
          .childOption[java.lang.Boolean](ChannelOption.TCP_NODELAY, true) //去除延迟发送
          .childHandler(new ChannelInitializer[SocketChannel] {
          override def initChannel(c: SocketChannel): Unit = {
            c.pipeline().addLast("mydecoder",new StringDecoder())
            c.pipeline().addLast(new StringEncoder())
            c.pipeline().addLast(new NettyTcpServerHandler)
          }
        })

        //服务启动开始监听端口连接
        val channelFuture = bootStrap.bind(host, port).sync()
        println("服务已启动开始监听port：" + port)
        channelFuture.channel().closeFuture().sync()
      }catch {
        case ex:InterruptedException =>{
              ex.printStackTrace()
        }
      } finally {
            bossGroup.shutdownGracefully()
            workerGroup.shutdownGracefully()
      }
  }

}

object NettyTcpServer{
  def main(args: Array[String]): Unit = {
      new NettyTcpServer().bind("127.0.0.1",8900)
  }
}
