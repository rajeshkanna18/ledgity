package com.ledgityapp.appapi.service;

import com.ledgityapp.appapi.model.UserDetails;
import com.ledgityapp.appapi.model.UserDevices;
import com.ledgityapp.appapi.repository.UserDevicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserDevicesService {

    @Autowired
    private UserDevicesRepository userDevicesRepository;

    @Autowired
    private UserDevicesService userDevicesService;

    public UserDevices saveUserDevices(UserDevices user) { return userDevicesRepository.save(user);}

    public UserDevices updateUserDevices(UserDevices user){
        UserDevices existingUser=userDevicesRepository.findById(user.getId()).orElse(null);
        existingUser.setUsername(user.getUsername());
        return userDevicesRepository.save(existingUser);}

    public Optional<UserDevices> getByDeviceIdentifier(String deviceIdentifier){
        return userDevicesRepository.findByDeviceIdentifier(deviceIdentifier); }

    public Optional<UserDevices> getByUsername(String username){
        return userDevicesRepository.findByUserName(username); }
}
