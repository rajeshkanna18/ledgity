package com.ledgityapp.appapi.service;

import com.ledgityapp.appapi.model.UserDetails;
import com.ledgityapp.appapi.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsService {

    @Autowired
    private UserDetailRepository userDetailRepository;

    public UserDetails saveUser(UserDetails user){ return userDetailRepository.save(user); }

    public UserDetails updateUser(UserDetails user){
        UserDetails existingUser=userDetailRepository.findById(user.getId()).orElse(null);
        existingUser.setUsername(user.getUsername());
        return userDetailRepository.save(existingUser);
    }

    public UserDetails getUserById(int id){ return userDetailRepository.findById(id).orElse(null); }

    public Optional<UserDetails> getByUsername(String username){
        return userDetailRepository.findByUsername(username); }
}
