package com.example.sofraapp.view.fragment.ui.home.home_fragment.homeClient;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofraapp.R;
import com.example.sofraapp.adapter.ShoppingClientAdapter;
import com.example.sofraapp.database.model.Order;
import com.example.sofraapp.database.model.OrderDataBase;
import com.example.sofraapp.helper.HelperMethod;
import com.example.sofraapp.view.activity.HomeActivity;
import com.example.sofraapp.view.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class ShoppingClientFragment extends BaseFragment {
    View view;
    Unbinder unbinder;

    BaseFragment baseFragment;
    @BindView(R.id.shopping_recyclerView)
    RecyclerView shoppingRecyclerView;
    @BindView(R.id.shopping_tv_total)
    TextView shoppingTvTotal;
    @BindView(R.id.shopping_tv_total_price)
    TextView shoppingTvTotalPrice;
    @BindView(R.id.shopping_btn_confirm)
    Button shoppingBtnConfirm;
    @BindView(R.id.shopping_btn_add)
    Button shoppingBtnAdd;
    List<Order> orders = new ArrayList<>();
    ShoppingClientAdapter shoppingClientAdapter;
    public LinearLayoutManager linearLayoutManager;
    int item = 0;
    int notes;
    int number = 0;
    double count = 0.0;

    public ShoppingClientFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.shopping_client, container, false);
        unbinder = ButterKnife.bind(this, view);
        HomeActivity homeActivity = (HomeActivity) getActivity();
        homeActivity.setFram(View.VISIBLE);
        initRecyclerView();
        setUpActivity();
        return view;
    }

    private void initRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        shoppingRecyclerView.setLayoutManager(linearLayoutManager);
        shoppingClientAdapter = new ShoppingClientAdapter(getActivity(), getActivity(), orders);
        shoppingRecyclerView.setAdapter(shoppingClientAdapter);
        orders.addAll(OrderDataBase.getInstance(getActivity()).orderDAOs().getOrderList());
        shoppingClientAdapter.notifyDataSetChanged();


        for (int i = 0; i < orders.size(); i++) {
            count = count + Double.parseDouble(orders.get(i).getPrice());
        }
        shoppingTvTotalPrice.setText(String.valueOf(count));


        for (int i = 0; i < orders.size(); i++) {
            item = orders.get(i).getId();
        }

        for (int i = 0; i < orders.size(); i++) {
            notes = orders.get(i).getId();
        }


        for (int i = 0; i < orders.size(); i++) {
            number = Integer.parseInt(orders.get(i).getNumber());
        }

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onBack() {

        super.onBack();
    }

    @OnClick({R.id.shopping_btn_confirm, R.id.shopping_btn_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.shopping_btn_confirm:
                OrderDetailClientFragment orderDetailClientFragment = new OrderDetailClientFragment();
                HelperMethod.ReplaceFragment(getActivity().getSupportFragmentManager(), orderDetailClientFragment, R.id.fram,
                        null, null);
                break;
            case R.id.shopping_btn_add:
                ListOfFoodClientFragment listOfFoodClientFragment = new ListOfFoodClientFragment();
                HelperMethod.ReplaceFragment(getActivity().getSupportFragmentManager(), listOfFoodClientFragment, R.id.fram,
                        null, null);
                break;
        }
    }


}
