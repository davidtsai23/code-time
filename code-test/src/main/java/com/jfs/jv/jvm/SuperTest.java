package com.jfs.jv.jvm;

/**
 * @author caiweiwei
 * @date 2019-04-17
 * @description 测试super关键词，super调用父类构造器必须放在子类构造器第一行，
 *              是因为如果放在后边，那么子类的继承覆盖就会失效，我们在使用子类
 *              的时候，在子类构造的时候为了满足子类的特殊行为进行定制，并通过
 *              语句执行表达，如果此时子类构造执行定制语句完毕又调用了父类的构造
 *              那么父类的执行语句就会覆盖子类的行为，例如：
 *              class Father{
 *
 *                  public Father(){
 *                      String name = "老王";
 *                  }
 *              }
 *
 *              class Son extends Father {
 *                  public Son(){
 *                      String name = "小宝";
 *                      super(); //这里编译通不过，即便能通过，这样的行为会将子类的name赋值行为覆盖
 *                  }
 *              }
 */
public class SuperTest {
    public static void main(String[] args) {
        Son son = new Son();
    }
}

class Father{

    public Father(){
        String name = "老王";
    }
}

class Son extends Father {
    public Son(){
        super();
        String name = "小宝";
    }
}