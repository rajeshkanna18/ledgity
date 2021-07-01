package com.ledgityapp.appapi.controller;

import com.ledgityapp.appapi.model.UserDetails;
import com.ledgityapp.appapi.model.UserDevices;
import com.ledgityapp.appapi.repository.UserDevicesRepository;
import com.ledgityapp.appapi.service.UserDevicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserDevicesController {

    @Autowired
    private UserDevicesService userDevicesService;

    @Autowired
    private UserDevicesRepository userDevicesRepository;

    @PostMapping("/device")
    public UserDevices addUserDevices(@RequestBody UserDevices userDevices) { return userDevicesService.saveUserDevices(userDevices); }

    @PutMapping("/device")
    public UserDevices updateUserDevices(@RequestBody UserDevices userDevices) { return userDevicesService.updateUserDevices(userDevices); }

    @GetMapping("/getbydeviceidentifier/{deviceidentifier}")
    public Optional<UserDevices> findByDeviceIdentifier(@PathVariable String deviceidentifier) { return userDevicesService.getByDeviceIdentifier(deviceidentifier);}

    @GetMapping("/getbyusername/{username}")
    public Optional<UserDevices> findByUserName(@PathVariable String username) { return userDevicesService.getByUsername(username);}

}
