package com.example.bankinglearning.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bankinglearning.models.Account.AccountOperations;
import com.example.bankinglearning.models.Account.BaseAccount;
import com.example.bankinglearning.models.Account.CreditAccount;
import com.example.bankinglearning.models.Account.CreditAccountOperations;
import com.example.bankinglearning.models.Account.DebitAccount;
import com.example.bankinglearning.models.Account.DebitAccountOperations;
import com.example.bankinglearning.models.Account.TicketsAccount;
import com.example.bankinglearning.models.Account.TicketsAccountOperations;
import com.example.bankinglearning.models.Client.Client;
import com.example.bankinglearning.models.ClientAccount.ClientAccount;
import com.example.bankinglearning.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;


public class ClientAccountViewModel extends ViewModel {

    public MutableLiveData<List<ClientAccount>> clientAccountLiveData = new MutableLiveData<>();

    public ClientAccountViewModel() {
        loadClientAccountAttributes();
    }

    public MutableLiveData<List<ClientAccount>> getClientAccountAttributes() {
        return clientAccountLiveData;
    }

    public void loadClientAccountAttributes() {
        Client client1 = new Client("Vlad Horatau", "1960524330207", DateUtil.getDate("30-03-2001"));
        DebitAccount debit1 = new DebitAccount("ROBTRLRONCRTDEBXXXXXXXX", 1000.32);
        CreditAccount credit1 = new CreditAccount("ROBTRLRONCRTCREDXXXXXXXX", 5000.0);
        TicketsAccount ticket1 = new TicketsAccount("ROBTRLRONCRTTICKXXXXXXXX", 300.0);
        List<ClientAccount> clientAccounts = new ArrayList();
        ClientAccount creditAcc = new ClientAccount(client1, credit1);
        ClientAccount debitAcc = new ClientAccount(client1, debit1);
        ClientAccount ticketAcc = new ClientAccount(client1, ticket1);
        clientAccounts.add(creditAcc);
        clientAccounts.add(debitAcc);
        clientAccounts.add(ticketAcc);
        clientAccountLiveData.setValue(clientAccounts);
    }

   /* public void createNewAccount(int position, String name) throws Exception {
        BaseAccount newAccount = null;
        switch (position) {
            case 0: {
                newAccount = new CreditAccount(name, 0.0);
            }
            break;
            case 1: {
                newAccount = new DebitAccount(name, 0.0);
            }
            break;
            case 2: {
                newAccount = new TicketsAccount(name, 0.0);
            }
            break;
        }
        if (newAccount != null) {
            List<ClientAccount> clientAccounts = clientAccountLiveData.getValue();
            ClientAccount newClientAccount = new ClientAccount(clientAccounts.get(position).getClient(), newAccount);
            clientAccounts.add(newClientAccount);
            clientAccountLiveData.setValue(clientAccounts);
        } else throw new Exception("New account was not created!");
    }*/


    private AccountOperations getAccountOperations(BaseAccount account) {
        if (account instanceof DebitAccount)
            return new DebitAccountOperations();
        else if (account instanceof CreditAccount)
            return new CreditAccountOperations();
        else if (account instanceof TicketsAccount)
            return new TicketsAccountOperations();
        else
            return new AccountOperations();
    }

    public void addMoney(ClientAccount clientAccount, double amount) throws Exception {
        AccountOperations accountOperations = getAccountOperations(clientAccount.getAccount());
        accountOperations.addMoney(clientAccount, amount);
        clientAccountLiveData.setValue(clientAccountLiveData.getValue());
    }

    public void withdrawMoney(ClientAccount clientAccount, double amount) throws Exception {
        AccountOperations accountOperations = getAccountOperations(clientAccount.getAccount());
        accountOperations.withdrawMoney(clientAccount, amount);
        clientAccountLiveData.setValue(clientAccountLiveData.getValue());
    }

}