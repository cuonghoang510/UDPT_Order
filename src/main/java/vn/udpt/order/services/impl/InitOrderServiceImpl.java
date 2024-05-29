package vn.udpt.order.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.udpt.order.factory.PaymentProcessFactory;
import vn.udpt.order.factory.impl.PaymentProcessService;
import vn.udpt.order.models.Item;
import vn.udpt.order.models.enums.APIStatus;
import vn.udpt.order.models.enums.OrderStatus;
import vn.udpt.order.models.exception.DefaultException;
import vn.udpt.order.models.merchantProfile.response.MerchantProfileResponse;
import vn.udpt.order.models.payment.response.PaymentSubmissionResponse;
import vn.udpt.order.models.request.InitOrderRequest;
import vn.udpt.order.models.response.InitOrderResponse;
import vn.udpt.order.models.userProfile.response.UserProfileResponse;
import vn.udpt.order.persistences.entites.Event;
import vn.udpt.order.persistences.entites.Order;
import vn.udpt.order.services.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class InitOrderServiceImpl implements InitOrderService {

    private final OrderService orderService;
    private final UserService userService;
    private final MerchantService merchantService;
    private final EventService eventService;
    private final PaymentProcessFactory paymentProcessFactory;

    private static final long experiodTime = 60 * 60L;

    @Override
    @SneakyThrows
    public InitOrderResponse initOrder(InitOrderRequest request) {

        UserProfileResponse userProfile = validateUserProfile(userService.getUserProfile(request.getUserId()));

        Event event = validateEvent(eventService.getById(request.getEventId()));

        MerchantProfileResponse merchantProfile = validateMerchantProfile(merchantService.getMerchantProfile(event.getMerchantId()));

        Order order = orderService.save(submitOrder(request, userProfile, event, merchantProfile));

        PaymentProcessService paymentProcessService = paymentProcessFactory.getPaymentProcess(order.getPaymentMethod());

        PaymentSubmissionResponse paymentSubmissionResponse = paymentProcessService.processPayment(paymentProcessService.createPaymentHandlerInput(order));

        return createInitOrderResponse(order, event, paymentSubmissionResponse);
    }

    private Order submitOrder(InitOrderRequest request, UserProfileResponse userProfile, Event event, MerchantProfileResponse merchantProfile) {
        Order order = Order.builder()
                .id(UUID.randomUUID().toString())
                .merchantId(merchantProfile.getId())
                .userId(userProfile.getId())
                .eventId(event.getId())
                .quantity(request.getQuantity())
                .amount(event.getPrice() * request.getQuantity())
                .paymentMethod(request.getPaymentMethod())
                .description(request.getDescription())
                .callbackUrl(request.getCallbackUrl())
                .fullName(userProfile.getFirstName() + " " + userProfile.getLastName())
                .phoneNumber(userProfile.getPhoneNumber())
                .email(userProfile.getEmail())
                .status(OrderStatus.CREATED)
                .createdDate(Instant.now())
                .expiredDate(Instant.now().plusSeconds(experiodTime))
                .startEventDate(event.getStartDate())
                .promotionId(request.getPromotionId())
                .price(event.getPrice())
                .build();

        log.info("Save order:{}", order);

        return orderService.save(order);
    }

    private InitOrderResponse createInitOrderResponse(Order order,Event event,PaymentSubmissionResponse paymentSubmissionResponse) {
        return InitOrderResponse.builder()
                .orderId(order.getId())
                .fullName(order.getFullName())
                .phoneNumber(order.getPhoneNumber())
                .email(order.getEmail())
                .status(order.getStatus())
                .createdAt(order.getCreatedDate())
                .paymentUrl(paymentSubmissionResponse.getPaymentUrl())
                .productList(List.of(Item.builder().productId(event.getId())
                .price(event.getPrice())
                .quantity(order.getQuantity()).build()))
                .paymentMethod(order.getPaymentMethod())
                .build();
    }

    private MerchantProfileResponse validateMerchantProfile(MerchantProfileResponse merchantProfile) {

        if(merchantProfile == null) {
            log.error("Merchant profile not found");
            throw new DefaultException(APIStatus.MERCHANT_NOT_FOUND);
        }

        return merchantProfile;
    }

    private Event validateEvent(Event event) {
        if(event == null) {
            log.error("Event not found");
            throw new DefaultException(APIStatus.EVENT_NOT_FOUND);
        }

        if(event.isOutOfStock()) {
            log.info("Event is out of stock");
            throw new DefaultException(APIStatus.EVENT_OUT_OF_STOCK);
        }

        if(event.isUnAvailable()) {
            log.info("Event is unavailable because of status invalid or expired for event:{}", event);
            throw new DefaultException(APIStatus.EVENT_INVALID);
        }

        return event;
    }

    private UserProfileResponse validateUserProfile(UserProfileResponse userProfile) {

        if(userProfile == null) {
            log.error("User profile not found");
            throw new DefaultException(APIStatus.USER_NOT_FOUND);
        }

        return userProfile;

    }

}
