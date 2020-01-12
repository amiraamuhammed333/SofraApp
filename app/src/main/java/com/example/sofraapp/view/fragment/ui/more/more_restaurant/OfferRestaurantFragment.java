package com.example.sofraapp.view.fragment.ui.more.more_restaurant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofraapp.R;
import com.example.sofraapp.adapter.OfferRestaurantAdapter;
import com.example.sofraapp.data.api.APIManger;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.login_restaurant.ResataurantData;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.offer_restaurant.OfferRestaurantData;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.offer_restaurant.OfferRestaurantResponse;
import com.example.sofraapp.helper.HelperMethod;
import com.example.sofraapp.helper.OnEndless;
import com.example.sofraapp.view.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sofraapp.data.local.SharedPreference.loadUserDataRestaurant;


public class OfferRestaurantFragment extends BaseFragment {
    View view;
    Unbinder unbinder;
    LinearLayoutManager linearLayoutManager;
    OfferRestaurantAdapter offerRestaurantAdapter;


    BaseFragment baseFragment;
    @BindView(R.id.offer_restaurant_tv)
    TextView offerRestaurantTv;
    @BindView(R.id.offer_restaurant_recyclerView)
    RecyclerView offerRestaurantRecyclerView;
    List<OfferRestaurantData> offerRestaurantDataList = new ArrayList<>();
    @BindView(R.id.offer_restaurant_btn_add)
    Button offerRestaurantBtnAdd;
    private int maxPage = 0;
    private OnEndless onEndless;
    private int previousPage = 1;
    ResataurantData resataurantData;
    private boolean filter = false;


    public OfferRestaurantFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setUpActivity();
        view = inflater.inflate(R.layout.offer_restaurant_fregment, container, false);
        unbinder = ButterKnife.bind(this, view);
        resataurantData = loadUserDataRestaurant(getActivity());
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        offerRestaurantRecyclerView.setLayoutManager(linearLayoutManager);
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
        offerRestaurantRecyclerView.addOnScrollListener(onEndless);
        offerRestaurantAdapter = new OfferRestaurantAdapter(getActivity(), getActivity(), offerRestaurantDataList);
        offerRestaurantRecyclerView.setAdapter(offerRestaurantAdapter);
        getOfferList(1);
    }

    public void getOfferList(int page) {
        APIManger.getApis().getOfferRestaurant(resataurantData.getApiToken(), page).enqueue(new Callback<OfferRestaurantResponse>() {
            @Override
            public void onResponse(Call<OfferRestaurantResponse> call, Response<OfferRestaurantResponse> response) {
                if (response.body().getStatus() == 1) {
                    maxPage = response.body().getData().getLastPage();
                    offerRestaurantDataList.addAll(response.body().getData().getData());
                    offerRestaurantAdapter.notifyDataSetChanged();
                }
                offerRestaurantAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<OfferRestaurantResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBack() {
        super.onBack();
    }



    @OnClick(R.id.offer_restaurant_btn_add)
    public void onClick() {
        OfferAddRestaurantFragment offerAddRestaurantFragment = new OfferAddRestaurantFragment();
        HelperMethod.ReplaceFragment(getActivity().getSupportFragmentManager(),offerAddRestaurantFragment,
                R.id.fram,null,null);
    }
}
