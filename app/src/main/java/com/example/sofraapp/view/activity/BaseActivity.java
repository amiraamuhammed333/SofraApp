package com.example.sofraapp.view.activity;


import androidx.appcompat.app.AppCompatActivity;

import com.example.sofraapp.view.fragment.BaseFragment;

public class BaseActivity extends AppCompatActivity {

    public BaseFragment baseFragment;

    public void superBackPressed() {
        super.onBackPressed ();
    }

    @Override
    public void onBackPressed() {
        baseFragment.onBack();
    }
}
