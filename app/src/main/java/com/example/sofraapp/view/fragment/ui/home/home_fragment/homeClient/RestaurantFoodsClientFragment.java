package com.example.sofraapp.view.fragment.ui.home.home_fragment.homeClient;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.ViewPager;

import com.example.sofraapp.R;
import com.example.sofraapp.adapter.TabAdapter;
import com.example.sofraapp.data.model.restaurant.RestaurantDataItem;
import com.example.sofraapp.view.activity.HomeActivity;
import com.example.sofraapp.view.fragment.BaseFragment;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RestaurantFoodsClientFragment extends BaseFragment {

    public int id;
    public static RestaurantDataItem  restaurant;
    @BindView(R.id.restaurant_foods_viewpager)
    ViewPager restaurantFoodsViewpager;
    @BindView(R.id.restaurant_foods_tablayout)
    TabLayout restaurantFoodsTablayout;

    View view;

    public RestaurantFoodsClientFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.restaurant_foods_client, container, false);
        ButterKnife.bind(this, view);
        HomeActivity homeActivity = (HomeActivity) getActivity();
        homeActivity.setFram(View.VISIBLE);
        homeActivity.setHomeActivityTitle("");

        setUpActivity();
        ListOfFoodClientFragment listOfFoodFragment = new ListOfFoodClientFragment();
        ReviewAndCommentClientFragment commentsAndEvaluationFragment = new ReviewAndCommentClientFragment();
        InformationClientFragment informationFragment = new InformationClientFragment();
        restaurantFoodsTablayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final TabAdapter adapter = new TabAdapter(getChildFragmentManager());
        adapter.addPage(listOfFoodFragment, getString(R.string.listoffood));
        adapter.addPage(commentsAndEvaluationFragment, getString(R.string.commentsandevaluation));
        adapter.addPage(informationFragment, getString(R.string.information));

        restaurantFoodsViewpager.setAdapter(adapter);
        restaurantFoodsTablayout.setupWithViewPager(restaurantFoodsViewpager);


        return view;
    }

    @Override
    public void onBack() {
        super.onBack();
    }
}
