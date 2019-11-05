package com.jfs;

import junit.framework.TestCase;

public class Test extends TestCase {

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

    public void testEnum(){
        System.out.println(ComponentTypeEnum.SELECT);
        System.out.println(ComponentTypeEnum.valueOf("CHECKBOX").compareId);
    }

    public enum ComponentTypeEnum {

        STR_INPUT("字符串输入框",10),
        NUM_INPUT("数字输入框",10),
        POSITIVE_INTEGER_INPUT("正整数输入框",10),
        SELECT("下拉框",10),
        CHECKBOX("多选框",11),
        DOUBLE_INPUT("双文本数字输入框",9),
        PERCENTAGE_INPUT("百分比数字输入框",10),
        DATE_BTW_INPUT("日期介于输入框",13);

        public String name;
        private int compareId;



        ComponentTypeEnum(String name,int compareId){
            this.name = name;
            this.compareId = compareId;
        }

        public String getName() {
            return name;
        }

        public int getCompareId() {
            return compareId;
        }
    }
}
