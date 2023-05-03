package com.coupons.logic;

import com.coupons.entities.Coupon;
import com.coupons.constants.Constants;
import com.coupons.dal.ICouponsDal;
import com.coupons.dto.CouponDto;
import com.coupons.enums.Category;
import com.coupons.enums.ErrorType;
import com.coupons.exceptions.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CouponsLogic {

    private ICouponsDal couponsDal;

    @Autowired
    public CouponsLogic(ICouponsDal couponsDal) {
        this.couponsDal = couponsDal;
    }

    public void addCoupon(Coupon coupon) throws ServerException {
        validateCoupon(coupon);
        try {
            couponsDal.save(coupon);
        }catch (Exception e){
            throw new ServerException("Error in creating Coupon " + coupon.getName(), ErrorType.GENERAL_ERROR);
        }
    }

    public void removeCoupon(long couponId) throws ServerException {
        try {
            couponsDal.deleteById(couponId);
        }catch (Exception e){
            throw new ServerException("Error in removing Coupon" + couponId, ErrorType.GENERAL_ERROR);
        }
    }

    public void updateCoupon(Coupon coupon) throws ServerException {
        validateCoupon(coupon);
        try {
            couponsDal.save(coupon);
        }catch (Exception e){
            throw new ServerException("Error in updating Coupon" + coupon.getName(), ErrorType.GENERAL_ERROR);
        }
    }

    public CouponDto getCoupon(long couponId) throws ServerException {
        try {
            CouponDto coupon = couponsDal.findById(couponId);
            return coupon;
        }catch (Exception e){
            throw new ServerException("Error in getting Coupon" + couponId, ErrorType.GENERAL_ERROR);
        }
    }

    public List<CouponDto> getAllCoupons(int page) throws ServerException {
        Pageable paging = PageRequest.of(page - 1 , Constants.RECORDS_PER_PAGE);
        try {
            List<CouponDto> coupons = couponsDal.findAllCoupons(paging);
            return coupons;
        }catch (Exception e){
            throw new ServerException("Error in getting all Coupons", ErrorType.GENERAL_ERROR);
        }
    }

    public List<CouponDto> getAllCouponsByCategory(int page, Category category) throws ServerException {
        Pageable paging = PageRequest.of(page - 1 , Constants.RECORDS_PER_PAGE);
        try {
            List<CouponDto> coupons = couponsDal.findAllByCategory(paging , category);
            return coupons;
        }catch (Exception e){
            throw new ServerException("Error in getting all Coupons by category", ErrorType.GENERAL_ERROR);
        }
    }

    public List<CouponDto> getAllCouponsByPriceRange(int page, int minPrice, int maxPrice) throws ServerException {
        Pageable paging = PageRequest.of(page - 1 , Constants.RECORDS_PER_PAGE);
        try {
            List<CouponDto> coupons = couponsDal.findAllByPriceRange(paging , minPrice , maxPrice);
            return coupons;
        }catch (Exception e){
            throw new ServerException("Error in getting all Coupons by price range", ErrorType.GENERAL_ERROR);
        }
    }

    public List<CouponDto> getAllCouponsOfCompany(int page, int companyId) throws ServerException {
        Pageable paging = PageRequest.of(page - 1 , Constants.RECORDS_PER_PAGE);
        try {
            List<CouponDto> coupons = couponsDal.findAllByCompanyId(paging, companyId);
            return coupons;
        }catch (Exception e){
            throw new ServerException("Error in getting all Coupons of a company", ErrorType.GENERAL_ERROR);
        }
    }

    public List<CouponDto> getAllCouponsOfCompanyByCategory(int page, int companyId, Category category) throws ServerException {
        Pageable paging = PageRequest.of(page - 1 , Constants.RECORDS_PER_PAGE);
        try {
            List<CouponDto> coupons = couponsDal.findAllOfCompanyByCategory(paging , companyId , category);
            return coupons;
        }catch (Exception e){
            throw new ServerException("Error in getting all Coupons a company by category", ErrorType.GENERAL_ERROR);
        }
    }

    public List<CouponDto> getAllCouponsOfCompanyByPriceRange(int page, int companyId, int minPrice, int maxPrice) throws ServerException {
        Pageable paging = PageRequest.of(page - 1 , Constants.RECORDS_PER_PAGE);
        try {
            List<CouponDto> coupons = couponsDal.findAllCouponsOfCompanyByPriceRange(paging, companyId , minPrice , maxPrice);
            return coupons;
        }catch (Exception e){
            throw new ServerException("Error in getting all Coupons of a company by price range", ErrorType.GENERAL_ERROR);
        }
    }

    private void validateCoupon(Coupon coupon) throws ServerException {
        validatePrice(coupon.getPrice());
        validateDate(coupon.getStartDate(), coupon.getEndDate());
    }

    private void validateDate(Date startDate, Date endDate) throws ServerException {
        if (!startDate.before(endDate)) {
            throw new ServerException("The start date is: " + startDate + " ,and the end date is: " + endDate, ErrorType.INVALID_DATES);
        }
    }

    private void validatePrice(int price) throws ServerException {
        if (price <= 0) {
            throw new ServerException("The price is: " + price, ErrorType.INVALID_PRICE);
        }
    }

    public void removeExpiredCoupons(Date date){
        couponsDal.deleteByEndDateBefore(date);
    }

}

