package com.gregzealley.apidemo.models;

public class DefaultErrorResponse {

    private String detail;

    public DefaultErrorResponse(final String detail) {
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(final String detail) {
        this.detail = detail;
    }
}
