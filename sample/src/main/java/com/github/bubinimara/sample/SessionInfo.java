package com.github.bubinimara.sample;

/**
 * Created by davide on 27/09/16.
 */

public class SessionInfo {
    private String mail;
    private long timestamp;
    // other here ...


    public SessionInfo() {
    }

    public SessionInfo(String mail, long timestamp) {
        this.mail = mail;
        this.timestamp = timestamp;
    }

    public String getMail() {
        return mail;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
