package com.jfs.jv.guice;

import com.google.inject.ImplementedBy;

/**
 * 直接为HelloGuice加上@ImplementedBy注释，而省略掉对com.google.inject.Module的实现
 * HelloGuiceModule.java不再需要
 */
@ImplementedBy(HelloGuiceImpl.class)
public interface HelloGuice {
    public void sayHello();
}
