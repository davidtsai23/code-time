package com.jfs.jv.proxy.statics;

public class RentersServiceProxy implements RentersService {

    private RentersService target;

    public RentersServiceProxy(RentersService target) {
        this.target = target;
    }

    @Override
    public void rentHouse() {
        System.out.println("开始代理租房手续");
        target.rentHouse();
        System.out.println("租房完成");
    }
}
