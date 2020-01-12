package com.example.sofraapp.view.fragment.ui.home.home_fragment.homeRresturant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofraapp.R;
import com.example.sofraapp.adapter.NotificationRestAdapter;
import com.example.sofraapp.data.api.APIManger;
import com.example.sofraapp.data.model.notification_client.NotificationClientResponse;
import com.example.sofraapp.data.model.notificatrion.NotificationResponse;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.notification_restaurant.NotificationRestData;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.notification_restaurant.NotificationRestResponse;
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


public class NotificationRestaurantFragment extends BaseFragment {
    View view;
    Unbinder unbinder;

    BaseFragment baseFragment;
    @BindView(R.id.notification_rest_rv)
    RecyclerView notificationRestRv;
    LinearLayoutManager linearLayoutManager;
    NotificationRestAdapter notificationRestAdapter;
    List<NotificationRestData> notificationRestDataList = new ArrayList<>();
    private Integer maxPage = 0;
    private OnEndless onEndless;
    private int previousPage = 1;
    private boolean filter = false;

    public NotificationRestaurantFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setUpActivity();
        view = inflater.inflate(R.layout.notification_rest, container, false);
        unbinder = ButterKnife.bind(this, view);
        HomeActivity homeActivity = (HomeActivity) getActivity();
        homeActivity.setFram(View.VISIBLE);
        setUpActivity();
        setUpHomeActivity();
        initRecyclerView();
        return view;
    }

    private void initRecyclerView(){
        linearLayoutManager = new LinearLayoutManager(getActivity());
        notificationRestRv.setLayoutManager(linearLayoutManager);
        onEndless = new OnEndless(linearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {
                if (maxPage != 0) { getNotification(current_page); }
                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        previousPage = current_page;
                        if (filter) { }
                        else { getNotification(current_page); }
                    } else { onEndless.current_page = previousPage; }
                } else { onEndless.current_page = previousPage; } }};
        notificationRestRv.addOnScrollListener(onEndless);
        notificationRestAdapter = new NotificationRestAdapter(getActivity(),getActivity(), notificationRestDataList);
        notificationRestRv.setAdapter(notificationRestAdapter);
        getNotification(1);
    }

    private void getNotification(int page) {
        APIManger.getApis().getNotificationRest("Jptu3JVmDXGpJEaQO9ZrjRg5RuAVCo45OC2AcOKqbVZPmu0ZJPN3T1sm0cWx").enqueue(new Callback<NotificationRestResponse>() {
            @Override
            public void onResponse(Call<NotificationRestResponse> call, Response<NotificationRestResponse> response) {
                if (response.body().getStatus()==1){
                    response.body().getData().getLastPageUrl();
                    notificationRestDataList.addAll(response.body().getData().getData());
                    notificationRestAdapter.notifyDataSetChanged(); }
                notificationRestAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NotificationRestResponse> call, Throwable t) {

            }
        });
    }
    @Override
    public void onBack() {
        super.onBack();
    }
}
