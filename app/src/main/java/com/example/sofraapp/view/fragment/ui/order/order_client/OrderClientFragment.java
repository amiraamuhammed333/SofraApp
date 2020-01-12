package com.example.sofraapp.view.fragment.ui.order.order_client;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.ViewPager;

import com.example.sofraapp.R;
import com.example.sofraapp.adapter.TabAdapter;
import com.example.sofraapp.view.activity.HomeActivity;
import com.example.sofraapp.view.fragment.BaseFragment;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class OrderClientFragment extends BaseFragment {
    View view;
    Unbinder unbinder;
    BaseFragment baseFragment;
    @BindView(R.id.order_client_tablayout)
    TabLayout orderClientTablayout;
    @BindView(R.id.order_client_viewpager)
    ViewPager orderClientViewpager;

    public OrderClientFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.order_client_fragmment, container, false);
        unbinder = ButterKnife.bind(this, view);
        HomeActivity homeActivity = (HomeActivity) getActivity();
        homeActivity.setFram(View.VISIBLE);
        setUpActivity();
        PendingOrderClientFragment pendingOrderClientFragment = new PendingOrderClientFragment();
        CurrentOrderClientFragment currentOrderClientFragment = new CurrentOrderClientFragment();
        CompletedOrderClientFragment completedOrderClientFragment = new CompletedOrderClientFragment();
        orderClientTablayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final TabAdapter adapter = new TabAdapter(getChildFragmentManager());
        adapter.addPage(pendingOrderClientFragment, getString(R.string.pending_orders));
        adapter.addPage(currentOrderClientFragment, getString(R.string.current_order));
        adapter.addPage(completedOrderClientFragment, getString(R.string.completed_order));

        orderClientViewpager.setAdapter(adapter);
        orderClientTablayout.setupWithViewPager(orderClientViewpager);

        return view;
    }

    @Override
    public void onBack() {

        super.onBack();
    }
}
