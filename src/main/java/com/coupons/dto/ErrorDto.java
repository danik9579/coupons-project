package com.coupons.dto;

public class ErrorDto {
    private int ErrorNumber;
    private String ErrorMessage;

    public ErrorDto(int errorNumber, String errorMessage) {
        ErrorNumber = errorNumber;
        ErrorMessage = errorMessage;
    }

    public int getErrorNumber() {
        return ErrorNumber;
    }

    public void setErrorNumber(int errorNumber) {
        ErrorNumber = errorNumber;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "ErrorDto{" +
                "ErrorNumber=" + ErrorNumber +
                ", ErrorMessage='" + ErrorMessage + '\'' +
                '}';
    }
}
