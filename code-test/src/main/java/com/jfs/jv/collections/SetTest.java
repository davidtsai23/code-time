package com.jfs.jv.collections;

import java.util.HashSet;
import java.util.Set;

public class SetTest {
    public static void main(String[] args) {
        Set<Integer> needReleaseLeg = new HashSet<>();
        Set<Integer> nowNeedOccupyLeg = new HashSet<Integer>() {
            {
                add(1);

            }
        };

        Set<Integer> alreadyOccupyPoint = new HashSet<Integer>() {
            {
                add(4);
                add(2);
                add(3);
            }
        };

        needReleaseLeg.addAll(alreadyOccupyPoint);
        needReleaseLeg.removeAll(nowNeedOccupyLeg);


    }
}
