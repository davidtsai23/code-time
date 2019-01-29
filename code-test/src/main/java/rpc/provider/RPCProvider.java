package rpc.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rpc.RPCRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class RPCProvider {

    private static Logger log = LoggerFactory.getLogger(RPCProvider.class);


    RPCService rpcService;

    public RPCProvider() {
        this.rpcService = new RPCServiceImpl();
    }

    public static void main(String[] args) throws IOException {
        new RPCProvider().run();
    }

    private void run() throws IOException {
        ServerSocket listener = new ServerSocket(9090);
        try {
            while (true) {
                Socket socket = listener.accept();
                try {
                    // 将请求反序列化
                    ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                    Object object = objectInputStream.readObject();

                    log.info("request is {}", object);

                    // 调用服务
                    int result = 0;
                    if (object instanceof RPCRequest) {
                        RPCRequest rpcRequest = (RPCRequest) object;
                        if ("add".equals(rpcRequest.getMethod())) {
                            result = rpcService.add(rpcRequest.getA(), rpcRequest.getB());
                        } else {
                            throw new UnsupportedOperationException();
                        }
                    }

                    // 返回结果
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    objectOutputStream.writeObject(new Integer(result));
                } catch (Exception e) {
                    log.error("fail", e);
                } finally {
                    socket.close();
                }
            }
        } finally {
            listener.close();
        }
    }
}
