package com.jfs.jvm.proxy.dynamic;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;


public class CgLibDynamicProxy implements MethodInterceptor {

    private Object target;

    public CgLibDynamicProxy(Object target) {
        this.target = target;
    }

    /**
     * 为目标对象生成代理对象
     * @return
     */
    public Object getProxyInstance() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib 动态代理开始");
        method.invoke(target,objects);
        System.out.println("cglib 动态代理结束");
        return null;
    }
}
