package com.coupons.dto;

import com.coupons.enums.UserType;

public class UserDto {
    private long id;
    private String userName;
    private UserType userType;
    private String companyName;

    public UserDto(long id, String userName, UserType userType, String companyName) {
        this.id = id;
        this.userName = userName;
        this.userType = userType;
        this.companyName = companyName;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "UserCompany{" +
                "userId=" + id +
                ", userName='" + userName + '\'' +
                ", userType=" + userType +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
