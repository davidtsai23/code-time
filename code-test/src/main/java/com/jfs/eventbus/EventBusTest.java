package com.jfs.eventbus;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class EventBusTest {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(customConfigurationModule());
        injector.getInstance(CenterApplication.class).runApp();
    }

    private static Module customConfigurationModule() {
        EventBusModule eventBusModule = new EventBusModule();

        return eventBusModule;
    }
}
