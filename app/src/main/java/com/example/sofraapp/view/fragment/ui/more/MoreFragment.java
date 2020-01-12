package com.example.sofraapp.view.fragment.ui.more;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sofraapp.R;
import com.example.sofraapp.data.local.SharedPreference;
import com.example.sofraapp.helper.HelperMethod;
import com.example.sofraapp.view.activity.HomeActivity;
import com.example.sofraapp.view.fragment.BaseFragment;
import com.example.sofraapp.view.fragment.ui.more.more_client.MoreClientFragment;
import com.example.sofraapp.view.fragment.ui.more.more_restaurant.MoreRestaurantFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.example.sofraapp.data.local.SharedPreference.CLIENT;
import static com.example.sofraapp.data.local.SharedPreference.RESTAURANT;
import static com.example.sofraapp.data.local.SharedPreference.USER_TYPE;


public class MoreFragment extends BaseFragment {
    View view;
    Unbinder unbinder;

    public MoreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_more, container, false);
        HomeActivity homeActivity = (HomeActivity) getActivity();
        homeActivity.setFram(View.GONE);
        unbinder = ButterKnife.bind(this, view);

        if (SharedPreference.loadStringData(getActivity(), USER_TYPE).equals(CLIENT)) {
            MoreClientFragment moreClientFragment = new MoreClientFragment();
            HelperMethod.ReplaceFragment(getActivity().getSupportFragmentManager(),
                    moreClientFragment, R.id.fram
                    , null, null);

        } else if (SharedPreference.loadStringData(getActivity(), USER_TYPE).equals(RESTAURANT)) {
            MoreRestaurantFragment moreRestaurantFragment = new MoreRestaurantFragment();
            HelperMethod.ReplaceFragment(getActivity().getSupportFragmentManager(),
                    moreRestaurantFragment, R.id.fram,
                    null, null);
        }
        return view;
    }

    @Override
    public void onBack() {
        super.onBack();
    }
}
