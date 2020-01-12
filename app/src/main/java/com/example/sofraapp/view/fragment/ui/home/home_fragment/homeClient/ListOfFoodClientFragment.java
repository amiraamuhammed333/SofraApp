package com.example.sofraapp.view.fragment.ui.home.home_fragment.homeClient;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofraapp.R;
import com.example.sofraapp.adapter.CategoryClientAdapter;
import com.example.sofraapp.adapter.RestaurantClientAdapter;
import com.example.sofraapp.adapter.RestaurantFoodsClientAdapter;
import com.example.sofraapp.data.api.APIManger;
import com.example.sofraapp.data.model.client.category_foods.CategoryDataItem;
import com.example.sofraapp.data.model.client.category_foods.CategoryResponse;
import com.example.sofraapp.data.model.restaurant.RestaurantDataItem;
import com.example.sofraapp.data.model.restaurant_details.RestaurantDetailsDataItem;
import com.example.sofraapp.data.model.restaurant_details.RestaurantDetailsResponse;
import com.example.sofraapp.helper.HelperMethod;
import com.example.sofraapp.helper.OnEndless;
import com.example.sofraapp.view.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListOfFoodClientFragment extends BaseFragment {

    @BindView(R.id.restaurant_foods_recyclerview)
    RecyclerView restaurantFoodsRecyclerview;
    @BindView(R.id.restaurant_foods_recyclerview_category)
    RecyclerView restaurantFoodsRecyclerviewCategory;

    List<RestaurantDetailsDataItem> restaurantDetailsDataItems = new ArrayList<>();
    List<CategoryDataItem> categoryDataItems = new ArrayList<>();

    RestaurantFoodsClientAdapter restaurantFoodsAdapter;
    CategoryClientAdapter categoryAdapter;
    private LinearLayoutManager linearLayoutManager, linearLayoutManager2;
    public RestaurantDataItem restaurant;
    public int id;

    private Integer maxPage = 0;
    private OnEndless onEndless;
    private int previousPage = 1;
    private boolean filter = false;

    View view;
    public int category = -1;

    public ListOfFoodClientFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.list_of_foods_client, container, false);
        ButterKnife.bind(this, view);
        setUpActivity();
        initRecyclerView();

        return view;
    }


    private void initRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager2 = new LinearLayoutManager(getContext());
        linearLayoutManager2.setOrientation(RecyclerView.HORIZONTAL);
        restaurantFoodsRecyclerview.setLayoutManager(linearLayoutManager);
        restaurantFoodsRecyclerviewCategory.setLayoutManager(linearLayoutManager2);
        onEndless = new OnEndless(linearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {
                if (maxPage != 0) {
                }
                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        previousPage = current_page;
                        if (filter) {
                        } else {
                            getListOfRestauramtFoods(current_page);
                        }
                    } else {
                        onEndless.current_page = previousPage;
                    }
                } else {
                    onEndless.current_page = previousPage;
                }
            }
        };
        restaurantFoodsRecyclerview.addOnScrollListener(onEndless);

        restaurantFoodsAdapter = new RestaurantFoodsClientAdapter(getContext(), restaurantDetailsDataItems);
        restaurantFoodsRecyclerview.setAdapter(restaurantFoodsAdapter);

        categoryAdapter = new CategoryClientAdapter(getActivity(), categoryDataItems, this);
        restaurantFoodsRecyclerviewCategory.setAdapter(categoryAdapter);

        getListOfRestauramtFoods(1);
        getListOfCategories();
        restaurantFoodsAdapter.setOnItemClickListener(new RestaurantClientAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                RequestOrderClientFragment requestOrderClientFragment = new RequestOrderClientFragment();
                requestOrderClientFragment.id = restaurantDetailsDataItems.get(pos).getId();
                HelperMethod.ReplaceFragment(getActivity().getSupportFragmentManager(), requestOrderClientFragment
                        , R.id.fram
                        , null, null);
            }
        });
    }


    public void getListOfCategories() {
        APIManger.getApis().getCategory(RestaurantFoodsClientFragment.restaurant.getId(), -1).enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.body().getStatus() == 1) {
                    response.body().getData();
                    categoryDataItems.addAll(response.body().getData());
                    categoryAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
            }
        });
    }


    public void getListOfRestauramtFoods(int page) {
        APIManger.getApis().getRestaurantsFoods(RestaurantFoodsClientFragment.restaurant.getId(), category, page)
                .enqueue(new Callback<RestaurantDetailsResponse>() {
                    @Override
                    public void onResponse(Call<RestaurantDetailsResponse> call, Response<RestaurantDetailsResponse> response) {
                        if (response.body().getStatus() == 1) {

                            if (page == 1) {

                                onEndless.current_page = 1;
                                onEndless.firstVisibleItem= 0;
                                onEndless.previousTotal = 0;
                                onEndless.visibleItemCount = 0;
                                onEndless.privious_page = 1;

                                restaurantDetailsDataItems = new ArrayList<>();
                                restaurantFoodsAdapter = new RestaurantFoodsClientAdapter(getContext(), restaurantDetailsDataItems);
                                restaurantFoodsRecyclerview.setAdapter(restaurantFoodsAdapter);

                            }

                            maxPage = response.body().getData().getLastPage();
                            restaurantDetailsDataItems.addAll(response.body().getData().getData());
                            restaurantFoodsAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<RestaurantDetailsResponse> call, Throwable t) {
                    }
                });
    }


    @Override
    public void onBack() {
        super.onBack();
    }
}
