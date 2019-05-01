package com.jfs.eventbus;

import com.google.inject.Inject;

public class CenterController implements EventHandler {

    private final EventSource eventSource;

    @Inject
    public CenterController(@ApplicationEventBus EventBus eventBus) {
        this.eventSource = eventBus;
    }

    @Override
    public void onEvent(Object event) {
        System.out.println("CenterController");
    }

    void init(){
        eventSource.subscribe(this);
    }
}
