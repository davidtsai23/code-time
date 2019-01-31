package eventbus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import static java.util.Objects.requireNonNull;

public class SimpleEventBus implements EventBus {

    /**
     * This class's logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(SimpleEventBus.class);

    /**
     * The listeners.
     */
    private final Set<EventHandler> listeners = new CopyOnWriteArraySet<>();

    @Override
    public void onEvent(Object event) {
        try {
            for (EventHandler listener : listeners) {
                listener.onEvent(event);
            }
        }
        catch (Exception exc) {
            LOG.warn("Exception thrown by event handler", exc);
        }
    }

    @Override
    public void subscribe(EventHandler listener) {
        requireNonNull(listener, "listener");

        listeners.add(listener);
    }

    @Override
    public void unsubscribe(EventHandler listener) {
        requireNonNull(listener, "listener");

        listeners.remove(listener);
    }
}
