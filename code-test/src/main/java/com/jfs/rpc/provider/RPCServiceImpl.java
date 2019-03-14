package com.jfs.rpc.provider;

public class RPCServiceImpl implements RPCService {
    @Override
    public int add(int a, int b) {
        return a + b;
    }
}
