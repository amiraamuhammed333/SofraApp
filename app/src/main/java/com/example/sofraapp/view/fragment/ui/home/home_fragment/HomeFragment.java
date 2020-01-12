package com.example.sofraapp.view.fragment.ui.home.home_fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofraapp.R;
import com.example.sofraapp.adapter.CustomSpinnerAdapter;
import com.example.sofraapp.adapter.RestaurantClientAdapter;
import com.example.sofraapp.data.api.APIManger;
import com.example.sofraapp.data.local.SharedPreference;
import com.example.sofraapp.data.model.restaurant.RestaurantDataItem;
import com.example.sofraapp.data.model.restaurant.RestaurantDataResponse;
import com.example.sofraapp.helper.HelperMethod;
import com.example.sofraapp.helper.OnEndless;
import com.example.sofraapp.view.activity.HomeActivity;
import com.example.sofraapp.view.fragment.BaseFragment;
import com.example.sofraapp.view.fragment.ui.home.home_fragment.homeClient.RestaurantFoodsClientFragment;
import com.example.sofraapp.view.fragment.ui.home.home_fragment.homeRresturant.FoodsRestaurantFragment;
import com.example.sofraapp.view.fragment.ui.home.home_fragment.homeRresturant.HomeRestaurantFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;
import static com.example.sofraapp.data.local.SharedPreference.CLIENT;
import static com.example.sofraapp.data.local.SharedPreference.RESTAURANT;
import static com.example.sofraapp.data.local.SharedPreference.USER_TYPE;
import static com.example.sofraapp.helper.GeneralRequests.getSpinnerData;

public class HomeFragment extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.home_fragment_spinner_city)
    Spinner homeFragmentSpinnerCity;
    @BindView(R.id.home_fragment_iv_spinner)
    ImageView homeFragmentIvSpinner;
    @BindView(R.id.home_fragment_constrain)
    ConstraintLayout homeFragmentConstrain;
    @BindView(R.id.home_fragment_et_favourite_restaurant)
    EditText homeFragmentEtFavouriteRestaurant;
    @BindView(R.id.home_fragment_recycle)
    RecyclerView homeFragmentRecycle;
    private LinearLayoutManager linearLayoutManager;
    RestaurantClientAdapter restaurantAdapter;
    List<RestaurantDataItem> restaurantDataItemsList = new ArrayList<>();
    CustomSpinnerAdapter citiesAdapter;
    CustomSpinnerAdapter regionsAdapter;
    RestaurantFoodsClientFragment restaurantFoodsFragment;
    private Integer maxPage = 0;
    private OnEndless onEndless;
    private int previousPage = 1;
    private boolean filter = false;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        HomeActivity homeActivity = (HomeActivity) getActivity();
        homeActivity.setFram(View.GONE);
        setUpActivity();
        setUpHomeActivity();
        unbinder = ButterKnife.bind(this, view);
        homeActivity.setHomeActivityTitle("");
        if (SharedPreference.loadStringData(getActivity(), USER_TYPE).equals(CLIENT)) {
            citiesAdapter = new CustomSpinnerAdapter(getContext());
            regionsAdapter = new CustomSpinnerAdapter(getContext());
            getSpinnerData(APIManger.getApis().getCities(), homeFragmentSpinnerCity
                    , citiesAdapter, getString(R.string.city), new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
            initRecyclerView();
        } else if (SharedPreference.loadStringData(getActivity(), USER_TYPE).equals(RESTAURANT)) {
            HomeRestaurantFragment homeRestaurantFragment = new HomeRestaurantFragment();
            FoodsRestaurantFragment foodsRestaurantFragment = new FoodsRestaurantFragment();
            HelperMethod.ReplaceFragment(getActivity().getSupportFragmentManager(),
                    homeRestaurantFragment, R.id.fram,
                    null, null);
        }
        return view;
    }

    private void initRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        homeFragmentRecycle.setLayoutManager(linearLayoutManager);
        onEndless = new OnEndless(linearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {
                if (maxPage != 0) {
                    if (current_page <= maxPage) {

                        
                        if (maxPage != 0 && current_page != 1) {
                            previousPage = current_page;
                            if (filter) {
                                getRestaurantFilterO(current_page);
                            } else {
                                getListOfRestauramt(current_page);
                            }
                        } else {
                            onEndless.current_page = previousPage;
                        }
                    } else {
                        onEndless.current_page = previousPage;
                    }
                }
            }
        };
        homeFragmentRecycle.addOnScrollListener(onEndless);
        restaurantAdapter = new RestaurantClientAdapter(getActivity(), restaurantDataItemsList);
        homeFragmentRecycle.setAdapter(restaurantAdapter);
        getListOfRestauramt(1);
    }

    public void getListOfRestauramt(int page) {

        APIManger.getApis().getRestaurant(page).enqueue(new Callback<RestaurantDataResponse>() {
            @Override
            public void onResponse(Call<RestaurantDataResponse> call, Response<RestaurantDataResponse> response) {

                if (response.body().getStatus() == 1) {

                    if (page == 1) {
                        onEndless.previousTotal = 0;
                        onEndless.current_page = 1;
                        onEndless.privious_page = 1;
                        restaurantDataItemsList = new ArrayList<>();
                        restaurantAdapter = new RestaurantClientAdapter(getActivity(), restaurantDataItemsList);
                        homeFragmentRecycle.setAdapter(restaurantAdapter);
                    }

                    maxPage = response.body().getData().getLastPage();
                    restaurantDataItemsList.addAll(response.body().getData().getData());
                    restaurantAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<RestaurantDataResponse> call, Throwable t) {


            }
        });

    }

    private void getRestaurantFilterO(int page) {
        filter = true;
        String key = homeFragmentEtFavouriteRestaurant.getText().toString();

        APIManger.getApis().getFilterRestaurant(key, citiesAdapter.selectedId, page)
                .enqueue(new Callback<RestaurantDataResponse>() {
                    @Override
                    public void onResponse(Call<RestaurantDataResponse> call, Response<RestaurantDataResponse> response) {
                        try {
                            if (response.body().getStatus() == 1) {

                                if (page == 1) {
                                    onEndless.previousTotal = 0;
                                    onEndless.current_page = 1;
                                    onEndless.privious_page = 1;
                                    restaurantDataItemsList = new ArrayList<>();
                                    restaurantAdapter = new RestaurantClientAdapter(getActivity(), restaurantDataItemsList);
                                    homeFragmentRecycle.setAdapter(restaurantAdapter);
                                }

                                maxPage = response.body().getData().getLastPage();
                                restaurantDataItemsList.addAll(response.body().getData().getData());
                                restaurantAdapter.notifyDataSetChanged();
                            }
                        } catch (Exception e) {
                        }
                    }

                    @Override
                    public void onFailure(Call<RestaurantDataResponse> call, Throwable t) {
                        try {
                            Log.d(TAG, "onFailure: ");
                        } catch (Exception e) {
                        }
                    }
                });
    }


    @OnClick(R.id.home_fragment_et_favourite_restaurant)
    public void onClick() {
        getRestaurantFilterO(1);
    }
}