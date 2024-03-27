package com.example.response;

public class LogInResponse {
    String message;
    Boolean status;
    private boolean success;

    public LogInResponse(String message, Boolean status) {
        this.message = message;
        this.status = status;
    }

    public LogInResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LogInResponse{" +
                "message='" + message + '\'' +
                ", status=" + status +
                '}';
    }

    public boolean isSuccess() {
        return this.success;
    }

}
