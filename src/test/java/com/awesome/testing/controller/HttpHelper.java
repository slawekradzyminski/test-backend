package com.awesome.testing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public abstract class HttpHelper {

    @Autowired
    protected TestRestTemplate restTemplate;

    protected <T> ResponseEntity<T> executeGet(String url, Class<T> responseType, Object... urlVariables) {
        return execute(HttpMethod.GET, url, null, responseType, urlVariables);
    }

    protected <T, V> ResponseEntity<T> executePut(String url, V body, Class<T> responseType, Object... urlVariables) {
        return execute(HttpMethod.PUT, url, body, responseType, urlVariables);
    }

    protected <T> ResponseEntity<T> executeDelete(String url, Class<T> responseType, Object... urlVariables) {
        return execute(HttpMethod.DELETE, url, null, responseType, urlVariables);
    }

    protected <T, V> ResponseEntity<T> executePost(String url, V body, Class<T> responseType, Object... urlVariables) {
        return execute(HttpMethod.POST, url, body, responseType, urlVariables);
    }

    private <T, V> ResponseEntity<T> execute(HttpMethod httpMethod, String url, V body, Class<T> responseType, Object[] urlVariables) {
        return restTemplate.exchange(url,
                httpMethod,
                new HttpEntity<>(body, getRequiredHeaders()),
                responseType,
                urlVariables);
    }

    protected static HttpHeaders getRequiredHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, "application/json");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        return headers;
    }

}
