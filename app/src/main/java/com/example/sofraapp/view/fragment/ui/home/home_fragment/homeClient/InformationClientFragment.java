package com.example.sofraapp.view.fragment.ui.home.home_fragment.homeClient;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sofraapp.R;
import com.example.sofraapp.adapter.CustomSpinnerAdapter;
import com.example.sofraapp.data.api.APIManger;
import com.example.sofraapp.data.model.restaurant.RestaurantDataItem;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.login_restaurant.ResataurantData;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.login_restaurant.Restaurant;
import com.example.sofraapp.view.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.example.sofraapp.helper.GeneralRequests.getSpinnerData;


public class InformationClientFragment extends BaseFragment {
    public RestaurantDataItem restaurant;
    View view;
    @BindView(R.id.information_tv_state)
    TextView informationTvState;
    @BindView(R.id.information_tv_minCharge)
    TextView informationTvMinCharge;
    @BindView(R.id.information_tv_delivery_cost)
    TextView informationTvDeliveryCost;
    Unbinder unbinder;
    CustomSpinnerAdapter citiesAdapter;
    CustomSpinnerAdapter regionsAdapter;
    ResataurantData resataurantData;
    @BindView(R.id.information_tv_city)
    TextView informationTvCity;
    @BindView(R.id.information_tv_region)
    TextView informationTvRegion;


    public InformationClientFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.information_client_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        informationTvState.setText("state"+"                         "+ RestaurantFoodsClientFragment.restaurant.getAvailability());
        informationTvMinCharge.setText("min charge"+"             "+RestaurantFoodsClientFragment.restaurant.getMinimumCharger());
        informationTvDeliveryCost.setText("delivery cost"+"           "+RestaurantFoodsClientFragment.restaurant.getDeliveryCost());
        setUpActivity();
        citiesAdapter = new CustomSpinnerAdapter(getActivity());
        regionsAdapter = new CustomSpinnerAdapter(getActivity());
        informationTvRegion.setText("city"+"                  "+RestaurantFoodsClientFragment.restaurant.getRegion().getName());
        informationTvCity.setText("region"+"                  "+RestaurantFoodsClientFragment.restaurant.getRegion().getCity().getName()
        );
//        getSpinnerData(APIManger.getApis().getCities(), informationTvCity
//                , citiesAdapter, getString(R.string.city), new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//                        if (i != 0) {
//                            getSpinnerData(APIManger.getApis().getRegions(citiesAdapter.selectedId), informationTvRegion
//                                    , regionsAdapter, getString(R.string.region), Integer.parseInt(RestaurantFoodsClientFragment.restaurant.getRegionId())); } }
//                    @Override
//                    public void onNothingSelected(AdapterView<?> adapterView) { }
//                }, Integer.parseInt(RestaurantFoodsClientFragment.restaurant.getRegionId()));
        return view;
    }

    @Override
    public void onBack() {
        super.onBack();
    }
}
