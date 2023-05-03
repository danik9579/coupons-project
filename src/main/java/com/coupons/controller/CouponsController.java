package com.coupons.controller;

import com.coupons.entities.Coupon;
import com.coupons.dto.CouponDto;
import com.coupons.enums.Category;
import com.coupons.exceptions.ServerException;
import com.coupons.logic.CouponsLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coupons")
public class CouponsController {

    private CouponsLogic couponsLogic;

    @Autowired
    public CouponsController(CouponsLogic couponsLogic){
        this.couponsLogic = couponsLogic;
    }

    @PostMapping
    public void createCoupon(@RequestBody Coupon coupon) throws ServerException {
        couponsLogic.addCoupon(coupon);
    }

    @PutMapping
    public void updateCoupon(@RequestBody Coupon coupon) throws ServerException {
        couponsLogic.updateCoupon(coupon);
    }

    @DeleteMapping("{couponId}")
    public void deleteCoupon(@PathVariable("couponId") int couponId) throws ServerException {
        couponsLogic.removeCoupon(couponId);
    }

    @GetMapping("{couponId}")
    public CouponDto getCoupon(@PathVariable("couponId") int couponId) throws ServerException {
        CouponDto coupon = couponsLogic.getCoupon(couponId);
        return coupon;
    }

    @GetMapping
    public List<CouponDto> getAllCoupons(@RequestParam("page") int page) throws ServerException {
        List<CouponDto> coupons = couponsLogic.getAllCoupons(page);
        return coupons;
    }

    @GetMapping("/byPriceRange")
    public List<CouponDto> getAllCouponsByPriceRange(@RequestParam("page") int page , @RequestParam("minPrice") int minPrice , @RequestParam("maxPrice") int maxPrice) throws ServerException {
        List<CouponDto> coupons = couponsLogic.getAllCouponsByPriceRange(page , minPrice , maxPrice);
        return coupons;
    }

    @GetMapping("/byCategory")
    public List<CouponDto> getAllCouponsByCategory(@RequestParam("page") int page , @RequestParam("category")Category category) throws ServerException {
        List<CouponDto> coupons = couponsLogic.getAllCouponsByCategory(page , category);
        return coupons;
    }

    @GetMapping("/ofCompany")
    public List<CouponDto> getAllCouponsOfCompany(@RequestParam("page") int page , @RequestParam("companyId")int companyId) throws ServerException {
        List<CouponDto> coupons = couponsLogic.getAllCouponsOfCompany(page , companyId);
        return coupons;
    }

    @GetMapping("/ofCompanyByPriceRange")
    public List<CouponDto> getAllCouponsOfCompanyByPrice(@RequestParam("page") int page , @RequestParam("companyId")int companyId , @RequestParam("minPrice") int minPrice , @RequestParam("maxPrice") int maxPrice) throws ServerException {
        List<CouponDto> coupons = couponsLogic.getAllCouponsOfCompanyByPriceRange(page , companyId , minPrice , maxPrice);
        return coupons;
    }

    @GetMapping("/ofCompanyByCategory")
    public List<CouponDto> getAllCouponsOfCompanyByCategory(@RequestParam("page") int page , @RequestParam("companyId")int companyId , @RequestParam("category")Category category) throws ServerException {
        List<CouponDto> coupons = couponsLogic.getAllCouponsOfCompanyByCategory(page , companyId ,category);
        return coupons;
    }

}
