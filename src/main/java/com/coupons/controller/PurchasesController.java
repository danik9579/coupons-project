package com.coupons.controller;

import com.coupons.entities.Purchase;
import com.coupons.dto.PurchaseDto;
import com.coupons.enums.Category;
import com.coupons.exceptions.ServerException;
import com.coupons.logic.PurchasesLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchasesController {

    private PurchasesLogic purchasesLogic;

    @Autowired
    public PurchasesController(PurchasesLogic purchasesLogic){
        this.purchasesLogic = purchasesLogic;
    }

    @PostMapping
    public void createPurchase(@RequestBody Purchase purchase) throws ServerException {
        purchasesLogic.addPurchase(purchase);
    }

    @PutMapping
    public void updatePurchase(@RequestBody Purchase purchase) throws ServerException {
        purchasesLogic.updatePurchase(purchase);
    }

    @DeleteMapping("{purchaseId}")
    public void deletePurchase(@PathVariable("purchaseId") int purchaseId) throws ServerException {
        purchasesLogic.removePurchase(purchaseId);
    }

    @GetMapping("{purchaseId}")
    public PurchaseDto getPurchase(@PathVariable("purchaseId") int purchaseId) throws ServerException {
        PurchaseDto purchase = purchasesLogic.getPurchase(purchaseId);
        return purchase;
    }

    @GetMapping
    public List<PurchaseDto> getAllPurchases(@RequestParam("page") int page) throws ServerException {
        List<PurchaseDto> purchases = purchasesLogic.getAllPurchases(page);
        return purchases;
    }

    @GetMapping("/getByCustomerId")
    public List<PurchaseDto> getAllPurchasesByCustomerId(@RequestParam("page") int page , @RequestParam("customerId") int customerId) throws ServerException {
        List<PurchaseDto> purchases = purchasesLogic.getAllPurchasesByCustomerId(page , customerId);
        return purchases;
    }

    @GetMapping("/customerIdAndCategory")
    public List<PurchaseDto> getAllPurchasesByCustomerIdAndCategory(@RequestParam("page") int page , @RequestParam("customerId") int customerId, @RequestParam("category") Category category) throws ServerException {
        List<PurchaseDto> purchases = purchasesLogic.getAllPurchasesByCustomerIdAndCategory(page,customerId,category);
        return purchases;
    }
}
