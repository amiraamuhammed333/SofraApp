package com.example.sofraapp.view.fragment.ui.order.order_restaurant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofraapp.R;
import com.example.sofraapp.adapter.CompletedOrderRestaurantAdapter;
import com.example.sofraapp.adapter.OrderClientAdapter;
import com.example.sofraapp.data.api.APIManger;
import com.example.sofraapp.data.model.order_client.order_client.OrderClientData;
import com.example.sofraapp.data.model.order_client.order_client.OrderClientResponse;
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


public class CompletedOrderRestaurantFragment extends BaseFragment {
    View view;
    Unbinder unbinder;
    LinearLayoutManager linearLayoutManager;
    CompletedOrderRestaurantAdapter completedOrderRestaurantAdapter;
    List<OrderRestaurantData> orderRestaurantDataList = new ArrayList<>();
    @BindView(R.id.completed_order_restaurant_recycleview)
    RecyclerView completedOrderRestaurantRecycleview;

    private int maxPage = 0;
    private OnEndless onEndless;
    private int previousPage = 1;
    ResataurantData resataurantData;
    private boolean filter = false;

    BaseFragment baseFragment;

    public CompletedOrderRestaurantFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.completed_order_restaurant, container, false);
        unbinder = ButterKnife.bind(this, view);
        setUpActivity();
        HomeActivity homeActivity = (HomeActivity) getActivity();
        homeActivity.setFram(View.VISIBLE);
        resataurantData = loadUserDataRestaurant(getActivity());
        iniRecyclerView();
        return view;
    }

    private void iniRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        completedOrderRestaurantRecycleview.setLayoutManager(linearLayoutManager);
        onEndless = new OnEndless(linearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {
                if (maxPage != 0) {
                    getCompletedOrder(current_page);
                }
                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        previousPage = current_page;
                        if (filter) {
                        } else {
                            getCompletedOrder(current_page);
                        }
                    } else {
                        onEndless.current_page = previousPage;
                    }
                } else {
                    onEndless.current_page = previousPage;
                }
            }
        };

        completedOrderRestaurantRecycleview.addOnScrollListener(onEndless);
        completedOrderRestaurantAdapter = new CompletedOrderRestaurantAdapter(getActivity(), getActivity(), orderRestaurantDataList);
        completedOrderRestaurantRecycleview.setAdapter(completedOrderRestaurantAdapter);
        getCompletedOrder(1);
    }

    public void getCompletedOrder(int page) {
        APIManger.getApis().getOrderRestaurant("Jptu3JVmDXGpJEaQO9ZrjRg5RuAVCo45OC2AcOKqbVZPmu0ZJPN3T1sm0cWx", "completed").
                enqueue(new Callback<OrderRestaurantResponse>() {
                    @Override
                    public void onResponse(Call<OrderRestaurantResponse> call, Response<OrderRestaurantResponse> response) {
                        if (response.body().getStatus() == 1) {
                            maxPage = response.body().getData().getLastPage();
                            orderRestaurantDataList.addAll(response.body().getData().getData());
                            completedOrderRestaurantAdapter.notifyDataSetChanged();
                        }
                        completedOrderRestaurantAdapter.notifyDataSetChanged();
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
