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
import vn.udpt.order.models.merchantProfile.response.MerchantProfileResponse;
import vn.udpt.order.services.HttpService;
import vn.udpt.order.services.MerchantService;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class MerchantServiceImpl implements MerchantService {


    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper;

    @Value("${merchant-profile.base-url}")
    private String merchantProfileBaseUrl;

    @Override
    @SneakyThrows
    public MerchantProfileResponse getMerchantProfile(String merchantId) {

        ResponseEntity<Object> merchantProfileResponse = restTemplate.exchange(merchantProfileBaseUrl + "/" + merchantId, HttpMethod.GET,new HttpEntity<>(new HttpHeaders()), Object.class);

        if(merchantProfileResponse.getStatusCode().is4xxClientError()) {
            log.error("Client error when get merchant profile with merchantId: {}", merchantId);
            return null;
        }

        if(merchantProfileResponse.getStatusCode().is5xxServerError()) {
            log.error("Server error when get merchant profile with merchantId: {}", merchantId);
            return null;
        }

        var response = objectMapper.convertValue(merchantProfileResponse.getBody(), Response.class);

        return objectMapper.convertValue(response.getData(), MerchantProfileResponse.class);
    }
}
