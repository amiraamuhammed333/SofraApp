package com.example.sofraapp.view.fragment.login_cycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sofraapp.R;
import com.example.sofraapp.data.api.APIManger;
import com.example.sofraapp.data.model.client.login_client.NewPassworadClientResponse;
import com.example.sofraapp.view.activity.HomeActivity;
import com.example.sofraapp.view.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NewPasswordRestaurantFragment extends BaseFragment {
    View view;
    Unbinder unbinder;

    BaseFragment baseFragment;
    @BindView(R.id.new_password_et_old_password)
    EditText newPasswordEtOldPassword;
    @BindView(R.id.new_password_et_new_password)
    EditText newPasswordEtNewPassword;
    @BindView(R.id.new_password_et_confirm_password)
    EditText newPasswordEtConfirmPassword;
    @BindView(R.id.new_password_btn_send)
    Button newPasswordBtnSend;
    String oldPass,newPass,confirmPass;

    public NewPasswordRestaurantFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setUpActivity();
        view = inflater.inflate(R.layout.new_restaurant_password, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick(R.id.new_password_btn_send)
    public void onClick() {
        changePass();
    }

    private void changePass(){
        oldPass=newPasswordEtOldPassword.getText().toString();
        newPass=newPasswordEtNewPassword.getText().toString();
        confirmPass=newPasswordEtConfirmPassword.getText().toString();
        APIManger.getApis().newPasswordRestauarnt("gjkiGjOvDxwOW5coGSAYJt20gPViDAPZXv8j6sp2KaDEZE1s9slvtSR3cfYe",
                oldPass, newPass,confirmPass).enqueue(new Callback<NewPassworadClientResponse>() {
            @Override
            public void onResponse(Call<NewPassworadClientResponse> call, Response<NewPassworadClientResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getActivity(),response.body().getMsg(),Toast.LENGTH_LONG).show();
                    Intent intent= new Intent(getActivity(), HomeActivity.class);
                    startActivity(intent);
                }            }

            @Override
            public void onFailure(Call<NewPassworadClientResponse> call, Throwable t) {

            }
        });

    }
}
