package com.coupons.entities;

import javax.persistence.*;
import com.coupons.enums.UserType;

@Entity
@Table(name="Users")
public class User {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "USER_NAME" , unique = true , nullable = false)
    private String userName;

    @Column(name = "PASSWORD" , nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType userType;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;


    public User() {
    }

    public void setId(long id) {
        this.id = id;
    }



    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }



    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", type='" + userType + '\'' +
                '}';
    }
}


