package com.example.sofraapp.view.fragment.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.sofraapp.R;
import com.example.sofraapp.data.local.SharedPreference;
import com.example.sofraapp.helper.HelperMethod;
import com.example.sofraapp.view.activity.HomeActivity;
import com.example.sofraapp.view.fragment.ui.profile.client.EditClientFragment;
import com.example.sofraapp.view.fragment.ui.profile.restaurant.EditProfilrRestaurantFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.example.sofraapp.data.local.SharedPreference.CLIENT;
import static com.example.sofraapp.data.local.SharedPreference.RESTAURANT;
import static com.example.sofraapp.data.local.SharedPreference.USER_TYPE;

public class ProfileFragment extends Fragment {



    View view;
    Unbinder unbinder;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_profile, container, false);
        HomeActivity homeActivity = (HomeActivity) getActivity();
        homeActivity.setFram(View.GONE);
        unbinder= ButterKnife.bind(this,view);
        if (SharedPreference.loadStringData(getActivity(), USER_TYPE).equals(CLIENT)) {
            EditClientFragment editProfileClientFragment= new EditClientFragment();
            HelperMethod.ReplaceFragment(getActivity().getSupportFragmentManager(),
                   editProfileClientFragment, R.id.fram
                    , null, null);

        } else if (SharedPreference.loadStringData(getActivity(), USER_TYPE).equals(RESTAURANT)) {
           EditProfilrRestaurantFragment editProfileRestaurantFragment = new EditProfilrRestaurantFragment();
            HelperMethod.ReplaceFragment(getActivity().getSupportFragmentManager(),
                    editProfileRestaurantFragment, R.id.fram,
                    null, null);
        }



        return view;
    }
}