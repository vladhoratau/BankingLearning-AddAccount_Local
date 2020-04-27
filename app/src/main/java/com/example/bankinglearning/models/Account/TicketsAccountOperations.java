package com.example.bankinglearning.models.Account;

import com.example.bankinglearning.models.ClientAccount.ClientAccount;

public class TicketsAccountOperations extends AccountOperations implements AccountOperationsInterface {
    @Override
    public void addMoney(ClientAccount account, Double amount) throws Exception {
        throw new Exception("You cannot add money on the tickets account!");

    }

    @Override
    public void withdrawMoney(ClientAccount account, Double amount) throws Exception {
        throw new Exception("You cannot withdraw money from the tickets account!");

    }
}
