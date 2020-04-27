package com.example.bankinglearning.views.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bankinglearning.R;
import com.example.bankinglearning.models.Account.BaseAccount;
import com.example.bankinglearning.models.Account.CreditAccount;
import com.example.bankinglearning.models.Account.DebitAccount;
import com.example.bankinglearning.models.Account.TicketsAccount;
import com.example.bankinglearning.views.listeners.OnDataPass;

import java.util.ArrayList;
import java.util.List;

public class AddAccountFragment extends Fragment {
    private ListView listView;
    private Button submit;
    private EditText newAccountName;
    private int selectedPosition;
    private View previousSelectedItem;
    private OnDataPass dataPasser;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_addaccount, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        newAccountName = view.findViewById(R.id.newAccountNameEditText);
        listView = view.findViewById(R.id.ListView);
        submit = view.findViewById(R.id.submit);

    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final List<String> accountTypesList = new ArrayList<>();
        accountTypesList.add("Credit Account");
        accountTypesList.add("Debit account");
        accountTypesList.add("Ticket account");
        final ArrayAdapter<String> accountTypeAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, accountTypesList);
        listView.setAdapter(accountTypeAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (previousSelectedItem != null) {
                    previousSelectedItem.setBackgroundColor(Color.WHITE);
                }
                previousSelectedItem = view;
                String selectedItem = (String) parent.getItemAtPosition(position);
                selectedPosition = position;
                view.setBackgroundColor(Color.parseColor("#EFF0F1"));
                Toast toast = Toast.makeText(getContext(), selectedItem + " selected", Toast.LENGTH_LONG);
                toast.show();
            }


        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    BaseAccount newAccount;
                    newAccount = createNewAccount(selectedPosition, newAccountName.getText().toString());
                    Toast toast = Toast.makeText(getContext(), newAccountName.getText().toString() + " created", Toast.LENGTH_LONG);
                    toast.show();
                    dataPasser.onDataPass(newAccount);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }


    public BaseAccount createNewAccount(int position, String name) {
        switch (position) {
            case 0: {
                return new CreditAccount(name, 0.0);
            }

            case 1: {
                return new DebitAccount(name, 0.0);
            }

            case 2: {
                return new TicketsAccount(name, 0.0);
            }
        }

        return null;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataPasser = (OnDataPass) context;
    }


}
