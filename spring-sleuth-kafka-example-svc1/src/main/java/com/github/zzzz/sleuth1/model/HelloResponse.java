package com.github.zzzz.sleuth1.model;

public class HelloResponse
{
    private final long id;
    private final String content;

    public HelloResponse(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

}
