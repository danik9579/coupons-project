package com.coupons.entities;



import com.coupons.enums.Category;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Coupons")
public class Coupon {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "NAME" ,  nullable = false)
    private String name;

    @Column(name = "PRICE" , nullable = false)
    private int price;

    @Column(name = "DESCRIPTION" , nullable = false)
    private String description;

    @Column(name = "START_DATE" ,  nullable = false)
    private Date startDate;

    @Column(name = "END_DATE" ,  nullable = false)
    private Date endDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "company_id" , nullable = false)
    private Company company;

    @OneToMany(mappedBy = "coupon",cascade = CascadeType.REMOVE)
    private List<Purchase> purchases;

    public Coupon() {
    }

    public Coupon(long id, String name , int price , String description , Date startDate , Date endDate , Category category ) {
        this(name ,price ,description ,startDate ,endDate ,category );
        this.id = id;
    }

    public Coupon(String name, int price, String description, Date startDate, Date endDate, Category category ) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", categoryId=" + category +
                '}';
    }
}
