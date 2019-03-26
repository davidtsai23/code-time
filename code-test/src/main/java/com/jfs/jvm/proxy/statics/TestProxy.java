package com.jfs.jvm.proxy.statics;

public class TestProxy {
    public static void main(String[] args) {
        //目标对象
        RentersService rentersService = new RentersServiceImpl();
        //代理对象
        RentersServiceProxy serviceProxy = new RentersServiceProxy(rentersService);
        serviceProxy.rentHouse();
    }
}
