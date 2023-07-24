package com.example.product_manager_mvc.utils;

import java.util.regex.Pattern;

public class Validate {
    private static final String REGEX_NAME = "^[A-Za-z0-9\\s][A-Za-z0-9]{4,19}$";
    private static final String REGEX_PRICE = "^[1-9][0-9]{3,7}\\.?\\d{1,2}$";
    private static final String REGEX_ADDRESS = "^[a-zA-Z0-9\\s.,]{3,245}$";

    public static boolean isNameValid(String name) {
        return Pattern.matches(REGEX_NAME, name);
    }

    public static boolean isPriceValid( String price) {
        return Pattern.matches(REGEX_PRICE, price);
    }
    public static boolean isAddressValid(String address) {
        return Pattern.matches(REGEX_ADDRESS, address);
    }
}
