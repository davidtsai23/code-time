package com.jfs.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置 RabbitMQ 地址
        factory.setHost("localhost");
        //建立到代理服务器到连接
        Connection connection = factory.newConnection();
        //获得信道
        Channel channel = connection.createChannel();
        //声明queue队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //发送消息

        String message = "Hello World!";
        for (int i=0;i<1000000;i++){
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
        }
        System.out.println(" [x] Sent '" + message + "'");
        channel.close();
        connection.close();
    }
}
