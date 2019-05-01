package com.jfs.jv.guice;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class PayPalCreditCardProcessor implements CreditCardProcessor {

    private static final String DEFAULT_API_KEY = "development-use-only";

    private String apiKey = DEFAULT_API_KEY;

    @Inject
    public void setApiKey(@Named("PayPal API key") String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public void pay() {
        System.out.println("PayPalCreditCardProcessor apiKey:"+apiKey);
    }

}
