package com.ledgityapp.appapi.controller;

import com.ledgityapp.appapi.config.ConfigUtils;
import com.ledgityapp.appapi.model.AuthToken;
import com.ledgityapp.appapi.model.LoginRequest;
import com.ledgityapp.appapi.model.UserRegistrationRequest;
import com.ledgityapp.appapi.model.UserRegistrationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private ConfigUtils configUtils;

    private RestTemplate restTemplate;

    @Autowired
    public UserController(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserRegistrationResponse register(@RequestBody UserRegistrationRequest user) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(configUtils.getRegistrationUrl());

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<UserRegistrationRequest> entity = new HttpEntity<>(user, headers);

        return restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.POST, entity, UserRegistrationResponse.class).getBody();
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthToken login(@RequestBody LoginRequest loginDto) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(configUtils.getLoginUrl());
        builder.queryParam("username", loginDto.getUserName());
        builder.queryParam("password", loginDto.getPassword());
        builder.queryParam("grant_type", loginDto.getGrant_type());

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, configUtils.getBasicAuth());
        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(headers);

//        ResponseEntity<String> responseEntity = restTemplate.exchange(
//                builder.build().encode().toUri(),
//                HttpMethod.POST,
//                requestEntity,
//                String.class);
//
//        HttpStatus statusCode = responseEntity.getStatusCode();
//
//        AuthToken result = new AuthToken();
//        if (statusCode == HttpStatus.ACCEPTED) {
//            return responseEntity.getBody();
//        }
//        return result;

        // return restTemplate.exchange(configUtils.getLoginUrl(), HttpMethod.POST, entity, AuthToken.class).getBody();
         return restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.POST, requestEntity, AuthToken.class).getBody();
    }

    @GetMapping("/ping")
    @ResponseStatus(HttpStatus.OK)
    public String getPing() {
        return "Welcome To Ledgity";
    }

}
