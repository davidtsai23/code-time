package com.jfs.jv.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.TestCase;

public class TestGuice extends TestCase {

    public void testHelloGuice(){
        Injector injector = Guice.createInjector(new HelloGuiceModule());
        HelloGuice helloGuice = injector.getInstance(HelloGuice.class);
        helloGuice.sayHello();
    }

    public void testHelloGuiceWithAnnotation(){
        Injector injector = Guice.createInjector();
        HelloGuice helloGuice = injector.getInstance(HelloGuice.class);
        helloGuice.sayHello();
    }

    public void testBillingModule(){
        Injector injector = Guice.createInjector(new BillingModule());
        TransactionLog transactionLog = injector.getInstance(TransactionLog.class);
        transactionLog.say();
        BillingService billingService = injector.getInstance(BillingService.class);
        billingService.order();
    }
}
