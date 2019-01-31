package eventbus;

public interface EventSource {
    void subscribe(EventHandler listener);

    void unsubscribe(EventHandler listener);
}
