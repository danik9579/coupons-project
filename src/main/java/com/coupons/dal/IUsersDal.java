package com.coupons.dal;

import com.coupons.dto.SuccessfulLoginData;
import com.coupons.dto.UserDto;
import com.coupons.entities.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUsersDal extends CrudRepository<User, Long> {
    @Query("SELECT NEW com.coupons.dto.SuccessfulLoginData(u.id, u.userType, u.company.id)" +
            "FROM User u WHERE u.userName= :userName AND u.password= :password")
    SuccessfulLoginData login(@Param("userName")String userName , @Param("password")String password);

    @Query("SELECT NEW com.coupons.dto.UserDto(u.id, u.userName, u.userType, u.company.name)" +
            "FROM User u LEFT JOIN Company com ON u.company.id = com.id WHERE u.id= :userId")
    UserDto findById(@Param("userId")long userId);

    @Query("SELECT NEW com.coupons.dto.UserDto(u.id, u.userName, u.userType, u.company.name)" +
            "FROM User u LEFT JOIN Company com ON u.company.id = com.id")
    List<UserDto> findAll(Pageable paging);
    boolean existsByUserName(String userName);

    @Query("select count(u.userName)>0 FROM User u WHERE u.userName= :userName AND u.id!= :id")
    boolean existsByUserNameInDifferentId(@Param("userName")String userName, @Param("id")long id);
}
