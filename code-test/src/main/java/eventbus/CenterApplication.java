package eventbus;

import com.google.inject.Inject;

public class CenterApplication implements EventHandler {

    /**
     * The application's event bus.
     */
    private final EventBus eventBus;

    private CenterController centerController;

    @Inject
    public CenterApplication(@ApplicationEventBus EventBus eventBus,
                             CenterController centerController) {
        this.eventBus = eventBus;
        this.centerController = centerController;
    }

    @Override
    public void onEvent(Object event) {
        if (event == ConnectionState.CONNECTING){
            System.out.println("app connecting");

        }
    }

    private void init(){
        eventBus.subscribe(this);
        centerController.init();
    }

    public void runApp(){
        init();
        eventBus.onEvent(ConnectionState.CONNECTING);
    }
}
