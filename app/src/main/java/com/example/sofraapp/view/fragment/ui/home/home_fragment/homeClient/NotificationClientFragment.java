package com.example.sofraapp.view.fragment.ui.home.home_fragment.homeClient;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofraapp.R;
import com.example.sofraapp.adapter.NotificationClientAdapter;
import com.example.sofraapp.data.api.APIManger;
import com.example.sofraapp.data.model.notification_client.NotificationClientData;
import com.example.sofraapp.data.model.notification_client.NotificationClientResponse;
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


public class NotificationClientFragment extends BaseFragment {
    @BindView(R.id.notification_client_rv)
    RecyclerView notificationClientRv;
    View view;
    Unbinder unbinder;
    LinearLayoutManager linearLayoutManager;
    NotificationClientAdapter notificationClientAdapter;
    List<NotificationClientData> notificationClientDataList = new ArrayList<>();
    private Integer maxPage = 0;
    private OnEndless onEndless;
    private int previousPage = 1;
    private boolean filter = false;

    public NotificationClientFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.notification_client, container, false);
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
    notificationClientRv.setLayoutManager(linearLayoutManager);
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
    notificationClientRv.addOnScrollListener(onEndless);
    notificationClientAdapter = new NotificationClientAdapter(getActivity(),getActivity(), notificationClientDataList);
    notificationClientRv.setAdapter(notificationClientAdapter);
    getNotification(1);
    }

    private void getNotification(int page){
        APIManger.getApis().getNotification("K1X6AzRlJFeVbGnHwGYsdCu0ETP1BqYC7DpMTZ3zLvKgU5feHMvsEEnKTpzh").enqueue(new Callback<NotificationClientResponse>() {
            @Override
            public void onResponse(Call<NotificationClientResponse> call, Response<NotificationClientResponse> response) {
                if (response.body().getStatus()==1){
                    response.body().getData().getLastPageUrl();
                    notificationClientDataList.addAll(response.body().getData().getData());
                    notificationClientAdapter.notifyDataSetChanged(); }
                notificationClientAdapter.notifyDataSetChanged(); }
            @Override
            public void onFailure(Call<NotificationClientResponse> call, Throwable t) { }}); }

    @Override
    public void onBack() {
        super.onBack();
    }
}
