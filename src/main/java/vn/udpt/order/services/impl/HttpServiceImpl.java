package vn.udpt.order.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Primary
public class HttpServiceImpl extends AbstractHttpService{
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    protected RestTemplate getRestTemplate() {
        return this.restTemplate;
    }

    protected ObjectMapper getObjectMapper() {
        return this.objectMapper;
    }

    public HttpServiceImpl(final ObjectMapper objectMapper) {
        this.restTemplate = new RestTemplate();
        this.objectMapper = objectMapper;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof HttpServiceImpl)) {
            return false;
        } else {
            HttpServiceImpl other = (HttpServiceImpl)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$restTemplate = this.getRestTemplate();
                Object other$restTemplate = other.getRestTemplate();
                if (this$restTemplate == null) {
                    if (other$restTemplate != null) {
                        return false;
                    }
                } else if (!this$restTemplate.equals(other$restTemplate)) {
                    return false;
                }

                Object this$objectMapper = this.getObjectMapper();
                Object other$objectMapper = other.getObjectMapper();
                if (this$objectMapper == null) {
                    if (other$objectMapper != null) {
                        return false;
                    }
                } else if (!this$objectMapper.equals(other$objectMapper)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof HttpServiceImpl;
    }

    public int hashCode() {
        int result = 1;
        Object $restTemplate = this.getRestTemplate();
        result = result * 59 + ($restTemplate == null ? 43 : $restTemplate.hashCode());
        Object $objectMapper = this.getObjectMapper();
        result = result * 59 + ($objectMapper == null ? 43 : $objectMapper.hashCode());
        return result;
    }

    public String toString() {
        RestTemplate var10000 = this.getRestTemplate();
        return "HttpServiceImpl(restTemplate=" + var10000 + ", objectMapper=" + this.getObjectMapper() + ")";
    }
}
