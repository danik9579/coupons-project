package com.coupons.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Purchases")
public class Purchase {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "AMOUNT" , nullable = false)
    private int amount;

    @Column(name = "TIMESTAMP" , nullable = false)
    private Date timeStamp;

    @ManyToOne
    @JoinColumn(name = "customer_id" , nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "coupon_id" , nullable = false)
    private Coupon coupon;

    public Purchase() {
    }

    public Purchase(long id, int amount, Date date) {
        this(amount ,date);
        this.id = id;
    }

    public Purchase(int amount, Date date) {
        this.amount = amount;
        this.timeStamp = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", amount=" + amount +
                ", date=" + timeStamp +
                '}';
    }
}
