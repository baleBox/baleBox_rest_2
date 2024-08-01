package org.rest;

import lombok.RequiredArgsConstructor;
import org.rest.entity.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Communication {

    private final RestTemplate restTemplate;

    private String sessionId;
    private final String URL = "http://94.198.50.185:7081/api/users/";


    public void getSessionId() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate
                .exchange(URL, HttpMethod.GET, entity, String.class);
        sessionId = response.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
    }

    public List<User> getAll() {
        ResponseEntity<List<User>> responseEntity = restTemplate
                .exchange(URL, HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {
                        });

        return responseEntity.getBody();
    }

    public String save(User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.COOKIE, sessionId);
        HttpEntity<User> request = new HttpEntity<>(user, headers);
        ResponseEntity<String> response = restTemplate
                .exchange(URL, HttpMethod.POST, request, String.class);

        return response.getBody();
    }

    public String update(User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.COOKIE, sessionId);
        HttpEntity<User> request = new HttpEntity<>(user, headers);
        ResponseEntity<String> response = restTemplate
                .exchange(URL, HttpMethod.PUT, request, String.class);

        return response.getBody();
    }

    public String delete(Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.COOKIE, sessionId);
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate
                .exchange(URL + id, HttpMethod.DELETE, request, String.class);

        return response.getBody();
    }
}
