package src.util.logging;

public interface Logger<T> {

    void logEvent(T event, boolean isDebug);
}
