package com.jfs.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import junit.framework.TestCase;

/**
 * @Author: caiweiwei
 * @Date: 2019-06-13 20:50
 * @Description
 */
public class AviatorTest extends TestCase {

    public void test(){
        String expression = "1&(0^0)";

        long start = System.currentTimeMillis();
        for (int i=0;i<100000;i++){
            AviatorEvaluator.exec(expression);
        }
        System.out.println("Aviator耗时："+ (System.currentTimeMillis()-start));

        long start2 = System.currentTimeMillis();



    }
}
