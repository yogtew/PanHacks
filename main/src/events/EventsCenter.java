package events;

import com.google.common.eventbus.EventBus;

/**
 * Manages Events raised in the application.
 */
public class EventsCenter {
    private static EventsCenter singleton;
    private final EventBus eventBus;

    private EventsCenter() {
        this.eventBus = new EventBus();
    }

    public static EventsCenter getSingleton() {
        if (singleton == null) {
            singleton = new EventsCenter();
        }

        return singleton;
    }

    public void registerHandler(Object handler) {
        this.eventBus.register(handler);
    }

    public <E extends BaseEvent> void post(E event) {
        this.eventBus.post(event);
    }
}
