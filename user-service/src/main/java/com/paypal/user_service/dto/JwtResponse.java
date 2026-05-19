package com.paypal.user_service.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
//Response DTO used to send JWT token to the client
@Data // getters, setters, toString()
@AllArgsConstructor // for constructor
public class JwtResponse {
    private String token;
}
//    public JwtResponse(String token) {
//        this.token = token;
//    }
//
//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }
