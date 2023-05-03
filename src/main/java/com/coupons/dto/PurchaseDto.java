package com.coupons.dto;

import com.coupons.enums.Category;

import java.util.Date;

public class PurchaseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String couponName;
    private int amount;
    private Date timeStamp;
    private int price;
    private Category category;
    private String companyName;


    public PurchaseDto(long id, String firstName, String lastName, String couponName, int amount, Date timeStamp, int price, Category category, String companyName) {
        this(id , firstName , lastName , couponName , timeStamp);
        this.amount = amount;
        this.price = price;
        this.category = category;
        this.companyName = companyName;
    }

    public PurchaseDto(long id, String firstName, String lastName, String couponName, Date timeStamp) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.couponName = couponName;
        this.timeStamp = timeStamp;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "CouponPurchase{" +
                "id=" + id +
                ", amount=" + amount +
                ", timeStamp=" + timeStamp +
                ", name='" + couponName + '\'' +
                ", price=" + price +
                ", category=" + category +
                '}';
    }
}
