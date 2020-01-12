package com.example.sofraapp.view.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import com.example.sofraapp.R;
import com.example.sofraapp.helper.HelperMethod;
import com.example.sofraapp.view.fragment.login_cycle.LogInFragment;

public class UserCycleActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_);

        HelperMethod.ReplaceFragment(this.getSupportFragmentManager(),
                new LogInFragment(), R.id.fram
                , null, null);

    }
}
