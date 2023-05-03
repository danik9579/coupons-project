package com.coupons.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Companies")
public class Company {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "NAME" , unique = true , nullable = false)
    private String name;

    @Column(name = "ADDRESS" , nullable = false)
    private String address;

    @Column(name = "PHONE_NUMBER" , nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "company" , cascade = CascadeType.REMOVE)
    private List<Coupon> coupons;

    @OneToMany(mappedBy = "company" , cascade = CascadeType.REMOVE)
    private List<User> users;

    public Company() {
    }


    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Companies{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
