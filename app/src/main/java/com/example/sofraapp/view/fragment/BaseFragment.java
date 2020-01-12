package com.example.sofraapp.view.fragment;


import androidx.fragment.app.Fragment;

import com.example.sofraapp.view.activity.BaseActivity;
import com.example.sofraapp.view.activity.HomeActivity;

public class BaseFragment extends Fragment {

    public BaseActivity baseActivity;
    public HomeActivity homeActivity;


    public void setUpActivity() {

        baseActivity = (BaseActivity) getActivity ();
        baseActivity.baseFragment = this;


    }
    public void setUpHomeActivity() {

        homeActivity = (HomeActivity) getActivity ();

    }


    public void onBack() {
        baseActivity.superBackPressed ();
    }
}
