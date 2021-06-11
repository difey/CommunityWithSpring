package com.example.demo.DTO;

public class GoogleUser {
    private String email;
    private String name;
    private String sub;

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
        this.name = email.split("@")[0];
    }
}
