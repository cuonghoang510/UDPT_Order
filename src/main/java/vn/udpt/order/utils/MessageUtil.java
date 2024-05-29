package vn.udpt.order.utils;

import org.springframework.context.MessageSource;
import vn.udpt.order.models.enums.Language;

import java.util.Locale;

public class MessageUtil {
    private MessageUtil() {
    }

    /**
     * To message string.
     *
     * @param messageBundle the message bundle
     * @param language      the language
     * @param messageSource the message source
     * @return the string
     */
    public static String toMessage(String messageBundle, Language language, MessageSource messageSource) {
        return messageSource.getMessage(messageBundle, new Object[0], new Locale(language.name()));
    }

    /**
     * To message string.
     *
     * @param messageBundle the message bundle
     * @param language      the language
     * @param messageSource the message source
     * @param args          the args
     * @return the string
     */
    public static String toMessage(String messageBundle, Language language, MessageSource messageSource, Object... args) {
        return messageSource.getMessage(messageBundle, args, new Locale(language.name()));
    }
}
