package com.jfs.jv.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class RentersDynamicProxy implements InvocationHandler {
    private Object target;

    public RentersDynamicProxy(Object target) {
        this.target = target;
    }

    // 为目标对象生成代理对象
    public Object getProxyInstance(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始动态代理");
        method.invoke(target,args);
        System.out.println("动态代理结束");
        return null;
    }
}
