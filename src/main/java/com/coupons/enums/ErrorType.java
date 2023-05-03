package com.coupons.enums;

public enum ErrorType {

    GENERAL_ERROR(600, "Something went wrong! please try again later.", true),
    SQL_ERROR(601, "SQL error", false),
    COMPANY_NAME_IS_NULL(602, "Enter a Company name please.", false),
    INVALID_COMPANY_NAME_ALREADY_EXIST(603, "This company name already Exist.", false),
    INVALID_USERNAME(604, "The user name is incorrect or invalid. Please correct and try again.", false),
    USERNAME_IS_NULL(605, "Enter your user name please.", false),
    INVALID_USERNAME_ALREADY_EXIST(606, "This user name already Exist.", false),
    INVALID_PASSWORD_MISSING_CAPITAL_LETTER(607, "The password is incorrect or invalid. Password must contain at least one capital letter , Please correct and try again.", false),
    INVALID_PASSWORD_MISSING_LOWER_CASE_LETTER(608, "The password is incorrect or invalid. Password must contain at least one lower case letter , Please correct and try again.", false),
    INVALID_PASSWORD_MISSING_NUMBER(609, "The password is incorrect or invalid. Password must contain at least one number , Please correct and try again.", false),
    PASSWORD_IS_NULL(610, "Enter your password please.", false),
    FAILED_LOGIN(611, "The user name or password are incorrect or invalid, Please correct and try again.", false),
    ADDRESS_IS_NULL(612, "Enter your address please.", false),
    INVALID_PRICE(613, "The price is incorrect or invalid. Please correct and try again.", false),
    INVALID_PHONE_NUMBER(614, "The phone number is incorrect or invalid. Please correct and try again.", false),
    PHONE_NUMBER_IS_NULL(615, "Enter your phone number please.", false),
    INVALID_DATES(616, "The start date is later than the end date.", false),
    INVALID_PASSWORD_TOO_SHORT(617, "The password is too short.", false),
    INVALID_PASSWORD_TOO_LONG(618, "The password is too long.", false);



    private int errorNumber;
    private String errorMessage;
    private boolean isShowStackTrace;

    ErrorType(int errorNumber, String errorMessage, boolean isShowStackTrace) {
        this.errorNumber = errorNumber;
        this.errorMessage = errorMessage;
        this.isShowStackTrace = isShowStackTrace;
    }

    ErrorType(int errorNumber, String errorMessage) {
        this.errorNumber = errorNumber;
        this.errorMessage = errorMessage;
    }

    ErrorType(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getErrorNumber() {
        return errorNumber;
    }

    public boolean isShowStackTrace() {
        return isShowStackTrace;
    }
}

