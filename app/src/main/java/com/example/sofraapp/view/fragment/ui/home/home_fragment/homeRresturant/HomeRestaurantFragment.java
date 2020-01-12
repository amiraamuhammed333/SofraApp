package com.example.sofraapp.view.fragment.ui.home.home_fragment.homeRresturant;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofraapp.R;
import com.example.sofraapp.adapter.CategoryRestaurantAdapter;
import com.example.sofraapp.data.api.APIManger;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.categoryRestaurant.my_category.CategoryRestaurantDataItem;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.categoryRestaurant.my_category.CategotyResaurantResponse;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.login_restaurant.ResataurantData;
import com.example.sofraapp.helper.OnEndless;
import com.example.sofraapp.view.activity.CustomDialogAddcategory;
import com.example.sofraapp.view.activity.CustomUpdateDialog;
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

import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.example.sofraapp.data.local.SharedPreference.loadUserDataRestaurant;


public class HomeRestaurantFragment extends BaseFragment {

    @BindView(R.id.home_restauran_recyclerview)
    RecyclerView homeRestauranRecyclerview;
    @BindView(R.id.home_restaurant_ib_add)
    ImageButton homeRestaurantIbAdd;
    Unbinder unbinder;

    View view;
    LinearLayoutManager linearLayoutManager;
    public CategoryRestaurantAdapter categoryRestaurantAdapter;
    public List<CategoryRestaurantDataItem> categoryRestaurantDataItemList = new ArrayList<>();
    private int maxPage = 0;
    private OnEndless onEndless;
    private int previousPage = 1;
    private boolean filter = false;
    ResataurantData resataurantData;
    Dialog dialog;
    int category_id = -1;


    public HomeRestaurantFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.home_restaurant_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        setUpActivity();
        resataurantData = loadUserDataRestaurant(getActivity());
        HomeActivity homeActivity = (HomeActivity) getActivity();
        homeActivity.setFram(View.VISIBLE);

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
                    getCategory(current_page);
                }

                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        previousPage = current_page;
                        if (filter) {

                        } else {
                            getCategory(current_page);
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
        categoryRestaurantAdapter = new CategoryRestaurantAdapter(getActivity(), getActivity(), categoryRestaurantDataItemList);
        homeRestauranRecyclerview.setAdapter(categoryRestaurantAdapter);
        getCategory(1);

        categoryRestaurantAdapter.setUpdateClickListner(new CategoryRestaurantAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                CustomUpdateDialog customUpdateDialog = new CustomUpdateDialog(baseActivity);
                customUpdateDialog.id = categoryRestaurantDataItemList.get(position).getId();
                customUpdateDialog.show();

            }
        });
        categoryRestaurantAdapter.setOnItemClickListener(new CategoryRestaurantAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                FoodsRestaurantFragment foodsRestaurantFragment = new FoodsRestaurantFragment();
                foodsRestaurantFragment.id = categoryRestaurantDataItemList.get(position).getId();
            }
        });
    }

    public void getCategory(int page) {
        APIManger.getApis().getCategory(resataurantData.getApiToken(), page).enqueue(new Callback<CategotyResaurantResponse>() {
            @Override
            public void onResponse(Call<CategotyResaurantResponse> call, Response<CategotyResaurantResponse> response) {
                if (response.body().getStatus() == 1) {
                    maxPage = response.body().getData().getLastPage();
                    categoryRestaurantDataItemList.addAll(response.body().getData().getData());
                    categoryRestaurantAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<CategotyResaurantResponse> call, Throwable t) {
                try {
                    Log.d(TAG, "onFailure: ");
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

        CustomDialogAddcategory cdd = new CustomDialogAddcategory(getActivity());
        cdd.homeRestaurantFragment = this;
        cdd.resataurantData = resataurantData;
        cdd.show();

    }


}
