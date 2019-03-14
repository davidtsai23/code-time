package com.jfs.jvm.guice;

public class MySqlDatabaseTransactionLog extends DatabaseTransactionLog {
    @Override
    public void say() {
        System.out.println("MySqlDatabaseTransactionLog");
    }
}
