package com.example.sofraapp.view.fragment.ui.order.order_restaurant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofraapp.R;
import com.example.sofraapp.adapter.CurrentOrderRestaurantAdapter;
import com.example.sofraapp.adapter.PendingOrderRestaurantAdapter;
import com.example.sofraapp.data.api.APIManger;
import com.example.sofraapp.data.model.order_restaurant.order_restaurant.OrderRestaurantData;
import com.example.sofraapp.data.model.order_restaurant.order_restaurant.OrderRestaurantResponse;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.login_restaurant.ResataurantData;
import com.example.sofraapp.helper.OnEndless;
import com.example.sofraapp.view.activity.HomeActivity;
import com.example.sofraapp.view.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sofraapp.data.local.SharedPreference.loadUserDataRestaurant;


public class CurrentOrderRestaurantFragment extends BaseFragment {
    View view;
    Unbinder unbinder;
    LinearLayoutManager linearLayoutManager;
    CurrentOrderRestaurantAdapter currentOrderRestaurantAdapter;
    List<OrderRestaurantData> orderRestaurantDataList = new ArrayList<>();
    @BindView(R.id.current_order_restaurant_recycleview)
    RecyclerView currentOrderRestaurantRecycleview;

    private int maxPage = 0;
    private OnEndless onEndless;
    private int previousPage = 1;
    ResataurantData resataurantData;
    private boolean filter = false;


    BaseFragment baseFragment;

    public CurrentOrderRestaurantFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.current_order_restaurant, container, false);
        unbinder = ButterKnife.bind(this, view);
        setUpActivity();
        HomeActivity homeActivity = (HomeActivity) getActivity();
        homeActivity.setFram(View.VISIBLE);
        resataurantData = loadUserDataRestaurant(getActivity());
        iniRecyclerView();
        return view;
    }

    private void iniRecyclerView()
    {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        currentOrderRestaurantRecycleview.setLayoutManager(linearLayoutManager);
        onEndless = new OnEndless(linearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {
                if (maxPage != 0) {
                    getcuurentOrder(current_page);
                }
                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        previousPage = current_page;
                        if (filter) {
                        } else {
                            getcuurentOrder(current_page);
                        }
                    } else {
                        onEndless.current_page = previousPage;
                    }
                } else {
                    onEndless.current_page = previousPage;
                }
            }
        };

        currentOrderRestaurantRecycleview.addOnScrollListener(onEndless);
        currentOrderRestaurantAdapter = new CurrentOrderRestaurantAdapter(getActivity(), getActivity(), orderRestaurantDataList);
       currentOrderRestaurantRecycleview.setAdapter(currentOrderRestaurantAdapter);
        getcuurentOrder(1);
    }

    public void getcuurentOrder(int page)
    {
        APIManger.getApis().getOrderRestaurant("Jptu3JVmDXGpJEaQO9ZrjRg5RuAVCo45OC2AcOKqbVZPmu0ZJPN3T1sm0cWx" , " current").
                enqueue(new Callback<OrderRestaurantResponse>() {
                    @Override
                    public void onResponse(Call<OrderRestaurantResponse> call, Response<OrderRestaurantResponse> response) {
                        if (response.body().getStatus() == 1) {
                            maxPage = response.body().getData().getLastPage();
                            orderRestaurantDataList.addAll(response.body().getData().getData());
                            currentOrderRestaurantAdapter.notifyDataSetChanged();
                        }
                        currentOrderRestaurantAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<OrderRestaurantResponse> call, Throwable t) {

                    }
                });

    }

    @Override
    public void onBack() {
        super.onBack();
    }
}
