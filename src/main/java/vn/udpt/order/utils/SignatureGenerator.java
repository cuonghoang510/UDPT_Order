package vn.udpt.order.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class SignatureGenerator {

//    public static String momoRequestData(MomoInitPaymentRequest request) {
//        return Parameter.ACCESS_KEY + "=" + MomoConstant.ACCESS_KEY + "&" +
//                Parameter.AMOUNT + "=" + request.getAmount() + "&" +
//                Parameter.EXTRA_DATA + "=" + request.getExtraData() + "&" +
//                Parameter.IPN_URL + "=" + request.getIpnUrl() + "&" +
//                Parameter.ORDER_ID + "=" + request.getOrderId() + "&" +
//                Parameter.ORDER_INFO + "=" + request.getOrderInfo() + "&" +
//                Parameter.PARTNER_CODE + "=" + MomoConstant.PARTNER_CODE + "&" +
//                Parameter.REDIRECT_URL + "=" + request.getRedirectUrl() + "&" +
//                Parameter.REQUEST_ID + "=" + request.getRequestId() + "&" +
//                Parameter.REQUEST_TYPE + "=" + request.getRequestType();
//    }

    public static String generateHmacSHA256Signature(String secretKey, String data) {
        try {
            Mac hmacSHA256 = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
            hmacSHA256.init(secretKeySpec);
            byte[] hash = hmacSHA256.doFinal(data.getBytes());

            StringBuilder result = new StringBuilder();
            for (byte b : hash) {
                result.append(String.format("%02x", b));
            }
            return result.toString();
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
            return null;
        }
    }
}
