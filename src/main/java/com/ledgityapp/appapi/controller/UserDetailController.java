package com.ledgityapp.appapi.controller;

import com.ledgityapp.appapi.model.UserDetails;
import com.ledgityapp.appapi.repository.UserDetailRepository;
import com.ledgityapp.appapi.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserDetailController {

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/add")
    public UserDetails addUser(@RequestBody UserDetails user) { return userDetailsService.saveUser(user); }

    @PutMapping("/update")
    public UserDetails updateUser(@RequestBody UserDetails user) { return userDetailsService.updateUser(user); }

    @GetMapping("/{id}")
    public UserDetails findAllUsers(@PathVariable int id) { return userDetailsService.getUserById(id); }

    @GetMapping("/byusername/{username}")
    public Optional<UserDetails> findByUsername(@PathVariable String username) { return userDetailsService.getByUsername(username); }

}
