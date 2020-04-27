package com.example.bankinglearning.views.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.bankinglearning.ApplicationClass;
import com.example.bankinglearning.R;
import com.example.bankinglearning.models.Account.BaseAccount;
import com.example.bankinglearning.models.ClientAccount.ClientAccount;
import com.example.bankinglearning.viewModels.ClientAccountViewModel;
import com.example.bankinglearning.views.activities.SecondActivity;
import com.example.bankinglearning.views.adapters.AccountAdapter;
import com.example.bankinglearning.views.listeners.NextPageClickListener;

import java.io.Serializable;
import java.util.List;


public class ClientAccountFragment extends Fragment implements Serializable {
    private TextView clientNameTextView, clientBdateTextView, clientCNPTextView, accountBalanceTextView;
    public ClientAccountViewModel clientAccountViewModel;
    private AccountAdapter accountAdapter;
    private Spinner spinner;
    private Button addMoney, withdrawMoney;
    private NumberPicker operationsPicker;
    private NextPageClickListener nextPageClickListener;
    private ImageButton nextPageButton;
    private int LAUNCH_SECOND_ACTIVITY = 1;
    private BaseAccount recievedAccount;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_clientaccount, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        clientNameTextView = view.findViewById(R.id.clientNameTextView);
        clientBdateTextView = view.findViewById(R.id.clientBDateTextView);
        clientCNPTextView = view.findViewById(R.id.clientCNPTextView);
        spinner = view.findViewById(R.id.accountChooserSpinner);
        accountBalanceTextView = view.findViewById(R.id.accountBalanceTextView);
        addMoney = view.findViewById(R.id.addButton);
        withdrawMoney = view.findViewById(R.id.withdrawButton);
        operationsPicker = view.findViewById(R.id.operationsPicker);
        operationsPicker.setMinValue(1);
        operationsPicker.setMaxValue(10000);
        nextPageButton = view.findViewById(R.id.nextPageButton);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        clientAccountViewModel = new ViewModelProvider(this).get(ClientAccountViewModel.class);
        accountAdapter = new AccountAdapter(clientAccountViewModel.getClientAccountAttributes().getValue());
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ClientAccount selectedAccount = (ClientAccount) parent.getItemAtPosition(position);
                accountBalanceTextView.setText(ApplicationClass.getInstance().getString(R.string.current_account_balance, selectedAccount.getAccount().getBalance().toString()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                addMoney.setEnabled(false);
                withdrawMoney.setEnabled(false);

            }
        });
        spinner.setAdapter(accountAdapter);
        clientAccountViewModel.getClientAccountAttributes().observe(getViewLifecycleOwner(), new Observer<List<ClientAccount>>() {
            @Override
            public void onChanged(List<ClientAccount> clientAccounts) {
                if (clientAccounts != null) {
                    clientNameTextView.setText("Holder Name: " + clientAccounts.get(0).getClient().getName());
                    clientBdateTextView.setText("Holder Birth date: " + clientAccounts.get(0).getClient().getBirthDate().toString());
                    clientCNPTextView.setText("Personal Identity Number: " + clientAccounts.get(0).getClient().getCnp());
                    accountBalanceTextView.setText(ApplicationClass.getInstance().getString(R.string.current_account_balance, clientAccounts.get(spinner.getSelectedItemPosition()).getAccount().getBalance().toString()));
                }
            }
        });
        addMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double amount = Double.valueOf(operationsPicker.getValue());
                try {
                    addMoney.setEnabled(true);
                    clientAccountViewModel.addMoney(clientAccountViewModel.getClientAccountAttributes().getValue().get(spinner.getSelectedItemPosition()), amount);
                } catch (Exception e) {
                    Toast toast = Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        withdrawMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double amount = Double.valueOf(operationsPicker.getValue());
                try {
                    withdrawMoney.setEnabled(true);
                    clientAccountViewModel.withdrawMoney(clientAccountViewModel.getClientAccountAttributes().getValue().get(spinner.getSelectedItemPosition()), amount);
                } catch (Exception e) {
                    Toast toast = Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        nextPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), SecondActivity.class);
                startActivityForResult(i, LAUNCH_SECOND_ACTIVITY);
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LAUNCH_SECOND_ACTIVITY) {
            if (resultCode == Activity.RESULT_OK) {
                recievedAccount = (BaseAccount) data.getSerializableExtra(SecondActivity.EXTRA_OBJECT);
                List<ClientAccount> clientAccounts = clientAccountViewModel.clientAccountLiveData.getValue();
                ClientAccount newClientAccount = new ClientAccount(clientAccounts.get(0).getClient(), recievedAccount);
                clientAccounts.add(newClientAccount);
                clientAccountViewModel.clientAccountLiveData.setValue(clientAccounts);


            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast toast = Toast.makeText(getContext(), "Nu merge", Toast.LENGTH_LONG);
                toast.show();
            }
        }

    }
}








