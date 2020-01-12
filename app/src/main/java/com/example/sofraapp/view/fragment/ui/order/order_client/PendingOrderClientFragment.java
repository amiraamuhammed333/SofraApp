package com.example.sofraapp.view.fragment.ui.order.order_client;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofraapp.R;
import com.example.sofraapp.adapter.OrderClientAdapter;
import com.example.sofraapp.data.api.APIManger;
import com.example.sofraapp.data.model.client.login_client.ClientData;
import com.example.sofraapp.data.model.order_client.order_client.OrderClientData;
import com.example.sofraapp.data.model.order_client.order_client.OrderClientResponse;
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

import static com.example.sofraapp.data.local.SharedPreference.loadUserDataClient;


public class PendingOrderClientFragment extends BaseFragment {
    View view;
    Unbinder unbinder;
    LinearLayoutManager linearLayoutManager;
    OrderClientAdapter currentOrderClientAdapter;
    List<OrderClientData> currentOrderClientDataList = new ArrayList<>();
    @BindView(R.id.pending_order_client_recycleview)
    RecyclerView pendingOrderClientRecycleview;
    private int maxPage = 0;
    private OnEndless onEndless;
    private int previousPage = 1;
    ClientData clientData;
    private boolean filter = false;

    BaseFragment baseFragment;

    public PendingOrderClientFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pending_order_client, container, false);
        unbinder = ButterKnife.bind(this, view);
        setUpActivity();
        HomeActivity homeActivity = (HomeActivity) getActivity();
        homeActivity.setFram(View.VISIBLE);
        clientData = loadUserDataClient(getActivity());
        iniRecyclerView();
        return view;
    }

    private void iniRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        pendingOrderClientRecycleview.setLayoutManager(linearLayoutManager);
        onEndless = new OnEndless(linearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {
                if (maxPage != 0) {
                    getCurrentOrder(current_page);
                }
                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        previousPage = current_page;
                        if (filter) {
                        } else {
                            getCurrentOrder(current_page);
                        }
                    } else {
                        onEndless.current_page = previousPage;
                    }
                } else {
                    onEndless.current_page = previousPage;
                }
            }
        };

        pendingOrderClientRecycleview.addOnScrollListener(onEndless);
        currentOrderClientAdapter = new OrderClientAdapter(getActivity(), getActivity(), currentOrderClientDataList);
        pendingOrderClientRecycleview.setAdapter(currentOrderClientAdapter);
        getCurrentOrder(1);
    }

    public void getCurrentOrder(int page) {
        APIManger.getApis().getCurrentOrderClient("HRbqKFSaq5ZpsOKITYoztpFZNylmzL9elnlAThxZSZ52QWqVBIj8Rdq7RhoB", " pending").
                enqueue(new Callback<OrderClientResponse>() {
                    @Override
                    public void onResponse(Call<OrderClientResponse> call, Response<OrderClientResponse> response) {
                        if (response.body().getStatus() == 1) {
                            maxPage = response.body().getData().getLastPage();
                            currentOrderClientDataList.addAll(response.body().getData().getData());
                            currentOrderClientAdapter.notifyDataSetChanged();
                        }
                        currentOrderClientAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<OrderClientResponse> call, Throwable t) {

                    }
                });

    }


    @Override
    public void onBack() {
        super.onBack();
    }
}
