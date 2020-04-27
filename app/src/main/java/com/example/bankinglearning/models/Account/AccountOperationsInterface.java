package com.example.bankinglearning.models.Account;

import com.example.bankinglearning.models.ClientAccount.ClientAccount;

public interface AccountOperationsInterface {

    void addMoney(ClientAccount account, Double amount) throws Exception;

    void withdrawMoney(ClientAccount account, Double amount) throws Exception;
}
