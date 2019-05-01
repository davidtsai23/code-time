package com.jfs.rpc.consumer;

import com.jfs.rpc.provider.RPCService;

public class RPCConsumer {

    private RPCService rpcService;

    public RPCConsumer() {
        this.rpcService = new RPCServiceImpl();
    }

    public static void main(String[] args) {
        RPCConsumer rpcConsumer = new RPCConsumer();
        int result = rpcConsumer.getRpcService().add(1,2);
        System.out.println(result);
    }

    public RPCService getRpcService() {
        return rpcService;
    }

    public void setRpcService(RPCService rpcService) {
        this.rpcService = rpcService;
    }
}
