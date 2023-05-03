package com.coupons.dto;

import com.coupons.enums.UserType;

public class SuccessfulLoginData {
    private long id;
    private UserType userType;
    private Long companyId;

    public SuccessfulLoginData(Long id, UserType userType, Long companyId) {
        this.id = id;
        this.userType = userType;
        this.companyId = companyId;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                '}';
    }
}
