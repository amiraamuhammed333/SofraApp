package com.example.sofraapp.view.fragment.ui.more.more_client;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sofraapp.R;
import com.example.sofraapp.data.api.APIManger;
import com.example.sofraapp.data.model.offer_client.list_order_client.OfferClientData;
import com.example.sofraapp.data.model.offer_client.offer_client_detail.OfferDetailClientResponse;
import com.example.sofraapp.view.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OfferDetailClientFragment extends BaseFragment {
    View view;
    Unbinder unbinder;
    BaseFragment baseFragment;
    public OfferClientData offer;
    @BindView(R.id.detail_offer_client_tv_name)
    TextView detailOfferClientTvName;
    @BindView(R.id.detail_offer_client_tv_desc)
    TextView detailOfferClientTvDesc;
    @BindView(R.id.detail_offer_client_tv_start_at)
    TextView detailOfferClientTvStartAt;
    @BindView(R.id.detail_offer_client_tv_ending_at)
    TextView detailOfferClientTvEndingAt;
    @BindView(R.id.detail_offer_client_tv_buy)
    Button detailOfferClientTvBuy;

    public OfferDetailClientFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setUpActivity();
        view = inflater.inflate(R.layout.detail_offer_client_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        getOfferDetail();
        return view;
    }

    @OnClick(R.id.detail_offer_client_tv_buy)
    public void onClick() {
        getOfferDetail();
    }

    private void getOfferDetail() {
         detailOfferClientTvName.setText(offer.getName());
         detailOfferClientTvDesc.setText(offer.getDescription());
         detailOfferClientTvStartAt.setText(offer.getStartingAt());
         detailOfferClientTvEndingAt.setText(offer.getEndingAt());
        APIManger.getApis().getDetail(21).enqueue(new Callback<OfferDetailClientResponse>() {
            @Override
            public void onResponse(Call<OfferDetailClientResponse> call, Response<OfferDetailClientResponse> response) {

            }

            @Override
            public void onFailure(Call<OfferDetailClientResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBack() {
        super.onBack();
    }
}
