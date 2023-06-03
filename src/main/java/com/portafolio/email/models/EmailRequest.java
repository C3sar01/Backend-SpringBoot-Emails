package com.portafolio.email.models;

public class EmailRequest {

    private String name;
    private String email;
    private String message;
    private String password;

    public EmailRequest(String name, String email, String message, String password) {
        this.name = name;
        this.email = email;
        this.message = message;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
