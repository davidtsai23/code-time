package jvm.guice;

public class HelloGuiceImpl implements HelloGuice {

    @Override
    public void sayHello() {
        System.out.println("Hello Guice!");
    }
}
