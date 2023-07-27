package com.lms.course.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class AuthService {

    @Autowired
    RestTemplate restTemplate;

    public String isCheckRole(String token)
    {

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8082/api/v1.0/lms/checkRole", HttpMethod.GET, requestEntity, String.class);


        return  response.getBody();
    }
}
