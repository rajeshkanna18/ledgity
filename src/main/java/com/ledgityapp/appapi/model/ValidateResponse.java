package com.ledgityapp.appapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateResponse {

    private String userId;
    private String user_name;
    private String roles="USER";
}
