package com.example.sofraapp.view.fragment.ui.order;

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
import com.example.sofraapp.view.fragment.ui.order.order_client.OrderClientFragment;
import com.example.sofraapp.view.fragment.ui.order.order_restaurant.OrderRestautrantFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import static com.example.sofraapp.data.local.SharedPreference.CLIENT;
import static com.example.sofraapp.data.local.SharedPreference.RESTAURANT;
import static com.example.sofraapp.data.local.SharedPreference.USER_TYPE;

public class OrderFragment extends Fragment {
    View view;
    Unbinder unbinder;
    OrderClientFragment orderClientFragment;
    OrderRestautrantFragment orderRestautrantFragment;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

          view = inflater.inflate(R.layout.fragment_order, container, false);
        HomeActivity homeActivity = (HomeActivity) getActivity();
        homeActivity.setFram(View.GONE);
        unbinder = ButterKnife.bind(this, view);

        if (SharedPreference.loadStringData(getActivity(), USER_TYPE).equals(CLIENT)) {
           orderClientFragment = new OrderClientFragment();
            HelperMethod.ReplaceFragment(getActivity().getSupportFragmentManager(),
                    orderClientFragment, R.id.fram
                    , null, null);

        } else if (SharedPreference.loadStringData(getActivity(), USER_TYPE).equals(RESTAURANT)) {
            orderRestautrantFragment = new OrderRestautrantFragment();
            HelperMethod.ReplaceFragment(getActivity().getSupportFragmentManager(),
                   orderRestautrantFragment, R.id.fram,
                    null, null);
        }

        return view;
    }
}