package com.coupons.logic;

import com.coupons.dto.CustomerDto;
import com.coupons.dto.UserDto;
import com.coupons.entities.Customer;
import com.coupons.constants.Constants;
import com.coupons.dal.ICustomersDal;
import com.coupons.enums.ErrorType;
import com.coupons.enums.UserType;
import com.coupons.exceptions.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomersLogic {
    private ICustomersDal customersDal;
    private UsersLogic usersLogic;


    @Autowired
    public CustomersLogic(ICustomersDal customersDal , UsersLogic usersLogic) {
        this.customersDal = customersDal;
        this.usersLogic = usersLogic;
    }


    public void addCustomer(Customer customer) throws ServerException {
        customer.getUser().setUserType(UserType.CUSTOMER);
        validateCustomer(customer);
        usersLogic.validateUser(customer.getUser());
        usersLogic.validateUserNameAvailability(customer.getUser().getUserName());
        try {
            customersDal.save(customer);
        }catch (Exception e){
            throw new ServerException("Error in creating customer: " + customer.getFirstName() + " " + customer.getLastName(), ErrorType.GENERAL_ERROR);
        }
    }

    public void updateCustomer(Customer customer) throws ServerException {
        validateCustomer(customer);
        usersLogic.validateUser(customer.getUser());
        usersLogic.validateUserNameAvailability(customer.getUser().getUserName() , customer.getUser().getId());
        try {
            customersDal.save(customer);
        }catch (Exception e){
            throw new ServerException("Error in updating customer: " + customer.getId(), ErrorType.GENERAL_ERROR);
        }
    }

    public void removeCustomer(long customerId) throws ServerException {
        try {
            customersDal.deleteById(customerId);
        }catch (Exception e){
            throw new ServerException("Error in removing Customer" + customerId, ErrorType.GENERAL_ERROR);
        }
    }

    public CustomerDto getCustomer(int customerId) throws ServerException {
        try {
            CustomerDto customer = customersDal.findById(customerId);
            return customer;
        }catch (Exception e){
            throw new ServerException("Error in getting Customer" + customerId, ErrorType.GENERAL_ERROR);
        }

    }

    public List<CustomerDto> getAllCustomers(int page) throws ServerException {
        Pageable paging = PageRequest.of(page - 1 , Constants.RECORDS_PER_PAGE);
        try {
            List<CustomerDto> customers = customersDal.findAllCoupons(paging);
            return customers;
        }catch (Exception e){
            throw new ServerException("Error in getting all Customers", ErrorType.GENERAL_ERROR);
        }
    }

    private void validateCustomer(Customer customer) throws ServerException {
        validateAddress(customer.getAddress());
        validatePhone(customer.getPhoneNumber());
    }

    private void validateAddress(String address) throws ServerException {
        if (address.isBlank()) {
            throw new ServerException("Address is is null", ErrorType.ADDRESS_IS_NULL);
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

}
