package util.logs;

public interface Logger<T> {

    /**
     * Logs an event.
     * 
     * @param event The event to log.
     */
    void logEvent(T event);
}
