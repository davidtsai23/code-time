package com.jfs.jv.guice;

import java.sql.Connection;

public class DatabaseTransactionLog implements TransactionLog {
    String jdbcUrl;
    int threadPoolSize;
    Connection connection;


    @Override
    public void say() {
        System.out.println("DatabaseTransactionLog");
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public int getThreadPoolSize() {
        return threadPoolSize;
    }

    public void setThreadPoolSize(int threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }
}
