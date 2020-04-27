package com.example.bankinglearning.models.Account;

import com.example.bankinglearning.models.ClientAccount.ClientAccount;

public class AccountOperations implements AccountOperationsInterface {

    @Override
    public void addMoney(ClientAccount account, Double amount) throws Exception {
        account.getAccount().setBalance(account.getAccount().getBalance() + amount);
    }

    public void withdrawMoney(ClientAccount account, Double amount) throws Exception {
        account.getAccount().setBalance(account.getAccount().getBalance() - amount);

    }


}
