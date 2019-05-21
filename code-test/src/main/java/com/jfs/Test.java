package com.jfs;

public class Test {

    public static void main(String[] args) {
        long fliterFlag = (1<<6) + (1<<7) + (1<<8) + (1<<19);
        System.out.println(fliterFlag);
        long ignoreFlag = fliterFlag | (0);

        System.out.println(ignoreFlag);
        System.out.println(~ignoreFlag);
        long flag = (1<<13) + (1<<25) + (1<<26) +(1<<14);
        System.out.println(flag);

        long localFlag = flag&(~ignoreFlag);
        System.out.println("localFlag="+localFlag);

        localFlag ^= (1<<25);
        System.out.println("localFlag="+localFlag);

        localFlag ^= (1<<26);
        System.out.println("localFlag="+localFlag);

        localFlag ^= (1<<14);
        System.out.println("localFlag="+localFlag);

        localFlag ^= (1<<13);
        System.out.println("localFlag="+localFlag);

    }
}
