package com.flogramming.web.rest.vm;

public class CustomLoginVM {

    String clientCode;
    LoginVM user;

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public LoginVM getUser() {
        return user;
    }

    public void setUser(LoginVM user) {
        this.user = user;
    }
}
