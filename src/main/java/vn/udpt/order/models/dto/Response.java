package vn.udpt.order.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.antlr.v4.runtime.misc.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import vn.udpt.order.models.enums.Language;
import vn.udpt.order.models.enums.Status;
import vn.udpt.order.utils.MessageUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

public class Response<T> implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(Response.class);
    private static final long serialVersionUID = 4543923542628633108L;
    @Enumerated(EnumType.STRING)
    private @NotNull Status status;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public Response(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    public Response(T data) {
        this.data = data;
        this.status = Status.SUCCESS;
        this.message = "success";
    }

    public static Response<Void> success(Language language, MessageSource messageSource) {
        return new Response(Status.SUCCESS, messageSource.getMessage(Status.SUCCESS.getMessage(), new Object[0], new Locale(language.name())));
    }

    public static Response<Object> success(Language language, MessageSource messageSource, Object data) {
        return new Response(Status.SUCCESS, messageSource.getMessage(Status.SUCCESS.getMessage(), new Object[0], new Locale(language.name())), data);
    }

    public static Response<Void> badRequest(Language language, MessageSource messageSource) {
        return new Response(Status.BAD_REQUEST, messageSource.getMessage(Status.BAD_REQUEST.getMessage(), new Object[0], new Locale(language.name())));
    }

    public static Response<Void> badRequest(Language eLanguage, MessageSource messageSource, List<String> messageBundles) {
        StringBuilder stringBuffer = new StringBuilder();
        messageBundles.forEach((messageBundle) -> {
            try {
                String message = MessageUtil.toMessage(messageBundle, eLanguage, messageSource);
                stringBuffer.append(message).append(".");
            } catch (Exception var6) {
                log.error("Can't found message bundle: {}", messageBundle);
                stringBuffer.append(messageBundle);
            }

        });
        return new Response(Status.BAD_REQUEST, stringBuffer.toString());
    }

    public static Response<Object> badRequest(Language eLanguage, MessageSource messageSource, Object data) {
        return new Response(Status.BAD_REQUEST, messageSource.getMessage(Status.BAD_REQUEST.getMessage(), new Object[0], new Locale(eLanguage.name())), data);
    }

    public static Response<Void> systemError(Language language, MessageSource messageSource) {
        return new Response(Status.SYSTEM_ERROR, messageSource.getMessage(Status.SYSTEM_ERROR.getMessage(), new Object[0], new Locale(language.name())));
    }

    public static Response<Void> timeOut(Language language, MessageSource messageSource) {
        return new Response(Status.TIME_OUT, messageSource.getMessage(Status.TIME_OUT.getMessage(), new Object[0], new Locale(language.name())));
    }

    public static Response<Void> customResponse(Language language, MessageSource messageSource, Status status) {
        return new Response(status, messageSource.getMessage(status.getMessage(), new Object[0], new Locale(language.name())));
    }

    public static Response<Object> customResponse(Language language, MessageSource messageSource, Status status, Object data) {
        return new Response(status, messageSource.getMessage(status.getMessage(), new Object[0], new Locale(language.name())), data);
    }

    public static Response<Object> bussinesResponse(Language language, MessageSource messageSource, Object data) {
        return new Response(Status.BUSINESS_ERROR, messageSource.getMessage(Status.BUSINESS_ERROR.getMessage(), new Object[0], new Locale(language.name())), data);
    }

    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException var3) {
            JsonProcessingException e = var3;
            e.printStackTrace();
            return "";
        }
    }

    public Status getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public T getData() {
        return this.data;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setData(final T data) {
        this.data = data;
    }

    public Response() {
    }

    public Response(final Status status, final String message, final T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
