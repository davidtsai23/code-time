package com.jfs.jvm.guice.provider;

import com.google.inject.Inject;
import com.jfs.jvm.guice.DatabaseTransactionLog;
import com.jfs.jvm.guice.TransactionLog;

import java.sql.Connection;

public class DatabaseTransactionLogProvider implements Provider<TransactionLog> {
    private final Connection connection;

    @Inject
    public DatabaseTransactionLogProvider(Connection connection) {
        this.connection = connection;
    }

    public TransactionLog get() {
        DatabaseTransactionLog transactionLog = new DatabaseTransactionLog();
        transactionLog.setConnection(connection);
        return transactionLog;
    }
}
