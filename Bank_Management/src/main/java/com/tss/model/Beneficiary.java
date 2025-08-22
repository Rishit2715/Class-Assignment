package com.tss.model;

public class Beneficiary {
    private int id;
    private String customerAccNo;
    private String beneficiaryAccNo;
    private String nickname;
    private String addedDate;

    // Constructors
    public Beneficiary() {}

    public Beneficiary(String customerAccNo, String beneficiaryAccNo, String nickname) {
        this.customerAccNo = customerAccNo;
        this.beneficiaryAccNo = beneficiaryAccNo;
        this.nickname = nickname;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCustomerAccNo() { return customerAccNo; }
    public void setCustomerAccNo(String customerAccNo) { this.customerAccNo = customerAccNo; }

    public String getBeneficiaryAccNo() { return beneficiaryAccNo; }
    public void setBeneficiaryAccNo(String beneficiaryAccNo) { this.beneficiaryAccNo = beneficiaryAccNo; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getAddedDate() { return addedDate; }
    public void setAddedDate(String addedDate) { this.addedDate = addedDate; }
}