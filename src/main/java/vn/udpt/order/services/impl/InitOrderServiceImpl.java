package vn.udpt.order.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.udpt.order.factory.PaymentProcessFactory;
import vn.udpt.order.factory.impl.PaymentProcessService;
import vn.udpt.order.models.Item;
import vn.udpt.order.models.dto.Payment;
import vn.udpt.order.models.enums.APIStatus;
import vn.udpt.order.models.enums.OrderStatus;
import vn.udpt.order.models.enums.PaymentMethod;
import vn.udpt.order.models.exception.DefaultException;
import vn.udpt.order.models.merchantProfile.response.MerchantProfileResponse;
import vn.udpt.order.models.order.request.GetPaymentUrlRequest;
import vn.udpt.order.models.order.request.InitOrderV2Request;
import vn.udpt.order.models.order.response.FetchPaymentMethodResponse;
import vn.udpt.order.models.order.response.InitOrderV2Response;
import vn.udpt.order.models.payment.response.PaymentSubmissionResponse;
import vn.udpt.order.models.order.request.InitOrderRequest;
import vn.udpt.order.models.order.response.InitOrderResponse;
import vn.udpt.order.models.userProfile.response.UserProfileResponse;
import vn.udpt.order.persistences.entites.Event;
import vn.udpt.order.persistences.entites.Order;
import vn.udpt.order.persistences.entites.OrderPaymentInfo;
import vn.udpt.order.persistences.entites.Route;
import vn.udpt.order.persistences.repositories.OrderPaymentInfoRepository;
import vn.udpt.order.persistences.repositories.RouteRepository;
import vn.udpt.order.services.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
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


    private final RouteRepository routeRepository;
    private final OrderPaymentInfoRepository orderPaymentInfoRepository;

    private static final long experiodTime = 60 * 60L;

    @Override
    @SneakyThrows
    public InitOrderResponse initOrder(InitOrderRequest request) {

        UserProfileResponse userProfile = validateUserProfile(userService.getUserProfile(request.getUserId()));

        Event event = validateEvent(eventService.getById(request.getEventId()));

//        MerchantProfileResponse merchantProfile = validateMerchantProfile(merchantService.getMerchantProfile(event.getMerchantId()));

        Order order = orderService.save(submitOrder(request, userProfile, event, null));

        PaymentProcessService paymentProcessService = paymentProcessFactory.getPaymentProcess(order.getPaymentMethod());

        PaymentSubmissionResponse paymentSubmissionResponse = paymentProcessService.processPayment(paymentProcessService.createPaymentHandlerInput(order));

        return createInitOrderResponse(order, event, paymentSubmissionResponse);
    }

    @Override
    @SneakyThrows
    public InitOrderV2Response initOrderV2(InitOrderV2Request request) {

        Route route = routeRepository.findById(request.getRouteId()).orElseThrow(() -> new DefaultException(APIStatus.ROUTE_NOT_FOUND));

        Order order = orderService.save(submitOrderV2(request, route));

        return InitOrderV2Response.builder()
                .orderId(order.getId())
                .message("success")
                .build();
    }

    @Override
    public FetchPaymentMethodResponse fetchPaymentMethod(String orderId) {
        Order order = orderService.getById(orderId);

        Route route = routeRepository.findById(order.getRouteId()).orElseThrow(() -> new DefaultException(APIStatus.ROUTE_NOT_FOUND));

        PaymentProcessService paymentProcessServiceForFundiin = paymentProcessFactory.getPaymentProcess(PaymentMethod.FUNDIIN);
        PaymentSubmissionResponse paymentSubmissionResponseForFundiin = paymentProcessServiceForFundiin.processPayment(paymentProcessServiceForFundiin.createPaymentHandlerInput(order));
        OrderPaymentInfo fundiinOrderPaymentInfo = OrderPaymentInfo.builder().id(UUID.randomUUID().toString()).orderId(orderId).paymentMethod(PaymentMethod.FUNDIIN).qrData(paymentSubmissionResponseForFundiin.getQrData()).paymentUrl(paymentSubmissionResponseForFundiin.getPaymentUrl()).build();
        orderPaymentInfoRepository.save(fundiinOrderPaymentInfo);

        PaymentProcessService paymentProcessServiceForMomo = paymentProcessFactory.getPaymentProcess(PaymentMethod.MOMO);
        PaymentSubmissionResponse paymentSubmissionResponseForMomo = paymentProcessServiceForMomo.processPayment(paymentProcessServiceForMomo.createPaymentHandlerInput(order));
        OrderPaymentInfo momoOrderPaymentInfo = OrderPaymentInfo.builder().id(UUID.randomUUID().toString()).orderId(orderId).paymentMethod(PaymentMethod.MOMO).qrData(paymentSubmissionResponseForMomo.getQrData()).paymentUrl(paymentSubmissionResponseForMomo.getPaymentUrl()).build();
        orderPaymentInfoRepository.save(momoOrderPaymentInfo);

        return FetchPaymentMethodResponse.builder()
                .orderId(order.getId())
                .fullName(order.getFullName())
                .phoneNumber(order.getPhoneNumber())
                .email(order.getEmail())
                .routeName(route.getName())
                .startLocation(route.getStartLocation())
                .destinationLocation(route.getEndLocation())
                .totalAmount(order.getAmount())
                .status(order.getStatus().name())
                .payments(List.of(Payment.builder().paymentMethodId(momoOrderPaymentInfo.getId()).orderId(order.getId()).paymentMethodName(PaymentMethod.MOMO.name()).build()
                        ,Payment.builder().paymentMethodId(fundiinOrderPaymentInfo.getId()).orderId(order.getId()).paymentMethodName(PaymentMethod.FUNDIIN.name()).build()))
                .build();
    }

    @Override
    public String getPaymentUrl(GetPaymentUrlRequest request) {
        Optional<OrderPaymentInfo> orderPaymentOptional = orderPaymentInfoRepository.findByOrderIdAndPaymentMethod(request.getOrderId(), request.getPaymentMethod());
        if(orderPaymentOptional.isEmpty()) {
            log.error("Order payment info not found with orderId:{} and paymentMethod:{}", request.getOrderId(), request.getPaymentMethod());
            throw new DefaultException(APIStatus.ORDER_NOT_FOUND);
        }
        return orderPaymentOptional.get().getQrData();
    }

    private Order submitOrderV2(InitOrderV2Request request, Route route) {
        return Order.builder()
                .id(UUID.randomUUID().toString())
                .email(request.getEmail())
                .fullName(request.getFullName())
                .phoneNumber(request.getPhoneNumber())
                .status(OrderStatus.CREATED)
                .amount(route.getPrice())
                .quantity(request.getQuantity())
                .startLocation(route.getStartLocation())
                .endLocation(route.getEndLocation())
                .routeId(route.getId())
                .build();
    }

    private Order submitOrder(InitOrderRequest request, UserProfileResponse userProfile, Event event, MerchantProfileResponse merchantProfile) {
        Order order = Order.builder()
                .id(UUID.randomUUID().toString())
                .merchantId(event.getMerchantId())
                .userId(userProfile.getId())
                .eventId(event.getId())
                .eventName(event.getTitle())
                .eventAddress(event.getLocation())
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
