package com.example.sofraapp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sofraapp.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public class EmptyFragment extends BaseFragment {
    View view;
    Unbinder unbinder;

    BaseFragment baseFragment;
    public EmptyFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setUpActivity();
        view= inflater.inflate ( R.layout.list_of_foods_client, container, false );
        unbinder= ButterKnife.bind(this,view);
        return  view;
    }

    @Override
    public void onBack() { super.onBack (); }
}
