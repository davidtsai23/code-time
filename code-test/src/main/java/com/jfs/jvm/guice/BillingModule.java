package com.jfs.jvm.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class BillingModule extends AbstractModule {
    @Override
    protected void configure() {
        //bind(TransactionLog.class).to(DatabaseTransactionLog.class);
        //bind(DatabaseTransactionLog.class).to(MySqlDatabaseTransactionLog.class);
        bind(CreditCardProcessor.class).annotatedWith(PayPal.class).to(PayPalCreditCardProcessor.class);
        bind(BillingService.class).to(RealBillingService.class);
    }

    @Provides
    TransactionLog provideTransactionLog() {
        DatabaseTransactionLog transactionLog = new DatabaseTransactionLog();
        transactionLog.setJdbcUrl("jdbc:mysql://localhost/pizza");
        transactionLog.setThreadPoolSize(30);
        return transactionLog;
    }

}
