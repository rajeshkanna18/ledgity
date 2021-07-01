package com.ledgityapp.appapi.config;

import com.ledgityapp.appapi.config.model.UserDetailsImpl;
import com.ledgityapp.appapi.config.model.UsernamePasswordAuthenticationTokenImpl;
import com.ledgityapp.appapi.model.ValidateResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Component
public class JwtAuthProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private ConfigUtils configUtils;

    private RestTemplate restTemplate;

    @Autowired
    public JwtAuthProvider(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Override
    @SneakyThrows
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        // NO-OP required
    }

    @Override
    @SneakyThrows
    protected UserDetails retrieveUser(String userName, UsernamePasswordAuthenticationToken authToken) {
        try {
            UsernamePasswordAuthenticationTokenImpl token = (UsernamePasswordAuthenticationTokenImpl) authToken;

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(configUtils.getValidateUrl());
//            builder.queryParam("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MjUwMzUwNzcsInVzZXJfbmFtZSI6Ijk5OTk5OTk5MTEiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiZGFiNGViODgtZmNmZi00MWNjLTllZGYtZjcyNTNkOWYxNzQ1IiwiY2xpZW50X2lkIjoibGVkZ2l0eSIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdfQ.nmN0V9P-fM8fFBQLC0DyGem58df03wQId2WKWUBlk80");
            String tk = token.getToken();
            builder.queryParam("token", tk);

            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
//            headers.add("x-auth-token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MjUwMzEyNjgsInVzZXJfbmFtZSI6Ijk5OTk5OTk5MTEiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiMDllYzk5N2EtZmVhYi00NWIzLWI1OTMtNDQwZmFiYTRkNDdiIiwiY2xpZW50X2lkIjoibGVkZ2l0eSIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdfQ.EH9jOiYJzCnZkRUehbIsS79x9xWjUHAgEx-hvHZIABM");
            headers.add(HttpHeaders.AUTHORIZATION, configUtils.getBasicAuth());

            HttpEntity<UsernamePasswordAuthenticationTokenImpl> entity = new HttpEntity<>(headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    builder.build().encode().toUri(),
                    HttpMethod.POST,
                    entity,
                    String.class);

            ValidateResponse resData = new RestTemplate().exchange(builder.build().encode().toUri(),
                    HttpMethod.POST, entity,
                    ValidateResponse.class).getBody();



            List<GrantedAuthority> grantedAuthorities =
                    AuthorityUtils.commaSeparatedStringToAuthorityList(resData.getRoles());

            return new UserDetailsImpl(resData.getUser_name(), resData.getUserId(), grantedAuthorities);
        } catch (Exception ex) {
            throw new RuntimeException("Invalid AccessToken");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationTokenImpl.class.isAssignableFrom(authentication);
    }
}
