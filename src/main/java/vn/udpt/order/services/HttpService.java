package vn.udpt.order.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import vn.udpt.order.models.dto.Response;

import java.util.Map;

public interface HttpService {
    ResponseEntity<Object> post(String url, Object request, HttpHeaders httpHeaders);

    ResponseEntity<Object> post(String uriStr, HttpHeaders headers);

    ResponseEntity<Object> post(String url, Object request) throws JsonProcessingException;

    ResponseEntity<Object> get(String url, Map<String, String> params, HttpHeaders headers);

    ResponseEntity<Object> get(String url, Map<String, String> params);

    ResponseEntity<String> getString(String url, Map<String, String> params, HttpHeaders headers);

    ResponseEntity<String> getString(String url, Map<String, String> params);

    ResponseEntity<Object> get(String url) throws JsonProcessingException;

    <T> ResponseEntity<T> post(String url, Object request, Class<T> destinationType) throws Exception;

    <T> ResponseEntity<T> post(String url, Object request, HttpHeaders headers, Class<T> destinationType) throws Exception;

    <T> ResponseEntity<Response<T>> sendInternalPost(String url, Object request, RestTemplate restTemplate, Class<T> destinationType);

    <T> ResponseEntity<Response<T>> sendInternalPost(String url, Object request, RestTemplate restTemplate, Class<T> destinationType, boolean logRequestResponse);

    <T> ResponseEntity<T> sendPost(String url, Object request, RestTemplate restTemplate, HttpHeaders httpHeaders, Class<T> destinationType, boolean logRequestResponse);

    <T> ResponseEntity<T> sendPost(String url, Object request, RestTemplate restTemplate, Class<T> destinationType);

    <T> ResponseEntity<T> sendPost(String url, Object request, RestTemplate restTemplate, Class<T> destinationType, boolean logRequestResponse);

    <T> ResponseEntity<T> sendGet(String url, RestTemplate restTemplate, HttpHeaders httpHeaders, Class<T> destinationType, boolean logRequestResponse);
}
