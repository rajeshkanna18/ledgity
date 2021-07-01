package com.ledgityapp.appapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationRequest {
    private String userName;
    private String password;
    private boolean active;
}
