package com.example.sofraapp.view.fragment.login_cycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sofraapp.R;
import com.example.sofraapp.adapter.CustomSpinnerAdapter;
import com.example.sofraapp.data.api.APIManger;
import com.example.sofraapp.helper.HelperMethod;
import com.example.sofraapp.view.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.example.sofraapp.data.local.SharedPreference.CONFIRMATION_PASSWORD_RESTAURANT;
import static com.example.sofraapp.data.local.SharedPreference.DELIVERYCOST_RESTAURANT;
import static com.example.sofraapp.data.local.SharedPreference.DELIVERY_TIME_RESTAURANT;
import static com.example.sofraapp.data.local.SharedPreference.EMAIL_RESTAURANT;
import static com.example.sofraapp.data.local.SharedPreference.MINIMUMCHARGER_RESTAURANT;
import static com.example.sofraapp.data.local.SharedPreference.NAME_RESTAURANT;
import static com.example.sofraapp.data.local.SharedPreference.PASSWORD_RESTAURANT;
import static com.example.sofraapp.data.local.SharedPreference.REGION_RESTAURANT;
import static com.example.sofraapp.data.local.SharedPreference.SaveData;
import static com.example.sofraapp.helper.GeneralRequests.getSpinnerData;


public class SignUpStep1RestaurantFragment extends BaseFragment {
    View view;
    @BindView(R.id.signup_step1_et_restaurant_name)
    EditText sigupStep1EtRestaurantName;
    @BindView(R.id.signup_step1_et_restaurant_email)
    EditText signupStep1EtRestaurantEmail;
    @BindView(R.id.signup_step1_et_Delivery_time)
    EditText signupStep1EtDeliveryTime;
    @BindView(R.id.signup_step1_spinner_city)
    Spinner signupStep1SpinnerCity;
    @BindView(R.id.signup_step1_spinner_region)
    Spinner signupStep1SpinnerRegion;
    @BindView(R.id.signup_step1_et_password)
    EditText signupStep1EtPassword;
    @BindView(R.id.signup_step1_et_confiem_password)
    EditText signupStep1EtConfiemPassword;
    @BindView(R.id.signup_step1_et_Minimum_Order)
    EditText signupStep1EtMinimumOrder;
    @BindView(R.id.signup_step1_et_Delivery_Charge)
    EditText signupStep1EtDeliveryCharge;


    Unbinder unbinder;

    CustomSpinnerAdapter citiesAdapter;
    CustomSpinnerAdapter regionsAdapter;
    @BindView(R.id.signup_step1_btn_login)
    Button signupStep1BtnLogin;


    public SignUpStep1RestaurantFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_signup_step1_restaurant, container, false);
        unbinder = ButterKnife.bind(this, view);
        citiesAdapter = new CustomSpinnerAdapter(getActivity());
        regionsAdapter = new CustomSpinnerAdapter(getActivity());

        getSpinnerData(APIManger.getApis().getCities(), signupStep1SpinnerCity
                , citiesAdapter, getString(R.string.city), new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if (i != 0) {
                            getSpinnerData(APIManger.getApis().getRegions(citiesAdapter.selectedId), signupStep1SpinnerRegion
                                    , regionsAdapter, getString(R.string.region));
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });



        return view;
    }

    @Override
    public void onBack() {
        super.onBack();
    }


    private void follow() {

        String name = sigupStep1EtRestaurantName.getText().toString();
        String email = signupStep1EtRestaurantEmail.getText().toString();
        String delivery_time = signupStep1EtDeliveryTime.getText().toString();
        int region = regionsAdapter.selectedId;
        final String password = signupStep1EtPassword.getText().toString();
        String password_confirmation = signupStep1EtConfiemPassword.getText().toString();
        String minimum_chager = signupStep1EtMinimumOrder.getText().toString();
        String delivery_cost = signupStep1EtDeliveryCharge.getText().toString();

        SaveData ( getActivity (), PASSWORD_RESTAURANT, password );
        SaveData ( getActivity (), CONFIRMATION_PASSWORD_RESTAURANT, password_confirmation);
        SaveData ( getActivity (), NAME_RESTAURANT, name );
        SaveData ( getActivity (), EMAIL_RESTAURANT, email);
        SaveData ( getActivity (), REGION_RESTAURANT, region );
        SaveData ( getActivity (), DELIVERYCOST_RESTAURANT, delivery_cost );
        SaveData ( getActivity (), MINIMUMCHARGER_RESTAURANT, minimum_chager );
        SaveData ( getActivity (), DELIVERY_TIME_RESTAURANT, delivery_time );




        validate();
        SignupStep2RestaurantFragment signupStep2RestaurantFragment = new SignupStep2RestaurantFragment();
        HelperMethod.ReplaceFragment(getActivity().getSupportFragmentManager(),signupStep2RestaurantFragment,
                R.id.fram,null,null);

    }

    public boolean validate() {
        boolean valid = true;
        if (sigupStep1EtRestaurantName.length() < 1 || sigupStep1EtRestaurantName.length() > 32) {
            sigupStep1EtRestaurantName.setError("Please enter valid name");
            valid = false;
        }

        if (signupStep1EtRestaurantEmail.length() < 1 || signupStep1EtRestaurantEmail.length() > 32) {
            signupStep1EtRestaurantEmail.setError("Please enter valid email address");
            valid = false;
        }
        if (signupStep1EtDeliveryTime.length() < 1 || signupStep1EtDeliveryTime.length() > 32) {
            signupStep1EtDeliveryTime.setError("Please enter valid name");
            valid = false;
        }

        if (signupStep1EtPassword.length() < 1) {
            signupStep1EtPassword.setError("Please enter valid password");
            valid = false;
        }
        if (signupStep1EtConfiemPassword.length() < 1) {
            signupStep1EtConfiemPassword.setError("Please enter valid password");
            valid = false;
        }
        if (!signupStep1EtConfiemPassword.equals(signupStep1EtPassword)) {
            signupStep1EtPassword.setError("Passwords don't match!");
            valid = false;
        }
        if (signupStep1EtMinimumOrder.length() < 1 || signupStep1EtMinimumOrder.length() > 32) {
            signupStep1EtMinimumOrder.setError("Please enter valid name");
            valid = false;
        }
        if (signupStep1EtDeliveryCharge.length() < 1 || signupStep1EtDeliveryCharge.length() > 32) {
            signupStep1EtDeliveryCharge.setError("Please enter valid name");
            valid = false;
        } else {
            Toast.makeText(getActivity(), "Success", Toast.LENGTH_LONG).show();
        }
        return valid;
    }

    @OnClick(R.id.signup_step1_btn_login)
    public void onClick() {
        follow();
    }
}
