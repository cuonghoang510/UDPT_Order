package vn.udpt.order.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import vn.udpt.order.models.fundiin.request.FundiinOrderRequest;

public class HeaderUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String createSignature(FundiinOrderRequest initOrderRequest) {
        String signature = null;
        try {
            signature = SignatureGenerator.generateHmacSHA256Signature(FundiinConstant.SECRETKEY,objectMapper.writeValueAsString(initOrderRequest));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return signature;
    }
}
