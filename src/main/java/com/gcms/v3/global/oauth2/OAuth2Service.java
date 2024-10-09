package com.gcms.v3.global.oauth2;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static com.gcms.v3.global.oauth2.GoogleProperties.GRANT_TYPE;

@Service
@RequiredArgsConstructor
public class OAuth2Service {

    private final GoogleProperties config;
    private final RestTemplate restTemplate;

    public String requestAccessToken(String code) {
        String decode = URLDecoder.decode(code, StandardCharsets.UTF_8);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", config.getClientId());
        body.add("client_secret", config.getClientSecret());
        body.add("code", decode);
        body.add("redirect_uri", config.getRedirectUri());
        body.add("grant_type", GRANT_TYPE);

        HttpEntity<?> request = new HttpEntity<>(body, httpHeaders);

        ResponseEntity<Map> response = restTemplate.postForEntity(config.getTokenUri(), request, Map.class);
        return (String) response.getBody().get("access_token");
    }


    public GoogleOAuth2UserInfo requestUserInfo(String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setBearerAuth(accessToken);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();

        HttpEntity<?> request = new HttpEntity<>(body, httpHeaders);

        ResponseEntity<Map> response = restTemplate.exchange(config.getUserInfoUri(), HttpMethod.GET, request, Map.class);

        return new GoogleOAuth2UserInfo(response.getBody());
    }
}
