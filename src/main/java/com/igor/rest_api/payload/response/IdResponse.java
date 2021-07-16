package com.igor.rest_api.payload.response;

public class IdResponse {
    private final Long id;

    public IdResponse(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
