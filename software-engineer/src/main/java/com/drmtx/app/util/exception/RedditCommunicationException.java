package com.drmtx.app.util.exception;

/**
 * Exception thrown when there is a communication problem with the Reddit API
 */
public class RedditCommunicationException extends Exception {
    public RedditCommunicationException(String message) {
        super(message);
    }

    public RedditCommunicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
