package com.example.sofraapp.view.fragment.ui.home.home_fragment.homeRresturant;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofraapp.R;
import com.example.sofraapp.adapter.FoodsRestaurantAdapter;
import com.example.sofraapp.data.api.APIManger;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.categoryRestaurant.my_category.CategoryRestaurantDataItem;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.login_restaurant.ResataurantData;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.restaurant_item.my_item.RestaurantDataMealsItem;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.restaurant_item.my_item.RestaurantMealsItemResponse;
import com.example.sofraapp.helper.HelperMethod;
import com.example.sofraapp.helper.OnEndless;
import com.example.sofraapp.view.activity.HomeActivity;
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

import static com.example.sofraapp.data.local.SharedPreference.NEW;
import static com.example.sofraapp.data.local.SharedPreference.SaveData;
import static com.example.sofraapp.data.local.SharedPreference.USER_TYPE;
import static com.example.sofraapp.data.local.SharedPreference.loadUserDataRestaurant;


public class FoodsRestaurantFragment extends BaseFragment {
    public  CategoryRestaurantDataItem restaurant;
    public int id;
    View view;
    Unbinder unbinder;
    LinearLayoutManager linearLayoutManager;
    FoodsRestaurantAdapter foodsRestaurantAdapter;

    List<RestaurantDataMealsItem> restaurantDataMealsItemList = new ArrayList<>();
    @BindView(R.id.home_restauran_recyclerview)
    RecyclerView homeRestauranRecyclerview;
    @BindView(R.id.home_restaurant_ib_add)
    ImageButton homeRestaurantIbAdd;
    private int maxPage = 0;
    private OnEndless onEndless;
    private int previousPage = 1;
    private boolean filter = false;
    ResataurantData resataurantData;
    Dialog dialog;


    public FoodsRestaurantFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.home_restaurant_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        setUpActivity();

        HomeActivity homeActivity = (HomeActivity) getActivity();
        homeActivity.setFram(View.VISIBLE);
        resataurantData = loadUserDataRestaurant(getActivity());

        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        homeRestauranRecyclerview.setLayoutManager(linearLayoutManager);

        onEndless = new OnEndless(linearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {

                if (maxPage != 0) {
                    getItemsFoods(current_page);
                }

                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        previousPage = current_page;
                        if (filter) {

                        } else {
                            getItemsFoods(current_page);
                        }
                    } else {
                        onEndless.current_page = previousPage;
                    }
                } else {
                    onEndless.current_page = previousPage;
                }
            }
        };
        homeRestauranRecyclerview.addOnScrollListener(onEndless);
        foodsRestaurantAdapter = new FoodsRestaurantAdapter(getActivity(), restaurantDataMealsItemList);
        homeRestauranRecyclerview.setAdapter(foodsRestaurantAdapter);
        getItemsFoods(1);

    }

    public void getItemsFoods(int page) {
        APIManger.getApis().getItem(resataurantData.getApiToken(), restaurant.getId()).enqueue(new Callback<RestaurantMealsItemResponse>() {
            @Override
            public void onResponse(Call<RestaurantMealsItemResponse> call, Response<RestaurantMealsItemResponse> response) {
                if (response.body().getStatus() == 1) {
                    maxPage = response.body().getData().getLastPage();
                    restaurantDataMealsItemList.addAll(response.body().getData().getData());
                    foodsRestaurantAdapter.notifyDataSetChanged();
                }
                foodsRestaurantAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<RestaurantMealsItemResponse> call, Throwable t) {
                try {

                } catch (Exception e) {

                }

            }
        });
    }


    @Override
    public void onBack() {
        super.onBack();

    }

    @OnClick(R.id.home_restaurant_ib_add)
    public void onClick() {
     //   SaveData(getActivity(), USER_TYPE, NEW);

        AddNewItemRestaurant addNewItemRestaurant= new AddNewItemRestaurant();
        addNewItemRestaurant.foodsRestaurantFragment = this;
        addNewItemRestaurant.resataurantData = resataurantData;


        HelperMethod.ReplaceFragment(getActivity().getSupportFragmentManager(),addNewItemRestaurant,
                R.id.fram,null,null);

    }


}
