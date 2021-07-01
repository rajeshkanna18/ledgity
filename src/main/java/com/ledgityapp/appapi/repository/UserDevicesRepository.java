package com.ledgityapp.appapi.repository;

import com.ledgityapp.appapi.model.UserDevices;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserDevicesRepository extends JpaRepository<UserDevices, UUID> {
    Optional<UserDevices> findByDeviceIdentifier(String deviceIdentifier);
    Optional<UserDevices> findByUserName(String username);

}
