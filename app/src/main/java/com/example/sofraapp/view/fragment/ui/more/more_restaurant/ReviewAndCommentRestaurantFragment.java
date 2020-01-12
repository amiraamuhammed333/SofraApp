package com.example.sofraapp.view.fragment.ui.more.more_restaurant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofraapp.R;
import com.example.sofraapp.adapter.ReviewAdapter;
import com.example.sofraapp.data.api.APIManger;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.review.ReviewRestaurantDataItem;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.review.ReviewRestaurantResponse;
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


public class ReviewAndCommentRestaurantFragment extends BaseFragment {
    public static ReviewRestaurantDataItem review;
    View view;
    @BindView(R.id.review_restaurant_recyclerview)
    RecyclerView reviewRestaurantRecyclerview;

    private LinearLayoutManager linearLayoutManager;
    ReviewAdapter reviewClientAdapter;
    List<ReviewRestaurantDataItem> reviewRestaurantDataItems = new ArrayList<>();
    Unbinder unbinder;

    private int maxPage = 0;
    private OnEndless onEndless;
    private int previousPage = 1;
    private boolean filter = false;

    public ReviewAndCommentRestaurantFragment() {

        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.review_and_comments_restaurant_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        HomeActivity homeActivity = (HomeActivity) getActivity();
        homeActivity.setFram(View.VISIBLE);
        initRecycleview();
        setUpActivity();
        return view;
    }

    private void initRecycleview() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        reviewRestaurantRecyclerview.setLayoutManager(linearLayoutManager);

        onEndless = new OnEndless(linearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {

                if (maxPage != 0) {
                    getReview(current_page);
                }

                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        previousPage = current_page;
                        if (filter) {

                        } else {
                            getReview(current_page);
                        }
                    } else {
                        onEndless.current_page = previousPage;
                    }
                } else {
                    onEndless.current_page = previousPage;
                }
            }
        };
        reviewRestaurantRecyclerview.addOnScrollListener(onEndless);

        reviewClientAdapter = new ReviewAdapter(getActivity(), getActivity(), reviewRestaurantDataItems);
        reviewRestaurantRecyclerview.setAdapter(reviewClientAdapter);
        getReview(1);

    }


    public void getReview(int page) {
        APIManger.getApis().getReview("Jptu3JVmDXGpJEaQO9ZrjRg5RuAVCo45OC2AcOKqbVZPmu0ZJPN3T1sm0cWx",1
                ).enqueue(new Callback<ReviewRestaurantResponse>() {
            @Override
            public void onResponse(Call<ReviewRestaurantResponse> call, Response<ReviewRestaurantResponse> response) {
                if (response.body().getStatus() == 1) {
                    maxPage = response.body().getData().getLastPage();
                    reviewRestaurantDataItems.addAll(response.body().getData().getData());
                    reviewClientAdapter.notifyDataSetChanged();
                }
                reviewClientAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ReviewRestaurantResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBack() {
        super.onBack();
    }

    public ReviewAndCommentRestaurantFragment reviewAndCommentClientFragment;


}
