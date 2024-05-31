package vn.udpt.order.models.event.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.udpt.order.models.enums.Currency;
import vn.udpt.order.models.enums.EventType;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEventRequest {
    @NotBlank
    private String merchantId;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private Instant startDate;

    @NotNull
    private Instant endDate;

    @NotBlank
    private String location;

    @NotNull
    private EventType type;

    @Min(1)
    private int minQuantity;

    @Min(1)
    private int maxQuantity;

    @Min(1)
    private int price;

    @NotNull
    private Currency currency;
}
