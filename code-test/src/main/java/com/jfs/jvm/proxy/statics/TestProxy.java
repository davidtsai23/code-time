package com.jfs.jvm.proxy.statics;

import com.jfs.jvm.proxy.dynamic.RentersDynamicProxy;

public class TestProxy {
    public static void main(String[] args) {

        testDynamicProxy();
    }

    public static void testDynamicProxy(){
        RentersService target = new RentersServiceImpl();
        RentersService rentersService = (RentersService) new RentersDynamicProxy(target).getProxyInstance();
        System.out.println(target.getClass());
        System.out.println(rentersService.getClass());
        rentersService.rentHouse();
    }

    public static void testStaticProxy(){
        //目标对象
        RentersService rentersService = new RentersServiceImpl();
        //代理对象
        RentersServiceProxy serviceProxy = new RentersServiceProxy(rentersService);
        serviceProxy.rentHouse();
    }
}
