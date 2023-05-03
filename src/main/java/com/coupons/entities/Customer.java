package com.coupons.entities;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="Customers")
public class Customer {
    @Id
    @Column(name = "id" , nullable = false)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    private User user;

    @Column(name = "FIRST_NAME" ,  nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME" ,  nullable = false)
    private String lastName;

    @Column(name = "ADDRESS" ,  nullable = false)
    private String address;

    @Column(name = "PHONE_NUMBER" ,  nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "customer" , cascade = CascadeType.REMOVE)
    private List<Purchase> purchases;


    public Customer() {
    }

    public Customer(User user, String firstName, String lastName, String address, String phoneNumber) {
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        return "Customer{" +
                "user=" + user +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
