package com.coupons.dal;

import com.coupons.dto.CompanyDto;
import com.coupons.dto.CouponDto;
import com.coupons.entities.Company;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICompaniesDal extends CrudRepository<Company, Long> {
    @Query("SELECT NEW com.coupons.dto.CompanyDto(com.id, com.name, com.address, com.phoneNumber)" +
            "FROM Company com WHERE com.id= :companyId")
    CompanyDto findById(@Param("companyId")long companyId);

    @Query("SELECT NEW com.coupons.dto.CompanyDto(com.id, com.name, com.address, com.phoneNumber)" +
            "FROM Company com")
    List<CompanyDto> findAllCompanies(Pageable paging);

    boolean existsByName(String name);

    @Query("select count(co.name)>0 FROM Company co WHERE co.name= :name AND co.id!= :id")
    boolean existsByNameInDifferentId(@Param("name")String name, @Param("id")long id);
}
