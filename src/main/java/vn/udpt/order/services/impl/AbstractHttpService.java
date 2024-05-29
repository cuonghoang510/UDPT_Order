package vn.udpt.order.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import vn.udpt.order.models.dto.Response;
import vn.udpt.order.services.HttpService;

import java.net.URI;
import java.util.Map;

@Service
public abstract class AbstractHttpService implements HttpService {
    private static final Logger log = LoggerFactory.getLogger(AbstractHttpService.class);

    protected AbstractHttpService() {
    }

    protected abstract RestTemplate getRestTemplate();

    protected abstract ObjectMapper getObjectMapper();

    public ResponseEntity<Object> post(String url, Object request, HttpHeaders headers) {
        try {
            log.info("START post() - url = {}, request body {}", url, this.getObjectMapper().writeValueAsString(request));
            HttpEntity<Object> entity = new HttpEntity(request, headers);
            ResponseEntity<Object> result = this.getRestTemplate().exchange(url, HttpMethod.POST, entity, Object.class, new Object[0]);
            log.info("END post() - url = {}, response body {}", url, this.getObjectMapper().writeValueAsString(result));
            return result;
        } catch (RestClientResponseException var6) {
            RestClientResponseException e = var6;
            log.error("Call {} server catch RestClientResponseException exception: {}", url, e.getMessage());
        } catch (JsonProcessingException var7) {
            JsonProcessingException e = var7;
            log.error("Convert value as String failed at - post() - url = {}, exception {}", url, e.getMessage());
        } catch (RestClientException var8) {
            RestClientException e = var8;
            log.error("END post() - url = {}, RestClientExcepion: {}", url, e.getMessage());
        }

        return null;
    }

    public ResponseEntity<Object> post(String uriStr, HttpHeaders headers) {
        try {
            log.info("START post() - url = {}", uriStr);
            HttpEntity<Object> entity = new HttpEntity(headers);
            URI uri = URI.create(uriStr);
            ResponseEntity<Object> result = this.getRestTemplate().exchange(uri, HttpMethod.POST, entity, Object.class);
            log.info("END post() - url = {}, response body {}", uriStr, this.getObjectMapper().writeValueAsString(result));
            return result;
        } catch (RestClientResponseException var6) {
            RestClientResponseException e = var6;
            log.error("Call {} server catch RestClientResponseException exception: {}", uriStr, e.getMessage());
        } catch (JsonProcessingException var7) {
            JsonProcessingException e = var7;
            log.error("Convert value as String failed at - post() - url = {}, exception {}", uriStr, e.getMessage());
        } catch (RestClientException var8) {
            RestClientException e = var8;
            log.error("END post() - url = {}, RestClientExcepion: {}", uriStr, e.getMessage());
        }

        return null;
    }

    public ResponseEntity<Object> post(String url, Object request) throws JsonProcessingException {
        try {
            log.info("START post() - url = {}, request body {}", url, this.getObjectMapper().writeValueAsString(request));
            HttpEntity<Object> entity = new HttpEntity(request);
            ResponseEntity<Object> result = this.getRestTemplate().exchange(url, HttpMethod.POST, entity, Object.class, new Object[0]);
            log.info("END post() - url = {}, response body {}", url, this.getObjectMapper().writeValueAsString(result));
            return result;
        } catch (RestClientResponseException var5) {
            RestClientResponseException e = var5;
            log.error("Call {} server catch RestClientResponseException exception: {}", url, e.getMessage());
            return null;
        }
    }

    public <T> ResponseEntity<T> post(String url, Object request, Class<T> destinationType) throws Exception {
        try {
            log.info("START post() - url = {}, request body {}", url, this.getObjectMapper().writeValueAsString(request));
            HttpEntity<Object> entity = new HttpEntity(request);
            ResponseEntity<Object> responseEntity = this.getRestTemplate().exchange(url, HttpMethod.POST, entity, Object.class, new Object[0]);
            T entityBody = this.getObjectMapper().convertValue(responseEntity.getBody(), destinationType);
            log.info("END post() - url = {}, response body {}", url, this.getObjectMapper().writeValueAsString(responseEntity.getBody()));
            return new ResponseEntity(entityBody, responseEntity.getHeaders(), responseEntity.getStatusCode());
        } catch (RestClientResponseException var7) {
            RestClientResponseException e = var7;
            log.error("Call {} server catch RestClientResponseException exception: {}", url, e.getMessage());
            return null;
        }
    }

