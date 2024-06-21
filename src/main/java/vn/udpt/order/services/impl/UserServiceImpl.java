package vn.udpt.order.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import vn.udpt.order.models.dto.Response;
import vn.udpt.order.models.userProfile.response.UserProfileResponse;
import vn.udpt.order.services.HttpService;
import vn.udpt.order.services.UserService;

import java.util.HashMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

//    private final HttpService httpService;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${user-profile.base-url}")
    private String userProfileBaseUrl;

    @Override
    @SneakyThrows
    public UserProfileResponse getUserProfile(String userId) {

        ResponseEntity<Object> userProfileResponse = restTemplate.exchange(userProfileBaseUrl + "/" + userId, HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), Object.class);

        log.info("Response: {}", userProfileResponse);

        if(userProfileResponse.getStatusCode().is4xxClientError()) {
            log.error("Client error when get user profile with userId: {}", userId);
            return null;
        }

        if(userProfileResponse.getStatusCode().is5xxServerError()) {
            log.error("Server error when get user profile with userId: {}", userId);
            return null;
        }

        Response response = objectMapper.convertValue(userProfileResponse.getBody(), Response.class);

        return objectMapper.convertValue(response.getData(), UserProfileResponse.class);
    }
}
