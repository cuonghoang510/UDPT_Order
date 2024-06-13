package vn.udpt.order.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import vn.udpt.order.exceptions.RestTemplateExceptionHandler;
import vn.udpt.order.utils.Constant;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class RestConfiguration {

    @Value("${fundiin.base-url}")
    private String fundiinCoreBaseUrl;

    @Value("${order.rest.client.connection-timeout}")
    private int connectTimeOut;

    @Value("${order.rest.client.read-timeout}")
    private int readTimeOut;

    @Value("${fundiin.client-id}")
    private String fundiinClientId;

    @Value("${fundiin.signature}")
    private String fundiinSignature;

    @Bean(name = "restTemplate")
    RestTemplate restTemplate(RestTemplateBuilder builder) {

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectionRequestTimeout(connectTimeOut);
        factory.setConnectTimeout(readTimeOut);

        RestTemplate restTemplate =  builder
                .requestFactory(() -> factory)
                .messageConverters(mappingJackson2HttpMessageConverter())
                .setConnectTimeout(Duration.ofSeconds(connectTimeOut))
                .build();

        restTemplate.setErrorHandler(new RestTemplateExceptionHandler());
        return restTemplate;
    }

    @Bean(name = "fundiinRestTemplate")
    RestTemplate fundiinCoreRestTemplate(RestTemplateBuilder builder) {

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectionRequestTimeout(connectTimeOut);
        factory.setConnectTimeout(readTimeOut);

        RestTemplate restTemplate =  builder
                .requestFactory(() -> factory)
                .messageConverters(mappingJackson2HttpMessageConverter())
                .setConnectTimeout(Duration.ofSeconds(connectTimeOut))
                .build();

        restTemplate.setErrorHandler(new RestTemplateExceptionHandler());
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(fundiinCoreBaseUrl));
        setupHeader(restTemplate, createHeaders(Constant.CLIENT_ID, fundiinClientId));
        setupHeader(restTemplate, createHeaders(Constant.SIGNATURE, fundiinSignature));
        return restTemplate;
    }

    private void setupAuthorization(RestTemplate restTemplate, HttpHeaders headers){

        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }

        interceptors.add((request, body, execution) -> {
            request.getHeaders().addAll(headers);
            return execution.execute(request, body);
        });
        restTemplate.setInterceptors(interceptors);
    }

    private void setupHeader( RestTemplate restTemplate, HttpHeaders httpHeaders) {
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().addAll(httpHeaders);
            return execution.execute(request, body);
        });
    }

    private HttpHeaders createHeaders(String headerName, String value) {
        return new HttpHeaders() {{
            set(headerName, value);
        }};
    }

    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON,
                MediaType.APPLICATION_FORM_URLENCODED, MediaType.TEXT_PLAIN, MediaType.TEXT_HTML, MediaType.APPLICATION_OCTET_STREAM));
        converter.setDefaultCharset(StandardCharsets.UTF_8);
        return converter;
    }

}
