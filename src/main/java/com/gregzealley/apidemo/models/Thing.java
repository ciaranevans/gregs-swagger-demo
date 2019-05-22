package com.gregzealley.apidemo.models;

public class Thing {

    private String message;

    public Thing(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

}
