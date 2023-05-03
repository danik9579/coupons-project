package com.coupons.dal;

import com.coupons.dto.CustomerDto;
import com.coupons.entities.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICustomersDal extends CrudRepository<Customer , Long> {
    @Query("SELECT NEW com.coupons.dto.CustomerDto(cu.id, cu.firstName, cu.lastName, cu.address, cu.phoneNumber)" +
            "FROM Customer cu WHERE cu.id= :customerId")
    CustomerDto findById(@Param("customerId")long customerId);

    @Query("SELECT NEW com.coupons.dto.CustomerDto(cu.id, cu.firstName, cu.lastName, cu.address, cu.phoneNumber)" +
            "FROM Customer cu")
    List<CustomerDto> findAllCoupons(Pageable paging);
}
