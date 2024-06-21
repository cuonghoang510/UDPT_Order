package vn.udpt.order.utils;

import vn.udpt.order.models.momo.request.MomoOrderRequest;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class SignatureGenerator {

    public static String momoRequestData(MomoOrderRequest request) {
        return MomoConstant.ACCESS_KEY + "=" + "klm05TvNBzhg7h7j" + "&" +
                MomoConstant.AMOUNT + "=" + request.getAmount() + "&" +
                MomoConstant.EXTRA_DATA + "=" + request.getExtraData() + "&" +
                MomoConstant.IPN_URL + "=" + request.getIpnUrl() + "&" +
                MomoConstant.ORDER_ID + "=" + request.getOrderId() + "&" +
                MomoConstant.ORDER_INFO + "=" + request.getOrderInfo() + "&" +
                MomoConstant.PARTNER_CODE + "=" + "MOMOBKUN20180529" + "&" +
                MomoConstant.REDIRECT_URL + "=" + request.getRedirectUrl() + "&" +
                MomoConstant.REQUEST_ID + "=" + request.getRequestId() + "&" +
                MomoConstant.REQUEST_TYPE + "=" + request.getRequestType();
    }

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
