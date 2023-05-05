package com.user.userservice.jwt.model;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private final String displayName;
    private final String role;

    public JwtResponse(String jwttoken, String displayName, String role) {
        this.jwttoken = jwttoken;
        this.displayName = displayName;
        this.role = role;
    }

    public String getToken() {
        return this.jwttoken;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public String getRole() {
        return this.role;
    }
}
