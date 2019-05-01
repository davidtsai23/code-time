package com.jfs.jv.jvm;

import java.util.HashSet;
import java.util.Objects;

public class HashCodeTest {
    public static void main(String[] args) {
        User user1 = new User("1", "xiaohua", "14");
        User user2 = new User("2", "xiaohua", "14");
        System.out.println((user1.equals(user2)));//打印为 true

        //不重写hashCode情况下用HashSet存储数据
        HashSet set = new HashSet();
        set.add(user1); //add方法会使用对象的hashCode做对象是否一样做存储判断
        set.add(user2);
        System.out.println(user1.equals(user2));//true
        System.out.println(user1.hashCode()==user2.hashCode()); //不重写hashCode()方法时，false
        System.out.println(set);//重写hashCode之后，打印set内容只有一条user数据
    }
}

/**
 * Java 对象如果要比较是否相等，
 * 则需要重写 equals 方法，同时重写 hashCode 方法，
 * 而且 hashCode 方法里面使用质数 31
 */
class User{
    private String id;
    private String name;
    private String age;

    public User() {
    }

    public User(String id, String name, String age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    /**
     * 为什么重写equals方法？
     * 如果不重写则调用父类方法即Object类的equals方法，方法体为：
     * public boolean equals(Object obj) {
     *         return (this == obj);
     *     }
     * 比较的是引用对象的内存地址，肯定不相等
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        //年龄名字相同就认为是同一对象
        return Objects.equals(name, user.name) &&
                Objects.equals(age, user.age);
    }

    /**
     * 为什么重写hashCode？
     * 因为根据对象相等判断的协定，一个对象必须返回相同的hash值，两个相等的对象必须具备相等的hash码，
     * 如果不遵从此规定，那么在判定同一个对象的时候用equals方法判断为true，而在使用HashMap、HashSet
     * 存储数据时会根据存储对象的hashCode值是否一致来判断是否是同一对象。
     * 当不重写时，返回的hashCode为对象的内存地址的hash值，所以new两个对象的hashCode是不一样的。
     * @return
     */
    @Override
    public int hashCode() {
        int result = 17;
        /**
         * String 源码中也使用的 31，然后网上说有这两点原因：
         *
         * 原因一：更少的乘积结果冲突
         * 　　31是质子数中一个“不大不小”的存在，如果你使用的是一个如2的较小质数，那么得出的乘积会在一个很小的范围，很容易造成哈希值的冲突。
         *     而如果选择一个100以上的质数，得出的哈希值会超出int的最大范围，这两种都不合适。
         *     而如果对超过 50,000 个英文单词（由两个不同版本的 Unix 字典合并而成）进行 hash code 运算，
         *     并使用常数 31, 33, 37, 39 和 41 作为乘子，每个常数算出的哈希值冲突数都小于7个（国外大神做的测试），
         *     那么这几个数就被作为生成hashCode值得备选乘数了。
         *
         * 　　所以从 31,33,37,39 等中间选择了 31 的原因看原因二。
         *
         * 原因二：31 可以被 JVM 优化
         * 　　JVM里最有效的计算方式就是进行位运算了：
         *
         * 　　* 左移 << : 左边的最高位丢弃，右边补全0（把 << 左边的数据*2的移动次幂）。
         * 　　* 右移 >> : 把>>左边的数据/2的移动次幂。
         * 　　* 无符号右移 >>> : 无论最高位是0还是1，左边补齐0。 　　
         *
         *       所以 ： 31 * i = (i << 5) - i（左边  31*2=62,右边   2*2^5-2=62） - 两边相等，JVM就可以高效的进行计算啦。。。
         */
        result = 31 * result + (name == null ? 0 : name.hashCode());
        result = 31 * result + (age == null ? 0 : age.hashCode());
        return result;
    }
}