package jvm.guice;

public class DatabaseTransactionLog implements TransactionLog {
    @Override
    public void say() {
        System.out.println("DatabaseTransactionLog");
    }
}
