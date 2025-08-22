package com.tss.service;

import com.tss.dao.BeneficiaryDAO;

public class BeneficiaryService {
    private BeneficiaryDAO beneficiaryDAO = new BeneficiaryDAO();

   // Updated method
public String addBeneficiary(String customerAccNo, String beneficiaryAccNo, String nickname) {
    try {
        // All validation already done in servlet
        boolean success = beneficiaryDAO.addBeneficiary(customerAccNo, beneficiaryAccNo, nickname);
        if (success) {
            return "success:Beneficiary added successfully!";
        } else {
            return "Failed to add beneficiary. Please try again.";
        }
    } catch (Exception e) {
        e.printStackTrace();
        return "Database error. Please try later.";
    }
}
}