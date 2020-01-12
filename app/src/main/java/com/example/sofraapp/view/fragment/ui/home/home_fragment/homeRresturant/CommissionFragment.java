package com.example.sofraapp.view.fragment.ui.home.home_fragment.homeRresturant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sofraapp.R;
import com.example.sofraapp.data.api.APIManger;
import com.example.sofraapp.data.model.order_restaurant.order_restaurant.OrderRestaurantData;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.commission.CommissionData;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.commission.CommissionResponse;
import com.example.sofraapp.view.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CommissionFragment extends BaseFragment {
    View view;
    Unbinder unbinder;

    BaseFragment baseFragment;
    @BindView(R.id.commission_fragment_tv_count)
    TextView commissionFragmentTvCount;
    @BindView(R.id.commission_fragment_tv_commission)
    TextView commissionFragmentTvCommission;
    @BindView(R.id.commission_fragment_tv_total)
    TextView commissionFragmentTvTotal;
    @BindView(R.id.commission_fragment_tv_commissions)
    TextView commissionFragmentTvCommissions;
    @BindView(R.id.commission_fragment_tv_payment)
    TextView commissionFragmentTvPayment;
    @BindView(R.id.commission_fragment_tv_netCommissions)
    TextView commissionFragmentTvNetCommissions;
    CommissionData commissionData;
    OrderRestaurantData order;

    public CommissionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setUpActivity();
        view = inflater.inflate(R.layout.commission_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
//        commissionFragmentTvCount.setText(commissionData.getCount());
//        commissionFragmentTvTotal.setText(commissionData.getTotal());
        getCommission();
        return view;
    }

    private void getCommission() {
        APIManger.getApis().getCommission("Jptu3JVmDXGpJEaQO9ZrjRg5RuAVCo45OC2AcOKqbVZPmu0ZJPN3T1sm0cWx").enqueue(new Callback<CommissionResponse>() {
            @Override
            public void onResponse(Call<CommissionResponse> call, Response<CommissionResponse> response) {
                if (response.body().getStatus() == 1) {
                    commissionFragmentTvTotal.setText(response.body().getData().getTotal());
                }
            }

            @Override
            public void onFailure(Call<CommissionResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBack() {
        super.onBack();
    }
}

