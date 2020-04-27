package com.example.bankinglearning.models.Account;

import java.io.Serializable;

public abstract class BaseAccount implements Serializable {
    private String accountNo;
    private Double balance;


    public BaseAccount(String accountNo, Double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }


    public String getAccountNo() {
        return accountNo;
    }


    public Double getBalance() {
        return balance;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }


    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
