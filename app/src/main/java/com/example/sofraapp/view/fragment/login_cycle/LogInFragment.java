package com.example.sofraapp.view.fragment.login_cycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sofraapp.R;
import com.example.sofraapp.data.api.APIManger;
import com.example.sofraapp.data.local.SharedPreference;
import com.example.sofraapp.data.model.client.login_client.ClientResponse;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.login_restaurant.RestaurantResponse;
import com.example.sofraapp.helper.HelperMethod;
import com.example.sofraapp.view.activity.HomeActivity;
import com.example.sofraapp.view.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sofraapp.data.local.SharedPreference.CLIENT;
import static com.example.sofraapp.data.local.SharedPreference.EMAIL_CLIENT;
import static com.example.sofraapp.data.local.SharedPreference.EMAIL_RESTAURANT;
import static com.example.sofraapp.data.local.SharedPreference.PASSWORD_CLIENT;
import static com.example.sofraapp.data.local.SharedPreference.PASSWORD_RESTAURANT;
import static com.example.sofraapp.data.local.SharedPreference.RESTAURANT;
import static com.example.sofraapp.data.local.SharedPreference.SaveData;
import static com.example.sofraapp.data.local.SharedPreference.USER_DATA_CLIENT;
import static com.example.sofraapp.data.local.SharedPreference.USER_DATA_RESTAURANT;
import static com.example.sofraapp.data.local.SharedPreference.USER_TYPE;


public class LogInFragment extends BaseFragment {
    View view;
    @BindView(R.id.login_tv_signin)
    TextView loginTvSignin;
    @BindView(R.id.login_et_email)
    EditText loginEtEmail;
    @BindView(R.id.login_et_password)
    EditText loginEtPassword;
    @BindView(R.id.login_btn_forget)
    TextView loginBtnForget;
    @BindView(R.id.login_btn_login)
    Button loginBtnLogin;
    @BindView(R.id.login_btn_new_accout)
    Button loginBtnNewAccout;
    Unbinder unbinder;

    public LogInFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick({R.id.login_btn_forget, R.id.login_btn_login, R.id.login_btn_new_accout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn_forget:
                if (SharedPreference.loadStringData(getActivity(), USER_TYPE).equals(CLIENT)) {
                    ResetPaasswordClientFragment resetPaasswordClientFragment= new ResetPaasswordClientFragment();
                    HelperMethod.ReplaceFragment(getActivity().getSupportFragmentManager(),resetPaasswordClientFragment,
                            R.id.fram,null,null);

                } else if (SharedPreference.loadStringData(getActivity(), USER_TYPE).equals(RESTAURANT)) {
                    NewPasswordRestaurantFragment newPasswordRestaurantFragment= new NewPasswordRestaurantFragment();
                    HelperMethod.ReplaceFragment(getActivity().getSupportFragmentManager(),newPasswordRestaurantFragment,
                            R.id.fram,null,null);
                }
                break;
            case R.id.login_btn_login:
                String email = loginEtEmail.getText().toString();
                String password = loginEtPassword.getText().toString();
                if (SharedPreference.loadStringData(getActivity(), USER_TYPE).equals(CLIENT)) {
                    onLoginClient(email, password);
                } else if (SharedPreference.loadStringData(getActivity(), USER_TYPE).equals(RESTAURANT)) {
                    onLoginRestaurant(email, password);
                }

                break;
            case R.id.login_btn_new_accout:
                if (SharedPreference.loadStringData(getActivity(), USER_TYPE).equals(CLIENT)) {
                    SignupClientFragment signinClientFragment = new SignupClientFragment();
                    HelperMethod.ReplaceFragment(getActivity().getSupportFragmentManager(),
                            signinClientFragment, R.id.fram
                            , null, null);
                } else if (SharedPreference.loadStringData(getActivity(), USER_TYPE).equals(RESTAURANT)) {
                    SignUpStep1RestaurantFragment signinRestaurantFragment = new SignUpStep1RestaurantFragment();
                    HelperMethod.ReplaceFragment(getActivity().getSupportFragmentManager(),
                            signinRestaurantFragment, R.id.fram,
                            null, null); }
                break; } }

    private void onLoginClient(String email, String password) {
        APIManger.getApis().loginClient(email, password)
                .enqueue(new Callback<ClientResponse>() {
                    @Override
                    public void onResponse(Call<ClientResponse> call, Response<ClientResponse> response) {
                        try {
                            if (response.isSuccessful()) {
                                SaveData(getActivity(), EMAIL_CLIENT, email);
                                SaveData(getActivity(), PASSWORD_CLIENT, password);
                                SaveData(getActivity(), USER_DATA_CLIENT, response.body().getData());
                                Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getActivity(), HomeActivity.class);
                                startActivity(intent);
                                getActivity().finish();
                            } else {
                                Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {} }
                        @Override
                    public void onFailure(Call<ClientResponse> call, Throwable t) { }});
    }

    private void onLoginRestaurant(String email, String password) {
        APIManger.getApis().loginRestaurant(email, password)
                .enqueue(new Callback<RestaurantResponse>() {
                    @Override
                    public void onResponse(Call<RestaurantResponse> call, Response<RestaurantResponse> response) {
                        try {
                            if (response.isSuccessful()) {
                                SaveData(getActivity(), EMAIL_RESTAURANT, email);
                                SaveData(getActivity(), PASSWORD_RESTAURANT, password);
                                SaveData(getActivity(), USER_DATA_RESTAURANT, response.body().getData());
                                Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();

//                                SignupClientFragment signinClientFragment = new SignupClientFragment();
//                                HelperMethod.ReplaceFragment(getActivity().getSupportFragmentManager(),
//                                        signinClientFragment, R.id.fram
//                                        , null, null);
                                Intent intent = new Intent(getActivity(), HomeActivity.class);
                                startActivity(intent);
                                getActivity().finish();
                            } else {
                                Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) { } }

                    @Override
                    public void onFailure(Call<RestaurantResponse> call, Throwable t) { }}); }


    private boolean validateLogin(String email, String password) {
        if (email == null || email.trim().length() == 0) {
            Toast.makeText(getActivity(), "email is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password == null || password.trim().length() == 0) {
            Toast.makeText(getActivity(), "Password is required", Toast.LENGTH_SHORT).show();
            return false; }
        return true;
    }
}
