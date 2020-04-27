package com.example.bankinglearning.views.activities;

import android.os.Bundle;

import com.example.bankinglearning.R;
import com.example.bankinglearning.views.fragments.ClientAccountFragment;

import java.io.Serializable;

public class MainActivity extends BaseActivity implements Serializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ClientAccountFragment clientAccountFragment;
        clientAccountFragment = new ClientAccountFragment();
        addFragment(R.id.container, clientAccountFragment, "clientAccountFragment");

    }

}


