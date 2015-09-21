package com.drmtx.app.util.exception;

/**
 * Exception thrown when there is a problem with the data returned from the Reddit API
 */
public class RedditDataException extends Exception {
    public RedditDataException(String message) {
        super(message);
    }

    public RedditDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
