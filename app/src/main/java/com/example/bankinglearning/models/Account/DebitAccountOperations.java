package com.example.bankinglearning.models.Account;

import com.example.bankinglearning.models.ClientAccount.ClientAccount;

public class DebitAccountOperations extends AccountOperations {


    @Override
    public void withdrawMoney(ClientAccount account, Double amount) throws Exception {
        if (account.getAccount().getBalance() < amount) {
            throw new Exception("You don't have enough money! The maximum amount you can withdraw is: " + account.getAccount().getBalance().intValue() + " RON");
        }
        account.getAccount().setBalance(account.getAccount().getBalance() - amount);
    }
}

