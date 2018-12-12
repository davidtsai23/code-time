package netty.udp

import io.netty.bootstrap.Bootstrap
import io.netty.buffer.PooledByteBufAllocator
import io.netty.channel.{AdaptiveRecvByteBufAllocator, ChannelInitializer, ChannelOption, ChannelPipeline}
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioDatagramChannel
import io.netty.handler.codec.string.{StringDecoder, StringEncoder}
import io.netty.handler.logging.LoggingHandler

class NettyUdpServer {

    def bind(port:Int): Unit ={
        val eventLoopGroup = new NioEventLoopGroup
        try{
            val bootstrap = new Bootstrap
            bootstrap.group(eventLoopGroup)
              .channel(classOf[NioDatagramChannel])
              .option[java.lang.Boolean](ChannelOption.SO_BROADCAST,true)
              .option(ChannelOption.ALLOCATOR,PooledByteBufAllocator.DEFAULT)
              .option(ChannelOption.RCVBUF_ALLOCATOR,new AdaptiveRecvByteBufAllocator(Integer.valueOf(1024*20),Integer.valueOf(1024*20),Integer.valueOf(1024*50)))
              .option(ChannelOption.SO_RCVBUF,Integer.valueOf(1024*20))
              .handler(new ChannelInitializer[NioDatagramChannel] {
                override def initChannel(c: NioDatagramChannel): Unit = {
                    val ch = c.pipeline()
                    ch.addLast("debug",new LoggingHandler())
                    ch.addLast("decoder",new StringDecoder())
                    ch.addLast("encoder",new StringEncoder())
                    ch.addLast(new NettyUdpServerHandler)
                }
              })

            println("UDP服务监听开启，监听端口："+port)
            bootstrap.bind(port).channel().closeFuture().await()
        } catch {
          case ex:Exception =>{
              ex.printStackTrace()
          }
        }finally {
          println("UDP服务监听关闭")
          eventLoopGroup.shutdownGracefully()
        }
    }
}

object NettyUdpServer{
  def main(args: Array[String]): Unit = {
      new NettyUdpServer().bind(12345)
  }
}
