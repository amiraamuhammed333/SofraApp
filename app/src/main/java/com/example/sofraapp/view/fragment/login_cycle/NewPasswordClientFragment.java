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


public class NewPasswordClientFragment extends BaseFragment {
    View view;
    Button loginBtnNewAccout;
    Unbinder unbinder;
    @BindView(R.id.new_password_et_code)
    EditText newPasswordEtCode;
    @BindView(R.id.new_password_et_new_password)
    EditText newPasswordEtNewPassword;
    @BindView(R.id.new_password_et_confirm_password)
    EditText newPasswordEtConfirmPassword;
    @BindView(R.id.new_password_btn_send)
    Button newPasswordBtnSend;
    String code,newPass,confirmPass;

    public NewPasswordClientFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.new_client_password, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void newpass(){
        code=newPasswordEtCode.getText().toString();
        newPass=newPasswordEtNewPassword.getText().toString();
        confirmPass=newPasswordEtConfirmPassword.getText().toString();
        APIManger.getApis().newPasswordClient(code,newPass,confirmPass).enqueue(new Callback<NewPassworadClientResponse>() {
            @Override
            public void onResponse(Call<NewPassworadClientResponse> call, Response<NewPassworadClientResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getActivity(),response.body().getMsg(),Toast.LENGTH_LONG).show();
                    Intent intent= new Intent(getActivity(),HomeActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<NewPassworadClientResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick(R.id.new_password_btn_send)
    public void onClick() {
        newpass();
    }
}
