package com.gremlin.kafkaalfidemo;

public class User {
    private String client;
    private String ip;

    public User(String client, String ip) {
        this.client = client;
        this.ip = ip;
    }

    public String toString() {
        return this.ip + "|" + this.client;
    }
}
