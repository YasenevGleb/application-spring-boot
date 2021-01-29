package com.example.demo.auth;

public enum ApplicationUserPermission1 {
    USER_READ("user:read"),
    USER_WRITE("user:write");
    private final String permission;

    ApplicationUserPermission1(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
