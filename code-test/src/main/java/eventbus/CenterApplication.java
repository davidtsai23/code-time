package eventbus;

import com.google.inject.Inject;

public class CenterApplication implements EventHandler {

    /**
     * The application's event bus.
     */
    private final EventBus eventBus;

    @Inject
    public CenterApplication(@ApplicationEventBus EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void onEvent(Object event) {
        if (event == ConnectionState.CONNECTING){
            System.out.println("app connecting");
            CenterController centerController = new CenterController(new SimpleEventBus());
            centerController.init();

        }
    }

    private void init(){
        eventBus.subscribe(this);
    }

    public void runApp(){
        init();
        eventBus.onEvent(1);
    }
}
