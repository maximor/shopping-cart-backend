package com.personal.shoppingcartrest.errormessage;

import java.util.Date;

public class ErrorMessage {
    private int status;
    private Date date;
    private String error;
    private String message;
    private String route;

    public ErrorMessage() {
    }

    public ErrorMessage(int status, Date date, String error, String message, String route) {
        this.status = status;
        this.date = date;
        this.error = error;
        this.message = message;
        this.route = route;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }
}
