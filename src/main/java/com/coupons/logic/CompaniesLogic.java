package com.coupons.logic;

import com.coupons.dto.CompanyDto;
import com.coupons.entities.Company;
import com.coupons.constants.Constants;
import com.coupons.dal.ICompaniesDal;
import com.coupons.enums.ErrorType;
import com.coupons.exceptions.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompaniesLogic {
    private ICompaniesDal companiesDal;

    @Autowired
    public CompaniesLogic(ICompaniesDal companiesDal) {
        this.companiesDal = companiesDal;
    }

    public void addCompany(Company company) throws ServerException {
        validateCompany(company);
        if(companiesDal.existsByName(company.getName())){
            throw new ServerException("The company name is exist " + company.getName(), ErrorType.INVALID_COMPANY_NAME_ALREADY_EXIST);
        }
        try {
            companiesDal.save(company);
        }catch (Exception e){
            throw new ServerException("Error in creating Company" + company.getName(), ErrorType.GENERAL_ERROR);
        }
    }

    public void removeCompany(long companyId) throws ServerException {
        try {
            companiesDal.deleteById(companyId);
        }catch (Exception e){
            throw new ServerException("Error in removing Company" + companyId, ErrorType.GENERAL_ERROR);
        }
    }

    public void updateCompany(Company company) throws ServerException {
        validateCompany(company);
        if(companiesDal.existsByNameInDifferentId(company.getName() , company.getId())){
            throw new ServerException("The company name is exist " + company.getName(), ErrorType.INVALID_COMPANY_NAME_ALREADY_EXIST);
        }
        try {
            companiesDal.save(company);
        }catch (Exception e){
            throw new ServerException("Error in updating Company" + company.getName(), ErrorType.GENERAL_ERROR);
        }
    }

    public CompanyDto getCompany(long companyId) throws ServerException {
        try {
            CompanyDto company = companiesDal.findById(companyId);
            return company;
        }catch (Exception e){
            throw new ServerException("Error in getting Company" + companyId, ErrorType.GENERAL_ERROR);
        }
    }

    public List<CompanyDto> getAllCompanies(int page) throws ServerException {
        Pageable paging = PageRequest.of(page - 1 , Constants.RECORDS_PER_PAGE);
        try {
            List<CompanyDto> companiesDto = companiesDal.findAllCompanies(paging);
            return companiesDto;
        }catch (Exception e){
            throw new ServerException("Error in getting all Company", ErrorType.GENERAL_ERROR);
        }
    }

    private void validateCompany(Company company) throws ServerException {
        validateName(company.getName());
        validateAddress(company.getAddress());
        validatePhone(company.getPhoneNumber());
    }

    private void validateName(String name) throws ServerException {
        if (name.isBlank()) {
            throw new ServerException("Company name is invalid " + name, ErrorType.COMPANY_NAME_IS_NULL);
        }
    }

    private void validatePhone(String phoneNumber) throws ServerException {
        if (phoneNumber.isBlank()) {
            throw new ServerException("Phone number is null", ErrorType.PHONE_NUMBER_IS_NULL);
        }
        if (phoneNumber.length() != 10) {
            throw new ServerException("Phone number invalid: " + phoneNumber, ErrorType.INVALID_PHONE_NUMBER);
        }
    }

    private void validateAddress(String address) throws ServerException {
        if (address.isBlank()) {
            throw new ServerException("Address is is null", ErrorType.ADDRESS_IS_NULL);
        }
    }

}
