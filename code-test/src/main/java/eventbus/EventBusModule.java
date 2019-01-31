package eventbus;

import com.google.inject.AbstractModule;

public class EventBusModule extends AbstractModule {
    @Override
    protected void configure() {
        EventBus newEventBus = new SimpleEventBus();
        bind(EventHandler.class)
                .annotatedWith(ApplicationEventBus.class)
                .toInstance(newEventBus);
        bind(EventSource.class)
                .annotatedWith(ApplicationEventBus.class)
                .toInstance(newEventBus);
        bind(EventBus.class)
                .annotatedWith(ApplicationEventBus.class)
                .toInstance(newEventBus);
    }
}
