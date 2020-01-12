package com.example.sofraapp.view.fragment.ui.more.more_client;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofraapp.R;
import com.example.sofraapp.adapter.OfferClientAdapter;
import com.example.sofraapp.data.api.APIManger;
import com.example.sofraapp.data.model.offer_client.list_order_client.OfferClientData;
import com.example.sofraapp.data.model.offer_client.list_order_client.OfferClientResponse;
import com.example.sofraapp.helper.OnEndless;
import com.example.sofraapp.view.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OfferClientFragent extends BaseFragment {
    View view;
    Unbinder unbinder;

    BaseFragment baseFragment;
    @BindView(R.id.offer_client_recyclerview)
    RecyclerView offerClientRecyclerview;
    LinearLayoutManager linearLayoutManager;
    OfferClientAdapter offerClientAdapter;
    List<OfferClientData>offerClientDataList=new ArrayList<>();
    private int maxPage = 0;
    private OnEndless onEndless;
    private int previousPage = 1;
    private boolean filter = false;

    public OfferClientFragent() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setUpActivity();
        view = inflater.inflate(R.layout.offer_client_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        linearLayoutManager= new LinearLayoutManager(getActivity());
        offerClientRecyclerview.setLayoutManager(linearLayoutManager);
        onEndless = new OnEndless(linearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {

                if (maxPage != 0) {
                    getOfferList(current_page);
                }

                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        previousPage = current_page;
                        if (filter) {

                        } else {
                            getOfferList(current_page);
                        }
                    } else {
                        onEndless.current_page = previousPage;
                    }
                } else {
                    onEndless.current_page = previousPage;
                }
            }
        };
        offerClientRecyclerview.addOnScrollListener(onEndless);
        offerClientAdapter=new OfferClientAdapter(getActivity(),getActivity(),offerClientDataList);
        offerClientRecyclerview.setAdapter(offerClientAdapter);
        getOfferList(1);

    }

    public void getOfferList(int page){
        APIManger.getApis().getOffers(page,1).enqueue(new Callback<OfferClientResponse>() {
            @Override
            public void onResponse(Call<OfferClientResponse> call, Response<OfferClientResponse> response) {
                if (response.body().getStatus()==1){
                    maxPage=response.body().getData().getLastPage();
                    offerClientDataList.addAll(response.body().getData().getData());
                    offerClientAdapter.notifyDataSetChanged();
                }
                offerClientAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<OfferClientResponse> call, Throwable t) {
                try {
            } catch (Exception e) { }
            }
        });

    }

    @Override
    public void onBack() {
        super.onBack();
    }
}
