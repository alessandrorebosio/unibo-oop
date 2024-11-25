package it.unibo.exceptions.fakenetwork.api;

import java.io.IOException;

public class NetworkException extends IOException {

    private final static String DEFAULT = "Network error: no response";

    public NetworkException() {
        super(DEFAULT);
    }

    public NetworkException(final String message) {
        super("Network error while sending message: " + message);
    }

}