    public <T> ResponseEntity<T> post(String url, Object request, HttpHeaders headers, Class<T> destinationType) throws Exception {
        try {
            log.info("START post() - url = {}, request body {}", url, this.getObjectMapper().writeValueAsString(request));
            HttpEntity<Object> entity = new HttpEntity(request, headers);
            ResponseEntity<Object> responseEntity = this.getRestTemplate().exchange(url, HttpMethod.POST, entity, Object.class, new Object[0]);
            T entityBody = this.getObjectMapper().convertValue(responseEntity.getBody(), destinationType);
            log.info("END post() - url = {}, response body {}", url, this.getObjectMapper().writeValueAsString(responseEntity.getBody()));
            return new ResponseEntity(entityBody, responseEntity.getHeaders(), responseEntity.getStatusCode());
        } catch (RestClientResponseException var8) {
            RestClientResponseException e = var8;
            log.error("Call {} server catch RestClientResponseException exception: {}", url, e.getMessage());
            return null;
        }
    }

    public ResponseEntity<Object> get(String url, Map<String, String> params, HttpHeaders headers) {
        try {
            log.info("START get() - url = {} with params - {} - headers: {}", new Object[]{url, params, headers});
            HttpEntity<Object> entity = new HttpEntity(headers);
            ResponseEntity<Object> result = this.getRestTemplate().exchange(url, HttpMethod.GET, entity, Object.class, params);
            log.info("END get() - url = {}, response body {}", url, this.getObjectMapper().writeValueAsString(result));
            return result;
        } catch (JsonProcessingException | RestClientResponseException var6) {
            Exception e = var6;
            log.error("Call {} server catch RestClientResponseException exception: {}", url, e.getMessage());
            return null;
        }
    }

    public ResponseEntity<Object> get(String url, Map<String, String> params) {
        try {
            log.info("START get() - url = {} with params - {}", url, params);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            HttpEntity<Object> entity = new HttpEntity(headers);
            ResponseEntity<Object> result = this.getRestTemplate().exchange(url, HttpMethod.GET, entity, Object.class, params);
            log.info("END get() - url = {}, response body {}", url, this.getObjectMapper().writeValueAsString(result));
            return result;
        } catch (JsonProcessingException | RestClientResponseException var6) {
            Exception e = var6;
            log.error("Call {} server catch RestClientResponseException exception: {}", url, e.getMessage());
            return null;
        }
    }

    public ResponseEntity<String> getString(String url, Map<String, String> params, HttpHeaders headers) {
        try {
            log.info("START get() - url = {} with params - {} - headers: {}", new Object[]{url, params, headers});
            headers.setContentType(MediaType.TEXT_PLAIN);
            HttpEntity<String> entity = new HttpEntity(headers);
            ResponseEntity<String> result = this.getRestTemplate().exchange(url, HttpMethod.GET, entity, String.class, params);
            log.info("END get() - url = {}, response body {}", url, this.getObjectMapper().writeValueAsString(result));
            return result;
        } catch (JsonProcessingException | RestClientResponseException var6) {
            Exception e = var6;
            log.error("Call {} server catch RestClientResponseException exception: {}", url, e.getMessage());
            return null;
        }
    }

