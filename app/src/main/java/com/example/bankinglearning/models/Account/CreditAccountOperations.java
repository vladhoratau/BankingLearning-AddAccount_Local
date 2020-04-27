package com.example.bankinglearning.models.Account;

import com.example.bankinglearning.models.ClientAccount.ClientAccount;

public class CreditAccountOperations extends AccountOperations implements AccountOperationsInterface {

    private static final int MAX_WITHDRAW_IN_RON = 10000;

    @Override
    public void addMoney(ClientAccount account, Double amount) {
        account.getAccount().setBalance(account.getAccount().getBalance() + amount);
    }

    @Override
    public void withdrawMoney(ClientAccount account, Double amount) throws Exception {
        if (amount >= MAX_WITHDRAW_IN_RON) {
            {
                throw new Exception("The maximum value you can withdraw is:" + MAX_WITHDRAW_IN_RON);
            }

        } else
            account.getAccount().setBalance(account.getAccount().getBalance() - amount);
    }
}
