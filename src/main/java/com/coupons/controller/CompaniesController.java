package com.coupons.controller;

import com.coupons.dto.CompanyDto;
import com.coupons.entities.Company;
import com.coupons.exceptions.ServerException;
import com.coupons.logic.CompaniesLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompaniesController {

    private CompaniesLogic companiesLogic;

    @Autowired
    public CompaniesController(CompaniesLogic companiesLogic){
        this.companiesLogic = companiesLogic;
    }

    @PostMapping
    public void createCompany(@RequestBody Company company) throws ServerException {
        companiesLogic.addCompany(company);
    }

    @PutMapping
    public void updateCompany(@RequestBody Company company) throws ServerException {
        companiesLogic.updateCompany(company);
    }

    @DeleteMapping("{companyId}")
    public void deleteCompany(@PathVariable("companyId") int companyId) throws ServerException {
        companiesLogic.removeCompany(companyId);
    }

    @GetMapping("{companyId}")
    public CompanyDto getCompany(@PathVariable("companyId") int companyId) throws ServerException {
        CompanyDto company = companiesLogic.getCompany(companyId);
        return company;
    }

    @GetMapping
    public List<CompanyDto> getAllCompanies(@RequestParam("page") int page) throws ServerException {
        List<CompanyDto> companies = companiesLogic.getAllCompanies(page);
        return companies;
    }
}
