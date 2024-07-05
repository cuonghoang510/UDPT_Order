package vn.udpt.order.models.email.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SendEmailRequest {
    private String sendName;

    private String receiveName;

    private String from;

    private String to;

    private String subject;

    private String paymentUrl;
}
