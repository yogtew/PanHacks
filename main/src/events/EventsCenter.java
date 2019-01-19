package events;

import com.google.common.eventbus.EventBus;

/**
 * Manages Events raised in the application
 */
public class EventsCenter {
    private static EventsCenter singleton;
    private final EventBus eventBus;

    private EventsCenter() {
        this.eventBus = new EventBus();
    }

    public EventsCenter getSingleton() {
        if (this.singleton == null) {
            this.singleton = new EventsCenter();
        }

        return this.singleton;
    }

    public void registerHandler(Object handler) {
        this.eventBus.register(handler);
    }

    public <E extends BaseEvent> void post(E event) {
        this.eventBus.post(event);
    }
}
