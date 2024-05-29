package vn.udpt.order.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.udpt.order.models.dto.Response;
import vn.udpt.order.models.userProfile.response.UserProfileResponse;
import vn.udpt.order.services.HttpService;
import vn.udpt.order.services.UserService;

import java.util.HashMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final HttpService httpService;
    private final ObjectMapper objectMapper;

    @Value("${user-profile.base-url}")
    private String userProfileBaseUrl;

    @Override
    @SneakyThrows
    public UserProfileResponse getUserProfile(String userId) {
        ResponseEntity<Object> userProfileResponse = httpService.get(userProfileBaseUrl + "/" + userId, new HashMap<>(), new HttpHeaders());

        if(userProfileResponse.getStatusCode().is4xxClientError()) {
            log.error("Client error when get user profile with userId: {}", userId);
            return null;
        }

        if(userProfileResponse.getStatusCode().is5xxServerError()) {
            log.error("Server error when get user profile with userId: {}", userId);
            return null;
        }

        Response<UserProfileResponse> response = objectMapper.convertValue(userProfileResponse.getBody(), Response.class);

        return response.getData();
    }
}
