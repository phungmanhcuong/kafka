package org.apache.kafka.reusable.startable;

public interface Server<T, State> {
    T startup();
    T shutdown();
    T setServerState(State sate);
    T awaitShutdown();
}
