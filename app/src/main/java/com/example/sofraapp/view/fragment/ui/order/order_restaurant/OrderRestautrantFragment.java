package com.example.sofraapp.view.fragment.ui.order.order_restaurant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.ViewPager;

import com.example.sofraapp.R;
import com.example.sofraapp.adapter.CompletedOrderRestaurantAdapter;
import com.example.sofraapp.adapter.TabAdapter;
import com.example.sofraapp.view.activity.HomeActivity;
import com.example.sofraapp.view.fragment.BaseFragment;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class OrderRestautrantFragment extends BaseFragment {
    View view;
    Unbinder unbinder;
    BaseFragment baseFragment;
    @BindView(R.id.order_restaurant_tablayout)
    TabLayout orderRestaurantTablayout;
    @BindView(R.id.order_restaurant_viewpager)
    ViewPager orderRestaurantViewpager;

    public OrderRestautrantFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.order_restaurant_fragment, container, false);
        unbinder= ButterKnife.bind(this,view);
        HomeActivity homeActivity = (HomeActivity) getActivity();
        homeActivity.setFram(View.VISIBLE);
        setUpActivity();
        PendingOrdeRestaurantFragment pendingOrderRestaurantFragment = new PendingOrdeRestaurantFragment();
        CurrentOrderRestaurantFragment currentOrderRestaurantFragment = new CurrentOrderRestaurantFragment();
        CompletedOrderRestaurantFragment completedOrdeRestaurantFragment = new CompletedOrderRestaurantFragment();
        orderRestaurantTablayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final TabAdapter adapter = new TabAdapter(getChildFragmentManager());
        adapter.addPage(pendingOrderRestaurantFragment, getString(R.string.pending_orders));
        adapter.addPage(currentOrderRestaurantFragment, getString(R.string.current_order));
        adapter.addPage(completedOrdeRestaurantFragment, getString(R.string.completed_order));

        orderRestaurantViewpager.setAdapter(adapter);
        orderRestaurantTablayout.setupWithViewPager(orderRestaurantViewpager);
        return view;
    }

    @Override
    public void onBack() { super.onBack(); }
}
