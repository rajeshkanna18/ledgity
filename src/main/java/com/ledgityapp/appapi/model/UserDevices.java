package com.ledgityapp.appapi.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="public.userdevices")
public class UserDevices {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String deviceIdentifier;
    private String username;
    private String env;
}
