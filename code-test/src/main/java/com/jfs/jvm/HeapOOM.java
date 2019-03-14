package com.jfs.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * VM参数：-Xmx20m -Xms20m -XX:+HeapDumpOnOutOfMemoryError(让虚拟机出现内存溢出时dump内存快照)
 */
public class HeapOOM {

    static class OOMObject {

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();
        while (true){
            list.add(new OOMObject());
        }
    }
}
