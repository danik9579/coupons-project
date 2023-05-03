package com.coupons.dal;

import com.coupons.dto.CouponDto;
import com.coupons.entities.Coupon;
import com.coupons.enums.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.List;


public interface ICouponsDal extends CrudRepository<Coupon, Long> {


    @Transactional
    void deleteByEndDateBefore(@Param("endDate")Date endDate);

    @Query("delete from Coupon c where c.company.id = :companyId")
    void deleteAllCouponsOfCompany(@Param("companyId")long companyId);

    @Query("SELECT NEW com.coupons.dto.CouponDto(cou.id, cou.name, cou.price, cou.description, cou.startDate, cou.endDate, cou.category, cou.company.name)" +
            "FROM Coupon cou JOIN Company com ON cou.company.id = com.id WHERE cou.id= :couponId")
    CouponDto findById(@Param("couponId")long couponId);

    @Query("SELECT NEW com.coupons.dto.CouponDto(cou.id, cou.name, cou.price, cou.description, cou.startDate, cou.endDate, cou.category, cou.company.name)" +
            "FROM Coupon cou JOIN Company com ON cou.company.id = com.id")
    List<CouponDto> findAllCoupons(Pageable paging);

    @Query("SELECT NEW com.coupons.dto.CouponDto(cou.id, cou.name, cou.price, cou.description, cou.startDate, cou.endDate, cou.category, cou.company.name)" +
            "FROM Coupon cou JOIN Company com ON cou.company.id = com.id WHERE cou.company.id= :companyId")
    List<CouponDto> findAllByCompanyId(Pageable paging, @Param("companyId")long companyId);

    @Query("SELECT NEW com.coupons.dto.CouponDto(cou.id, cou.name, cou.price, cou.description, cou.startDate, cou.endDate, cou.category, cou.company.name)" +
            "FROM Coupon cou JOIN Company com ON cou.company.id = com.id WHERE cou.category= :category")
    List<CouponDto> findAllByCategory(Pageable paging, @Param("category") Category category);

    @Query("SELECT NEW com.coupons.dto.CouponDto(cou.id, cou.name, cou.price, cou.description, cou.startDate, cou.endDate, cou.category, cou.company.name)" +
            "FROM Coupon cou JOIN Company com ON cou.company.id = com.id WHERE cou.price BETWEEN :minPrice AND :maxPrice")
    List<CouponDto> findAllByPriceRange(Pageable paging, @Param("minPrice") int minPrice , @Param("maxPrice") int maxPrice);

    @Query("SELECT NEW com.coupons.dto.CouponDto(cou.id, cou.name, cou.price, cou.description, cou.startDate, cou.endDate, cou.category, cou.company.name)" +
            "FROM Coupon cou JOIN Company com ON cou.company.id = com.id WHERE cou.company.id= :companyId AND cou.category= :category")
    List<CouponDto> findAllOfCompanyByCategory(Pageable paging, @Param("companyId")long companyId , @Param("category") Category category);

    @Query("SELECT NEW com.coupons.dto.CouponDto(cou.id, cou.name, cou.price, cou.description, cou.startDate, cou.endDate, cou.category, cou.company.name)" +
            "FROM Coupon cou JOIN Company com ON cou.company.id = com.id WHERE cou.company.id= :companyId AND cou.price BETWEEN :minPrice AND :maxPrice")
    List<CouponDto> findAllCouponsOfCompanyByPriceRange(Pageable paging, @Param("companyId")long companyId , @Param("minPrice") int minPrice , @Param("maxPrice") int maxPrice);

}
