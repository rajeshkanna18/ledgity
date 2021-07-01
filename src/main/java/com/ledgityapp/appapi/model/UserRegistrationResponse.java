package com.ledgityapp.appapi.model;

import java.util.List;

public class UserRegistrationResponse {

    public String id;
    public String userName;
    public String password;
    public boolean active;
    public List<Role> roles;

}

class Role{
    public int id;
    public String rolename;
    public String description;
}
