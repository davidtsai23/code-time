package jvm.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.TestCase;

public class TestGuice extends TestCase {

    public void testHelloGuice(){
        Injector injector = Guice.createInjector(new HelloGuiceModule());
        HelloGuice helloGuice = injector.getInstance(HelloGuice.class);
        helloGuice.sayHello();
    }
}