    public ResponseEntity<String> getString(String url, Map<String, String> params) {
        try {
            log.info("START get() - url = {} with params - {}", url, params);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_PLAIN);
            HttpEntity<String> entity = new HttpEntity(headers);
            ResponseEntity<String> result = this.getRestTemplate().exchange(url, HttpMethod.GET, entity, String.class, params);
            log.info("END get() - url = {}, response body {}", url, this.getObjectMapper().writeValueAsString(result));
            return result;
        } catch (JsonProcessingException | RestClientResponseException var6) {
            Exception e = var6;
            log.error("Call {} server catch RestClientResponseException exception: {}", url, e.getMessage());
            return null;
        }
    }

    public ResponseEntity<Object> get(String url) throws JsonProcessingException {
        try {
            log.info("START get() - url = {}", url);
            ResponseEntity<Object> result = this.getRestTemplate().getForEntity(url, Object.class, new Object[]{1});
            log.info("END get() - url = {}, response body {}", url, this.getObjectMapper().writeValueAsString(result));
            return result;
        } catch (RestClientResponseException var3) {
            RestClientResponseException e = var3;
            log.error("Call {} server catch RestClientResponseException exception: {}", url, e.getMessage());
            return null;
        }
    }

    @SneakyThrows
    public <T> ResponseEntity<T> sendPost(String url, Object request, RestTemplate restTemplate, Class<T> destinationType) {
        try {
            return this.sendPost(url, request, restTemplate, destinationType, true);
        } catch (Throwable var6) {
            Throwable $ex = var6;
            throw $ex;
        }
    }

    @SneakyThrows
    public <T> ResponseEntity<T> sendPost(String url, Object request, RestTemplate restTemplate, Class<T> destinationType, boolean logRequestResponse) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            if (logRequestResponse) {
                log.info("Send POST request to {} : request body: {}", url, this.getObjectMapper().writeValueAsString(request));
            }

            HttpEntity<Object> entity = new HttpEntity(request, headers);
            ResponseEntity<Object> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, Object.class, new Object[0]);
            if (logRequestResponse) {
                log.info("Response from {}; HTTP STATUS: {}; Response Body: {};", new Object[]{url, responseEntity.getStatusCode().value(), this.getObjectMapper().writeValueAsString(responseEntity.getBody())});
            }

            T entityBody = this.getObjectMapper().convertValue(responseEntity.getBody(), destinationType);
            return new ResponseEntity(entityBody, responseEntity.getHeaders(), responseEntity.getStatusCode());
        } catch (Throwable var10) {
            Throwable $ex = var10;
            throw $ex;
        }
    }

    @SneakyThrows
    public <T> ResponseEntity<Response<T>> sendInternalPost(String url, Object request, RestTemplate restTemplate, Class<T> destinationType) {
        try {
            return this.sendInternalPost(url, request, restTemplate, destinationType, true);
        } catch (Throwable var6) {
            Throwable $ex = var6;
            throw $ex;
        }
    }

    @SneakyThrows
    public <T> ResponseEntity<Response<T>> sendInternalPost(String url, Object request, RestTemplate restTemplate, Class<T> destinationType, boolean logRequestResponse) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            if (logRequestResponse) {
                log.info("Send POST request to {} : request body: {}", url, this.getObjectMapper().writeValueAsString(request));
            }

            HttpEntity<Object> entity = new HttpEntity(request, headers);
            ResponseEntity<Object> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, Object.class, new Object[0]);
            if (logRequestResponse) {
                log.info("Response from {}; HTTP STATUS: {}; Response Body: {};", new Object[]{url, responseEntity.getStatusCode().value(), this.getObjectMapper().writeValueAsString(responseEntity.getBody())});
            }

            Response entityBody = (Response)this.getObjectMapper().convertValue(responseEntity.getBody(), Response.class);
            entityBody.setData(this.getObjectMapper().convertValue(entityBody.getData(), destinationType));
            return new ResponseEntity(entityBody, responseEntity.getHeaders(), responseEntity.getStatusCode());
        } catch (Throwable var10) {
            Throwable $ex = var10;
            throw $ex;
        }
    }

    @SneakyThrows
    public <T> ResponseEntity<T> sendPost(String url, Object request, RestTemplate restTemplate, HttpHeaders httpHeaders, Class<T> destinationType, boolean logRequestResponse) {
        try {
            if (httpHeaders == null) {
                httpHeaders = new HttpHeaders();
            }

            httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
            if (logRequestResponse) {
                log.info("Send POST request to {} ; request header: {};  request body: {}", new Object[]{url, httpHeaders.toString(), this.getObjectMapper().writeValueAsString(request)});
            }

            HttpEntity<Object> entity = new HttpEntity(request, httpHeaders);
            ResponseEntity<Object> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, Object.class, new Object[0]);
            if (logRequestResponse) {
                log.info("Response from {}; HTTP STATUS: {}; Response Body: {};", new Object[]{url, responseEntity.getStatusCode().value(), this.getObjectMapper().writeValueAsString(responseEntity.getBody())});
            }

            T entityBody = this.getObjectMapper().convertValue(responseEntity.getBody(), destinationType);
            return new ResponseEntity(entityBody, responseEntity.getHeaders(), responseEntity.getStatusCode());
        } catch (Throwable var10) {
            Throwable $ex = var10;
            throw $ex;
        }
    }

    @SneakyThrows
    public <T> ResponseEntity<T> sendGet(String url, RestTemplate restTemplate, HttpHeaders httpHeaders, Class<T> destinationType, boolean logRequestResponse) {
        try {
            if (httpHeaders == null) {
                httpHeaders = new HttpHeaders();
            }

            httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
            if (logRequestResponse) {
                log.info("Send GET request to {}", url);
            }

            HttpEntity<T> entity = new HttpEntity((Object)null, httpHeaders);
            ResponseEntity responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Object.class, new Object[0]);
            if (logRequestResponse) {
                log.info("Response from {}; HTTP STATUS: {}; Response Body: {};", new Object[]{url, responseEntity.getStatusCode().value(), this.getObjectMapper().writeValueAsString(responseEntity.getBody())});
            }

            T entityBody = this.getObjectMapper().convertValue(responseEntity.getBody(), destinationType);
            return new ResponseEntity(entityBody, responseEntity.getHeaders(), responseEntity.getStatusCode());
        } catch (Throwable var9) {
            Throwable $ex = var9;
            throw $ex;
        }
    }
}
