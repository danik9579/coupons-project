package com.coupons.dal;

import com.coupons.dto.PurchaseDto;
import com.coupons.entities.Purchase;
import com.coupons.enums.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface IPurchasesDal extends CrudRepository<Purchase , Long> {
    @Query("SELECT NEW com.coupons.dto.PurchaseDto(pu.id, pu.customer.firstName, pu.customer.lastName, pu.coupon.name, pu.amount, pu.timeStamp, pu.coupon.price, pu.coupon.category, pu.coupon.company.name)" +
            "FROM Purchase pu JOIN Customer cu ON pu.customer.id = cu.id JOIN Coupon cou ON pu.coupon.id = cou.id WHERE pu.id= :purchaseId")
    PurchaseDto findById(@Param("purchaseId")long purchaseId);

    @Query("SELECT NEW com.coupons.dto.PurchaseDto(pu.id, pu.customer.firstName, pu.customer.lastName, pu.coupon.name, pu.amount, pu.timeStamp, pu.coupon.price, pu.coupon.category, pu.coupon.company.name)" +
            "FROM Purchase pu JOIN Customer cu ON pu.customer.id = cu.id JOIN Coupon cou ON pu.coupon.id = cou.id")
    List<PurchaseDto> findAll(Pageable pageable);

    @Query("SELECT NEW com.coupons.dto.PurchaseDto(pu.id, pu.customer.firstName, pu.customer.lastName, pu.coupon.name, pu.amount, pu.timeStamp, pu.coupon.price, pu.coupon.category, pu.coupon.company.name)" +
            "FROM Purchase pu JOIN Customer cu ON pu.customer.id = cu.id JOIN Coupon cou ON pu.coupon.id = cou.id WHERE pu.customer.id= :customerId")
    List<PurchaseDto> findAllByCustomerId(@Param("customerId")long customerId , Pageable pageable);

    @Query("SELECT NEW com.coupons.dto.PurchaseDto(pu.id, pu.customer.firstName, pu.customer.lastName, pu.coupon.name, pu.amount, pu.timeStamp, pu.coupon.price, pu.coupon.category, pu.coupon.company.name)" +
            "FROM Purchase pu JOIN Customer cu ON pu.customer.id = cu.id JOIN Coupon cou ON pu.coupon.id = cou.id WHERE pu.customer.id= :customerId AND pu.coupon.category= :category")
    List<PurchaseDto> findAllByCustomerIdAndCategory(@Param("customerId")long customerId , @Param("category") Category category , Pageable pageable);



}
