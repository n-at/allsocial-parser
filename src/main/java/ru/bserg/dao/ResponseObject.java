package ru.bserg.dao;

import java.util.List;

/**
 * Created by SBoichenko on 25.04.2016.
 */
public class ResponseObject {

    Long error_status;

    String error_message;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    Response response;

    public Long getError_status() {
        return error_status;
    }

    public void setError_status(Long error_status) {
        this.error_status = error_status;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }
}
