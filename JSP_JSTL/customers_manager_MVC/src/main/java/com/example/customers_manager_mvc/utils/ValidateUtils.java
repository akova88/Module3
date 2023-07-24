package com.example.customers_manager_mvc.utils;

import java.util.regex.Pattern;

public class ValidateUtils {
    private static final String REGEX_NAME = "^[A-Za-z][A-Za-z ]{7,19}$";
    private static final String REGEX_EMAIL = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,3}$";
    private static final String REGEX_ADDRESS = "^[a-zA-Z0-9\\\\s.,]{3,245}$";

    public static boolean isNameValid(String name) {
        return Pattern.matches(REGEX_NAME, name);
    }

    public static boolean isEmailValid( String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }
    public static boolean isAddressValid(String address) {
        return Pattern.matches(REGEX_ADDRESS, address);
    } 

}
