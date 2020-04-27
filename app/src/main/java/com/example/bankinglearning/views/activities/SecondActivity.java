package com.example.bankinglearning.views.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.bankinglearning.R;
import com.example.bankinglearning.models.Account.BaseAccount;
import com.example.bankinglearning.views.fragments.AddAccountFragment;
import com.example.bankinglearning.views.listeners.OnDataPass;

public class SecondActivity extends BaseActivity implements OnDataPass {
    public BaseAccount account;
    public static final String EXTRA_OBJECT = "newAccountID";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        final AddAccountFragment addAccountFragment = new AddAccountFragment();
        addFragment(R.id.container2, addAccountFragment, "addAccountFragment");

    }

    @Override
    public void onDataPass(BaseAccount account) {
        this.account = account;
        openFirstActivity();
    }

    public void openFirstActivity() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(EXTRA_OBJECT, account);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
