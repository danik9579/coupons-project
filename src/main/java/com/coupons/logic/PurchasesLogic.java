package com.coupons.logic;

import com.coupons.dto.UserDto;
import com.coupons.entities.Purchase;
import com.coupons.constants.Constants;
import com.coupons.dal.IPurchasesDal;
import com.coupons.dto.PurchaseDto;
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
public class PurchasesLogic {
    private IPurchasesDal purchasesDal;

    @Autowired
    public PurchasesLogic(IPurchasesDal purchasesDal) {
        this.purchasesDal = purchasesDal;
    }

    public void addPurchase(Purchase purchase) throws ServerException {
        Date date = new Date();
        purchase.setTimeStamp(date);
        try {
            purchasesDal.save(purchase);
        }catch (Exception e){
            throw new ServerException("Error in creating Purchase " + purchase, ErrorType.GENERAL_ERROR);
        }
    }

    public void removePurchase(long purchaseId) throws ServerException {
        try {
            purchasesDal.deleteById(purchaseId);
        }catch (Exception e){
            throw new ServerException("Error in removing Purchase " + purchaseId, ErrorType.GENERAL_ERROR);
        }
    }


    public void updatePurchase(Purchase purchase) throws ServerException {
        try {
            purchasesDal.save(purchase);
        }catch (Exception e){
            throw new ServerException("Error in updating Purchase " + purchase.getId(), ErrorType.GENERAL_ERROR);
        }
    }

    public PurchaseDto getPurchase(long purchaseId) throws ServerException {
        try {
            PurchaseDto purchase = purchasesDal.findById(purchaseId);
            return purchase;
        }catch (Exception e){
            throw new ServerException("Error in getting Purchase " + purchaseId, ErrorType.GENERAL_ERROR);
        }
    }

    public List<PurchaseDto> getAllPurchases(int page) throws ServerException {
        Pageable paging = PageRequest.of(page - 1 , Constants.RECORDS_PER_PAGE);
        try {
            List<PurchaseDto> purchases = purchasesDal.findAll(paging);
            return purchases;
        }catch (Exception e){
            throw new ServerException("Error in getting all Purchases", ErrorType.GENERAL_ERROR);
        }
    }

    public List<PurchaseDto> getAllPurchasesByCustomerId(int page, int customerId) throws ServerException {
        Pageable paging = PageRequest.of(page - 1 , Constants.RECORDS_PER_PAGE);
        try {
            List<PurchaseDto> purchaseDto = purchasesDal.findAllByCustomerId(customerId , paging);
            return purchaseDto;
        }catch (Exception e){
            throw new ServerException("Error in getting Purchases by customerId: " + customerId, ErrorType.GENERAL_ERROR);
        }
    }

    public List<PurchaseDto> getAllPurchasesByCustomerIdAndCategory(int page, int customerId, Category category) throws ServerException {
        Pageable paging = PageRequest.of(page - 1 , Constants.RECORDS_PER_PAGE);
        try {
            List<PurchaseDto> purchaseDto = purchasesDal.findAllByCustomerIdAndCategory(customerId , category , paging);
            return purchaseDto;
        }catch (Exception e){
            throw new ServerException("Error in getting Purchases by customerId: " + customerId + " , and category: " + category, ErrorType.GENERAL_ERROR);
        }
    }
}
