package vn.udpt.order.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Language {
    /**
     * Vi language.
     */
    vi,
    /**
     * En language.
     */
    en;

    @JsonCreator
    public static Language fromValue(String value) {
        return "en".equals(value) ? en : vi;
    }
}
